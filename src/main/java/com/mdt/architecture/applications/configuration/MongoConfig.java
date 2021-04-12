package com.mdt.architecture.applications.configuration;

import com.mdt.architecture.applications.configuration.converter.ZonedDateTimeReadConverter;
import com.mdt.architecture.applications.configuration.converter.ZonedDateTimeWriteConverter;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.connection.ClusterConnectionMode;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

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

  @Value("${spring.data.mongodb.withSha1}")
  private Boolean withSha1;

  @Bean
  public MongoClient mongoClient() {

    ServerAddress serverAddress = new ServerAddress(host, port);

    MongoClientSettings.Builder builder = commonBuilder(withSha1);
    builder.applyToClusterSettings(cluster -> {
      cluster.mode(ClusterConnectionMode.SINGLE);
      cluster.hosts(Collections.singletonList(serverAddress)); });

    MongoClientSettings mongoClientSettings = builder.build();

    return MongoClients.create(mongoClientSettings);
  }

  @Bean
  public MongoTemplate mongoTemplate() {
    return new MongoTemplate(mongoClient(), database);
  }

  @Override
  public MongoCustomConversions customConversions() {
    return new MongoCustomConversions(List.of(
        new ZonedDateTimeReadConverter(),
        new ZonedDateTimeWriteConverter()));
  }

  private MongoClientSettings.Builder commonBuilder(Boolean withSha1) {

    MongoClientSettings.Builder builder = MongoClientSettings.builder()
        .applyToSslSettings(ssl -> ssl.enabled(isSsl))
        .applicationName(appName);

    if (Boolean.TRUE.equals(withSha1)) {
      MongoCredential credential = MongoCredential.createScramSha1Credential(user, database, password.toCharArray());
      builder.credential(credential);
    }

    return builder;
  }

  @Override
  protected String getDatabaseName() {
    return database;
  }
}
