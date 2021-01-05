package com.mdt.architecture.infrastructure.entrypoints.receivers.web.house.dto;

import com.mdt.architecture.domain.model.publishingHouse.PublishingHouse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public interface PublishingHouseResponse {

  @Builder
  @Getter
  @Setter
  @ToString
  class PublishingHouseDetailResponse {

    private String id;
    private String name;
    private String adress;
    private Boolean isActive;

    public static PublishingHouseDetailResponse fromModel(final PublishingHouse publishingHouse) {
      return PublishingHouseDetailResponse.builder()
                    .id(publishingHouse.getId())
                    .name(publishingHouse.getName())
                    .adress(publishingHouse.getAdress())
                    .isActive(publishingHouse.getIsActive())
                    .build();
    }

    public static List<PublishingHouseDetailResponse> fromModel(final List<PublishingHouse> publishingHouses) {
      return publishingHouses.stream().map(PublishingHouseDetailResponse::fromModel).collect(Collectors.toList());
    }
  }
}
