package com.mdt.architecture.infrastructure.entrypoints.receivers.book.dto;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Map;

import com.mdt.architecture.domain.model.Book;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public interface BookRequest {

  @Getter
  @Setter
  class CreationBookRequest {
    @NotNull(message = "ISBN is required")
    private Long isbn;
    @NotBlank(message = "Name is required")
    private String name;
    @NotNull(message = "Quantity is required")
    private Integer quantity;
    @NotEmpty(message = "Author is required")
    private String author;
    private Map<String, Object> properties;
    private Long startSaleDate;
    @NotBlank(message = "Status is required")
    private String status;

    public static Book toModel(CreationBookRequest request) {
      return Book.builder()
      .isbn(request.getIsbn())
      .name(request.getName())
      .quantity(request.getQuantity())
      .author(request.getAuthor())
      .properties(request.getProperties())
      .startSaleDate(Instant.ofEpochMilli(request.startSaleDate)
              .atZone(ZoneId.systemDefault()).toLocalDateTime())
      .status(request.status)
      .build();
    }

  }

}
