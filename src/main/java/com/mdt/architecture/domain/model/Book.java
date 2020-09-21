/*
Model package
 */
package com.mdt.architecture.domain.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mdt.architecture.infrastructure.adapters.h2repository.BookData;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class Book implements Serializable {
  private static final long serialVersionUID = -7883007230820875782L;

  private Long id;
  private Long isbn;
  private String name;
  private Integer quantity;
  private Boolean available;
  private String author;
  private Map<String, Object> properties;
  private LocalDateTime startSaleDate;
  private String status;

  public static Book fromModel(BookData bookData) {

    return Book.builder()
            .id(bookData.getId())
            .author(bookData.getAuthor())
            .name(bookData.getName())
            .isbn(bookData.getIsbn())
            .available(bookData.getAvailable())
            .quantity(bookData.getQuantity())
            .properties(convertMapFromJson(bookData.getProperties()))
            .status(bookData.getStatus())
            .startSaleDate(Objects.nonNull(bookData.getStartSaleDate())
                    ? toLocalDateTime(bookData.getStartSaleDate()) : null)
            .build();
  }

  public static List<Book> fromModel(List<BookData> bookDataList) {
    return bookDataList.stream()
            .filter(bookData -> Objects.nonNull(bookData))
            .map(book -> Book.fromModel(book))
            .filter(newBook -> Objects.nonNull(newBook))
            .collect(Collectors.toList());
  }

  private static Map<String, Object> convertMapFromJson(String json) {
    try {
      return new ObjectMapper().readValue(json, new TypeReference<Map<String, Object>>() {} );
    } catch (Exception  e) {
      return new HashMap<>();
    }
  }

  private static LocalDateTime toLocalDateTime(Timestamp timestamp) {
    return timestamp.toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime();
  }
}
