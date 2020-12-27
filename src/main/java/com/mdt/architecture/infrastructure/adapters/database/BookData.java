package com.mdt.architecture.infrastructure.adapters.database;

import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "book_data")
@NoArgsConstructor
public class BookData {
  @Id
  private String id;
  @Column(unique = true)
  private Long isbn;
  @NotBlank(message = "Name is mandatory")
  private String name;
  private Integer quantity;
  private Boolean available;
  @NotBlank(message = "Author is mandatory")
  private String author;
  private String properties;
  @Column(name = "start_sale_date")
  private ZonedDateTime startSaleDate;
  private String status;
}
