package com.mdt.architecture.applications.configuration;

import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.connection.ClusterConnectionMode;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@TestConfiguration
public class MongoTestConfig {
  @Value("${spring.data.mongodb.database}")
  private String database;

  @Value("${spring.data.mongodb.port}")
  private Integer port;

  @Value("${spring.data.mongodb.host}")
  private String host;

  @Value("${spring.data.mongodb.password}")
  private String password;

  @Value("${spring.data.mongodb.user}")
  private String user;

  @Value("${spring.data.mongodb.appName}")
  private String appName;

  @Value("${spring.data.mongodb.isSsl}")
  private Boolean isSsl;

  @Bean
  public MongoClient mongoClient() {

    ServerAddress serverAddress = new ServerAddress(host, port);

    MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
        .applicationName(appName)
        .applyToClusterSettings(builder -> {
          builder.mode(ClusterConnectionMode.SINGLE);
          builder.hosts(Collections.singletonList(serverAddress)); })
        .build();
    return MongoClients.create(mongoClientSettings);
  }

  @Bean
  public MongoTemplate mongoTemplate() {
    return new MongoTemplate(mongoClient(), database);
  }

}
