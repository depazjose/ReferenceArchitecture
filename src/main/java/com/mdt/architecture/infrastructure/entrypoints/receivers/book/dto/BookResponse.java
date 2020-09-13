package com.mdt.architecture.infrastructure.entrypoints.receivers.book.dto;

import java.util.Map;

import com.mdt.architecture.domain.model.Book;

import lombok.Builder;
import lombok.Getter;

public interface BookResponse {

  @Getter
  @Builder
  public class BookDetailResponse {
    private Long id;
    private Long isbn;
    private String name;
    private Integer quantity;
    private Boolean available;
    private String autor;
    private Map<String, Object> properties;

    public static BookDetailResponse fromModel(final Book book) {
      BookDetailResponse bookDetailResponse;
        bookDetailResponse = BookDetailResponse.builder()
        .id(book.getId())
        .isbn(book.getIsbn())
        .name(book.getName())
        .quantity(book.getQuantity())
        .available(book.getAvailable())
        .autor(book.getAutor())
        .properties(book.getProperties())
        .build();

      return bookDetailResponse;

    }
  }
}
