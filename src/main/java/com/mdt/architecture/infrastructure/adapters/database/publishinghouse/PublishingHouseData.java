package com.mdt.architecture.infrastructure.adapters.database.publishinghouse;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "publishing_house_data")
@Getter
@Setter
public class PublishingHouseData {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String adress;
  private boolean isActive;
}
