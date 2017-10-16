package com.hfbarrigas.randomtext.configuration;

import com.hfbarrigas.randomtext.properties.CustomMongoProperties;
import com.hfbarrigas.randomtext.repositories.ReactiveTextDataRepository;
import com.mongodb.ServerAddress;
import com.mongodb.async.client.MongoClientSettings;
import com.mongodb.connection.ClusterSettings;
import com.mongodb.connection.SocketSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableReactiveMongoRepositories(basePackageClasses = ReactiveTextDataRepository.class)
@EnableConfigurationProperties(CustomMongoProperties.class)
public class MongoConfiguration extends AbstractReactiveMongoConfiguration {

    private CustomMongoProperties customMongoProperties;
    private MongoProperties mongoProperties;

    @Autowired
    public MongoConfiguration(CustomMongoProperties customMongoProperties, MongoProperties mongoProperties) {
        this.customMongoProperties = customMongoProperties;
        this.mongoProperties = mongoProperties;
    }

    @Override
    protected String getDatabaseName() {
        return mongoProperties.getDatabase();
    }

    @Override
    public MongoClient reactiveMongoClient() {
        final SocketSettings socketSettings = mongoSocketSettings();
        //simplistic configuration
        return MongoClients.create(MongoClientSettings
                .builder()
                .applicationName(customMongoProperties.getApplicationName())
                .clusterSettings(ClusterSettings.builder()
                        .hosts(Collections.singletonList(new ServerAddress(
                                mongoProperties.getHost(),
                                mongoProperties.getPort()
                        )))
                        .serverSelectionTimeout(customMongoProperties.getServerSelectionTimeout(), TimeUnit.MILLISECONDS)
                        .build())
                .socketSettings(socketSettings)
                .heartbeatSocketSettings(socketSettings)
                .build());
    }

    private SocketSettings mongoSocketSettings() {
        return SocketSettings.builder()
                .connectTimeout(customMongoProperties.getConnectTimeout(), TimeUnit.MILLISECONDS)
                .readTimeout(customMongoProperties.getReadTimeout(), TimeUnit.MILLISECONDS)
                .build();
    }
}
