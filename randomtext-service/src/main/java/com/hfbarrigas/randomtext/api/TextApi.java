package com.hfbarrigas.randomtext.api;

import com.hfbarrigas.randomtext.logger.Loggable;
import com.hfbarrigas.randomtext.mapper.ApiMapper;
import com.hfbarrigas.randomtext.model.api.TextData;
import com.hfbarrigas.randomtext.repositories.ReactiveTextDataRepository;
import com.hfbarrigas.randomtext.services.TextDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.Min;

@RestController
@Validated
@RequestMapping(value = "/betvictor")
public class TextApi implements Loggable {

    private ApiMapper apiMapper;
    private TextDataService textDataService;
    private ReactiveTextDataRepository reactiveTextDataRepository;

    @Autowired
    public TextApi(TextDataService textDataService,
                   ApiMapper apiMapper,
                   ReactiveTextDataRepository reactiveTextDataRepository) {
        this.textDataService = textDataService;
        this.apiMapper = apiMapper;
        this.reactiveTextDataRepository = reactiveTextDataRepository;
    }

    @GetMapping(value = "/text", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Mono<TextData> textData(@Min(1) @RequestParam(name = "p_start", required = true) Integer pStart,
                                   @RequestParam(name = "p_end", required = true) Integer pEnd,
                                   @Min(1) @RequestParam(name = "w_count_min", required = true) Integer wCountMin,
                                   @RequestParam(name = "w_count_max", required = true) Integer wCountMax) {
        return textDataService.getRandomText(pStart, pEnd, wCountMin, wCountMax).map(apiMapper::toApiTextData);
    }

    @GetMapping(value = "/history", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Flux<TextData> history() {
        return reactiveTextDataRepository.findTop10ByOrderByTimestampDesc()
                .map(apiMapper::toApiTextData);
    }
}
