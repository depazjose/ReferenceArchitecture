package com.mdt.architecture.domain.shared;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class PublishingHouseNameException extends RuntimeException {

  public PublishingHouseNameException() {
    super("The name is required");
  }
}
