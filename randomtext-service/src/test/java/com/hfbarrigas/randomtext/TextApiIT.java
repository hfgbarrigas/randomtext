package com.hfbarrigas.randomtext;

import com.hfbarrigas.randomtext.model.api.TextData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.function.Consumer;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("it")
public class TextApiIT {

    @Autowired
    private WebTestClient client;

    @Test
    public void getSuccessTexData() {
        TextData textData = this.client
                .get()
                .uri("/betvictor/text?p_start=1&p_end=2&w_count_min=1&w_count_max=25")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(TextData.class)
                .returnResult()
                .getResponseBody();

        Assert.assertNotNull(textData.getAvgParagraphProcessingTime());
        Assert.assertNotNull(textData.getMostFrequentWord());
        Assert.assertNotNull(textData.getTotalProcessingTime());
        Assert.assertNotNull(textData.getAvgParagraphSize());
    }

    @Test
    public void getSuccessHistory() {
        this.client
                .get()
                .uri("/betvictor/history")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(TextData.class)
                .consumeWith(result -> Assert.assertTrue(result.getResponseBody().size() != 0));
    }

    @Test
    public void expectBadRequestOnMissingParametersOrInvalidInput() {
        expectBadRequest("p_start=1");
        expectBadRequest("p_start=0&p_end=2&w_count_min=1&w_count_max=25");
        expectBadRequest("p_start=25&p_end=10&w_count_min=1&w_count_max=25");
        expectBadRequest("p_start=1&p_end=10&w_count_min=25&w_count_max=1");
    }

    public void expectBadRequest(String condition) {
        this.client
                .get()
                .uri("/betvictor/text?" + condition)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isBadRequest();
    }
}
