package com.mdt.architecture.domain.shared;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookNotFoundException extends RuntimeException {

  public BookNotFoundException(Long id, String type) {
    super(String.format("Book with %s %s not found!", type, id));
  }

}
