package com.mdt.architecture.infrastructure.adapters.h2repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "book_data")
@Getter
@Setter
public class BookData {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column(unique = true)
  private Long isbn;
  private String name;
  private Integer quantity;
  private Boolean available;
  private String autor;
  private String properties;
}
