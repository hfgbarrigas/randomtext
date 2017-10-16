package com.hfbarrigas.randomtext.services;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Ordering;
import com.hfbarrigas.randomtext.exceptions.InternalErrorException;
import com.hfbarrigas.randomtext.gateways.RandomTextApiGateway;
import com.hfbarrigas.randomtext.logger.Loggable;
import com.hfbarrigas.randomtext.model.database.TextData;
import com.hfbarrigas.randomtext.model.gateways.RandomTextResponse;
import com.hfbarrigas.randomtext.repositories.ReactiveTextDataRepository;
import com.mongodb.util.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class TextDataService implements Loggable {

    private RandomTextApiGateway randomTextApiGateway;
    private ReactiveTextDataRepository reactiveTextDataRepository;

    private static final String HTML_PARAGRAPH_REGEX = "(<p>|</p>)";
    private static final String SPACE = " ";

    @Autowired
    public TextDataService(RandomTextApiGateway randomTextApiGateway, ReactiveTextDataRepository reactiveTextDataRepository) {
        this.randomTextApiGateway = randomTextApiGateway;
        this.reactiveTextDataRepository = reactiveTextDataRepository;
    }

    @Nonnull
    public Mono<TextData> getRandomText(@Nonnull Integer startParagraphs, @Nonnull Integer endParagraphs, @Nonnull Integer minWords, @Nonnull Integer maxWords) {
        final Stopwatch stopWatch = Stopwatch.createStarted();
        return Mono.zip(IntStream.range(startParagraphs, endParagraphs + 1)
                        .mapToObj(i -> randomTextApiGateway.getRandomText(i, minWords, maxWords))
                        .collect(Collectors.toList()),
                args -> {

                    String paragraphs = Stream.of(args)
                            .map(r -> ((RandomTextResponse) r).getText())
                            .reduce(String::concat)
                            .orElseThrow(() -> this.exception(args));

                    TextData textData = getMostFrequentWord(paragraphs);

                    //the average time wasted on each paragraph is to actually know it's size
                    String[] allParagraphs = paragraphs.split("(\r|\n)");
                    Stream.of(allParagraphs)
                            .forEach(paragraph -> {
                                Stopwatch stopwatch = Stopwatch.createStarted();
                                textData.setAvgParagraphSize(textData.getAvgParagraphSize() + paragraph.split(SPACE).length);
                                stopwatch.stop();
                                textData.setAvgParagraphProcessingTime(textData.getAvgParagraphProcessingTime() + stopwatch.elapsed(TimeUnit.MILLISECONDS));
                            });

                    //if by any change we get an empty response from random text api, this will throw exception because 0ed division
                    textData.setAvgParagraphProcessingTime(textData.getAvgParagraphProcessingTime() / allParagraphs.length);
                    textData.setAvgParagraphSize(textData.getAvgParagraphSize() / allParagraphs.length);

                    //finally check long it elapsed
                    stopWatch.stop();
                    textData.setTotalProcessingTime((float) stopWatch.elapsed(TimeUnit.MILLISECONDS));
                    //store into the database
                    reactiveTextDataRepository.save(textData).subscribe();
                    return textData;
                });
    }

    @Nonnull
    private TextData getMostFrequentWord(@Nonnull String paragraphs) {
        final String cleanedParagraphs = paragraphs.replaceAll(HTML_PARAGRAPH_REGEX, "");
        logger().info("Processing: " + cleanedParagraphs);

        final String[] words = cleanedParagraphs.split(SPACE);

        final LinkedHashMap<String, Long> wordsByFrequency = Stream.of(words)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted((es1, es2) -> es2.getValue().compareTo(es1.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (o, n) -> o, LinkedHashMap::new));

        final Iterator<String> it = wordsByFrequency.keySet().iterator();

        if (it.hasNext()) {
            return new TextData(it.next(), 0, 0F, 0F, System.currentTimeMillis());
        } else {
            throw new InternalErrorException("No most frequent word found for paragraphs: " + paragraphs);
        }
    }

    private InternalErrorException exception(Object[] args) {
        logger().error(String.format("Error processing request. Data was: %s ", JSON.serialize(args)));
        return new InternalErrorException("Error processing request.");
    }
}
