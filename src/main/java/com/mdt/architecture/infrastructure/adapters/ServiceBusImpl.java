package com.mdt.architecture.infrastructure.adapters;

import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mdt.architecture.domain.model.event.Payload;
import com.mdt.architecture.domain.model.event.gateway.EventSender;
import com.mdt.architecture.infrastructure.adapters.azuresb.MessageConversionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ServiceBusImpl implements EventSender {
  private static final String MESSAGE_ERROR_TO_CONVERT_JSON = "Object can't be convert to JSON.";

  private static Logger logger = LogManager.getLogger(ServiceBusImpl.class);

  private final ServiceBusSenderClient senderClient;

  public ServiceBusImpl(final ServiceBusSenderClient senderClient) {
    this.senderClient = senderClient;
  }

  @Override
  @Async
  public void sendMessage(Payload payload) {

    try {
      senderClient.sendMessage(new ServiceBusMessage(getJsonString(payload)));
    } catch (Exception ex) {
      logger.error(ex.getMessage());
    }

    senderClient.close();
  }

  private String getJsonString(Payload payload) {
    try {
      return new ObjectMapper().writeValueAsString(payload);
    } catch (JsonProcessingException e) {
      throw new MessageConversionException(MESSAGE_ERROR_TO_CONVERT_JSON, e);
    }
  }
}
