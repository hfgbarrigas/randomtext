package com.hfbarrigas.randomtext;

import com.hfbarrigas.randomtext.logger.Loggable;
import com.hfbarrigas.randomtext.repositories.ReactiveTextDataRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.validation.constraints.NotNull;

@SpringBootApplication
public class RandomTextApplication implements Loggable {

    public static void main(String[] args) {
        SpringApplication.run(RandomTextApplication.class);
    }

    @Bean
    public CommandLineRunner clearDatabase(@NotNull ReactiveTextDataRepository repository) {
        return args -> repository.deleteAll();
    }
}
