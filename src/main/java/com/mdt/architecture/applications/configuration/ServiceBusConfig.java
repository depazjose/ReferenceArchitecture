package com.mdt.architecture.applications.configuration;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBusConfig {

  @Value("${azure.serviceBus.connectionString}")
  private String connectionString;

  @Value("${azure.serviceBus.topicName}")
  private String topicName;

  @Value("${azure.serviceBus.subscriptionName}")
  private String subscriptionName;

  @Bean
  public ServiceBusSenderClient senderClient() {
    return new ServiceBusClientBuilder()
        .connectionString(connectionString)
        .sender()
        .topicName(topicName)
        .buildClient();
  }
}
