package com.mdt.architecture.infrastructure.adapters.database.publishinghouse;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "PublishingHouse")
@Data
public class PublishingHouseData {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private String id;
  private String name;
  private String adress;
  private Boolean isActive;
}
