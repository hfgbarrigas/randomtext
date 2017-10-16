package com.hfbarrigas.randomtext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hfbarrigas.randomtext.model.api.TextData;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("it")
public class TextApiIT {

    @Rule
    public Timeout timeout = Timeout.millis(2000);

    @Autowired
    private WebTestClient client;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setup() {

    }

}
