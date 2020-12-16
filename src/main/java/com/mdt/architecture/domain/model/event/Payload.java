package com.mdt.architecture.domain.model.event;

import lombok.Data;

@Data
public class Payload {

  private String id;
  private Double price;
  private Integer presentation;
  private Long barCode;

}
