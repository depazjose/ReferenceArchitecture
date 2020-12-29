package com.mdt.architecture.domain.usescase.publishinghouse;

import com.mdt.architecture.domain.model.publishingHouse.PublishingHouse;
import com.mdt.architecture.domain.model.publishingHouse.gateway.PublishingHouseRepository;
import java.util.List;

public class PublishingHouseUseCaseImpl implements PublishingHouseUseCase {

  private final PublishingHouseRepository publishingHouseRepository;

  public PublishingHouseUseCaseImpl(
      PublishingHouseRepository publishingHouseRepository) {
    this.publishingHouseRepository = publishingHouseRepository;
  }

  @Override
  public PublishingHouse savePublishingHouse(PublishingHouse publishingHouse) {
    return publishingHouseRepository.savePublishingHouse(publishingHouse);
  }

  @Override
  public List<PublishingHouse> findAll() {
    System.out.println("all...");
    return null;
  }
}
