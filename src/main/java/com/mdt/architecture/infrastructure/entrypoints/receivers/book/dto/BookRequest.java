package com.mdt.architecture.infrastructure.entrypoints.receivers.book.dto;

import java.util.Map;

import com.mdt.architecture.domain.model.Book;

import lombok.Getter;
import lombok.Setter;

public interface BookRequest {

  @Getter
  @Setter
  class CreationBookRequest {
    private Long isbn;
    private String name;
    private Integer quantity;
    private String autor;
    private Map<String, Object> properties; 

    public static Book toModel(CreationBookRequest request) {
      Book book = Book.builder()
      .isbn(request.getIsbn())
      .name(request.getName())
      .quantity(request.getQuantity())
      .autor(request.getAutor())
      .properties(request.getProperties())
      .build();
      return book;
    }

  }

}
