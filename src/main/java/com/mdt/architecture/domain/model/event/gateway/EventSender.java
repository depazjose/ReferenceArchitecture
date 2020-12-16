package com.mdt.architecture.domain.model.event.gateway;

import com.mdt.architecture.domain.model.event.Payload;

public interface EventSender {
  void sendMessage(Payload payload);
}
