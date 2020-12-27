package com.mdt.architecture.applications.configuration;

import com.mongodb.client.MongoClient;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.mongo.MongoLockProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShedLockConfig {

  @Bean
  public LockProvider lockProvider(MongoClient mongo) {
    return new MongoLockProvider(mongo.getDatabase("mdt"));
  }
}
