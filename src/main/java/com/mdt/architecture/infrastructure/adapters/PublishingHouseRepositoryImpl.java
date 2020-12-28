package com.mdt.architecture.infrastructure.adapters;

import com.mdt.architecture.domain.model.publishingHouse.PublishingHouse;
import com.mdt.architecture.domain.model.publishingHouse.gateway.PublishingHouseRepository;
import com.mdt.architecture.infrastructure.adapters.database.publishinghouse.PublishingHouseRepositoryAdapter;
import java.util.List;

public class PublishingHouseRepositoryImpl implements PublishingHouseRepository {

  private PublishingHouseRepositoryAdapter publishingHouseRepositoryAdapter;

  public PublishingHouseRepositoryImpl(final PublishingHouseRepositoryAdapter publishingHouseRepositoryAdapter) {
    this.publishingHouseRepositoryAdapter = publishingHouseRepositoryAdapter;
  }

  @Override
  public PublishingHouse savePublishingHouse(PublishingHouse publishingHouse) {
    return publishingHouseRepositoryAdapter.savePublishingHouse(publishingHouse);
  }

  @Override
  public List<PublishingHouse> findAll() {
    return null;
  }
}
