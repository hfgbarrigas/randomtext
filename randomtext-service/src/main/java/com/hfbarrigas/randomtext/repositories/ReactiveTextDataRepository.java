package com.hfbarrigas.randomtext.repositories;

import com.hfbarrigas.randomtext.model.database.TextData;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;

public interface ReactiveTextDataRepository extends ReactiveSortingRepository<TextData, String> {
    Flux<TextData> findTop10ByOrderByTimestampDesc();
}
