package com.mdt.architecture.infrastructure.entrypoints.receivers.web.house.dto;

import com.mdt.architecture.domain.model.publishingHouse.PublishingHouse;
import lombok.Getter;
import lombok.Setter;

public interface PublishingHouseRequest {

  @Getter
  @Setter
  class CreationPublishingHouseRequest {

    private Long id;
    private String name;
    private String adress;
    private Boolean isActive;

    public static PublishingHouse toModel(CreationPublishingHouseRequest request) {
      return PublishingHouse.builder()
                    .name(request.name)
                    .adress(request.adress)
                    .isActive(request.isActive)
                    .build();
    }
  }
}
