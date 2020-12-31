package com.mdt.architecture.infrastructure.adapters.database.publishinghouse;

import com.mdt.architecture.domain.model.publishingHouse.PublishingHouse;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class PublishingHouseRepositoryAdapter {

  private final PublishingHouseDataRepository publishingHouseDataRepository;

  public PublishingHouseRepositoryAdapter(PublishingHouseDataRepository publishingHouseDataRepository) {
    this.publishingHouseDataRepository = publishingHouseDataRepository;
  }

  public PublishingHouse savePublishingHouse(PublishingHouse publishingHouse) {
    PublishingHouseData publishingHouseData = new PublishingHouseData();
    publishingHouseData.setId(publishingHouse.getId());
    publishingHouseData.setName(publishingHouse.getName());
    publishingHouseData.setAdress(publishingHouse.getAdress());
    publishingHouseData.setActive(publishingHouse.getIsActive());
    return PublishingHouse.fromModel(publishingHouseDataRepository.save(publishingHouseData));
  }

  public List<PublishingHouse> findAll() {
    return PublishingHouse.fromModel(publishingHouseDataRepository.findAll());
  }
}
