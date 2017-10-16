package com.hfbarrigas.randomtext.mapper;

import com.hfbarrigas.randomtext.model.api.TextData;
import org.springframework.stereotype.Component;

@Component
public class ApiMapper {

    public TextData toApiTextData(com.hfbarrigas.randomtext.model.database.TextData td) {
        return TextData.builder()
                .withAvgParagraphProcessingTime(td.getAvgParagraphProcessingTime())
                .withAvgParagraphSize(td.getAvgParagraphSize())
                .withMostFrequentWord(td.getMostFrequentWord())
                .withTotalProcessingTime(td.getTotalProcessingTime())
                .build();
    }
}
