package com.hfbarrigas.randomtext.configuration;

import com.hfbarrigas.randomtext.properties.WebClientProperties;
import io.netty.channel.ChannelOption;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableConfigurationProperties(WebClientProperties.class)
public class WebClientConfiguration {
    @Bean
    public WebClient webClient(ClientHttpConnector httpConnector) {
        return WebClient.builder()
                .clientConnector(httpConnector)
                .build();
    }

    @Bean
    public ClientHttpConnector httpClient(WebClientProperties webClientProperties) {
        return new ReactorClientHttpConnector((options) -> options
                .compression(true)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, webClientProperties.getConnectTimeout())
                .option(ChannelOption.SO_KEEPALIVE, true));
    }
}
