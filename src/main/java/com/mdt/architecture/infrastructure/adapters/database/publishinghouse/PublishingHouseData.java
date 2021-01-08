package com.mdt.architecture.infrastructure.adapters.database.publishinghouse;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

//H2

@Entity
@Table(name = "publishing_house_data")
@Getter
@Setter
public class PublishingHouseData {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @NotBlank(message = "Name is mandatory")
  private String name;
  private String address;
  @Column(name = "is_active")
  private Boolean isActive;
}
