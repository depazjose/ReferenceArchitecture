package com.mdt.architecture.domain.model.publishingHouse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mdt.architecture.infrastructure.adapters.database.publishinghouse.PublishingHouseData;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class PublishingHouse implements Serializable {

  private String id;
  private String name;
  private String adress;
  private Boolean isActive;

  public static PublishingHouse fromModel(PublishingHouseData publishingHouseData) {

    return PublishingHouse.builder()
                .id(publishingHouseData.getId())
                .name(publishingHouseData.getName())
                .adress(publishingHouseData.getAdress())
                .isActive(publishingHouseData.getIsActive())
                .build();
  }

  public static List<PublishingHouse> fromModel(List<PublishingHouseData> publishingHouseData) {
    return publishingHouseData.stream()
            .filter(Objects::nonNull)
            .map(PublishingHouse::fromModel)
            .collect(Collectors.toList());
  }
}
