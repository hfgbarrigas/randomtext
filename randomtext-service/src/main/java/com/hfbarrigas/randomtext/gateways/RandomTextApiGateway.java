package com.hfbarrigas.randomtext.gateways;

import com.hfbarrigas.randomtext.model.gateways.RandomTextResponse;
import com.hfbarrigas.randomtext.properties.WebClientProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.Nonnull;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Service
public class RandomTextApiGateway {

    private WebClient webClient;
    private WebClientProperties webClientProperties;

    @Autowired
    public RandomTextApiGateway(WebClient webClient, WebClientProperties webClientProperties) {
        this.webClient = webClient;
        this.webClientProperties = webClientProperties;
    }

    public Mono<RandomTextResponse> getRandomText(@Nonnull Integer numberOfParagraphs, @Nonnull Integer minWords, @Nonnull Integer maxWords) {
        return webClient
                .get()
                .uri("http://www.randomtext.me/api/giberish/p-{numberOfParagraphs}/{minWords}-{maxWords}", numberOfParagraphs, minWords, maxWords)
                .exchange()
                .timeout(Duration.of(webClientProperties.getReadTimeout(), ChronoUnit.MILLIS))
                .flatMap(clientResponse -> clientResponse.bodyToMono(RandomTextResponse.class));
    }
}
