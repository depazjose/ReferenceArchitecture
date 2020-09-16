package com.mdt.architecture.domain.shared;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class BookFoundException extends RuntimeException {
  static final long serialVersionUID = -6034897190045766990L;

  public BookFoundException(Long isbn) {
      super(String.format("Book with ISBN %s already exists!", isbn));
  }
}
