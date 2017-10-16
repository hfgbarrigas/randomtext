package com.hfbarrigas.randomtext.repositories;

import com.hfbarrigas.randomtext.model.database.TextData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ReactiveTextDataRepository extends ReactiveMongoRepository<TextData, String> {

}
