package com.mdt.architecture.infrastructure.adapters;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.mdt.architecture.domain.model.event.Payload;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ServiceBusImplTest {

  @Mock
  private ServiceBusSenderClient sender;

  @InjectMocks
  private ServiceBusImpl serviceBusImpl;

  @BeforeEach
  void init() {
  }

  @Test
  void shouldSendMessage() {
    Payload payload = new Payload();
    payload.setId("1");
    payload.setBarCode(1234567890L);
    payload.setPrice(100.00d);
    payload.setPresentation(1);

    doNothing().when(sender).sendMessage(any());

    serviceBusImpl.sendMessage(payload);
  }


}
