/*
Model package
 */
package com.mdt.architecture.domain.model;

import java.util.Map;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Book {

  private Long id;
  private Long isbn;
  private String name;
  private Integer quantity;
  private Boolean available;
  private String autor;
  private Map<String, Object> properties;
}
