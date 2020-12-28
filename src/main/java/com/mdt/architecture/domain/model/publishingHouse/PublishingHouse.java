package com.mdt.architecture.domain.model.publishingHouse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mdt.architecture.infrastructure.adapters.database.publishinghouse.PublishingHouseData;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class PublishingHouse {

  private Long id;
  private String name;
  private String adress;
  private Boolean isActive;

  public static PublishingHouse fromModel(PublishingHouseData publishingHouseData) {

    return PublishingHouse.builder()
                .id(publishingHouseData.getId())
                .name(publishingHouseData.getName())
                .adress(publishingHouseData.getAdress())
                .isActive(publishingHouseData.isActive())
                .build();
  }
}
