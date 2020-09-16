package com.mdt.architecture.infrastructure.entrypoints.receivers.book.dto;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mdt.architecture.domain.model.Book;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public interface BookResponse {

  @Builder
  @Getter
  @ToString
  @JsonInclude(JsonInclude.Include.NON_NULL)
  class BookDetailResponse {
    private Long id;
    private Long isbn;
    private String name;
    private Integer quantity;
    private Boolean available;
    private String author;
    private Map<String, Object> properties;
    private Long startSaleDate;
    private String status;

    public static BookDetailResponse fromModel(final Book book) {
      BookDetailResponse bookDetailResponse;
        bookDetailResponse = BookDetailResponse.builder()
        .id(book.getId())
        .isbn(book.getIsbn())
        .name(book.getName())
        .quantity(book.getQuantity())
        .available(book.getAvailable())
        .author(book.getAuthor())
        .properties(book.getProperties())
        .startSaleDate(Objects.nonNull(book.getStartSaleDate())
                ? book.getStartSaleDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() : null)
        .status(book.getStatus())
        .build();

      return bookDetailResponse;
    }

    public static List<BookDetailResponse> fromModel(final List<Book> books) {
      return books.stream().map(BookDetailResponse::fromModel).collect(Collectors.toList());
    }

  }
}
