package com.mdt.architecture.infrastructure.adapters.database.publishinghouse;

import com.mdt.architecture.domain.model.publishingHouse.PublishingHouse;
import com.mdt.architecture.domain.model.publishingHouse.gateway.PublishingHouseRepository;
import java.util.List;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
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
  @Cacheable(value = "PublishingHouses", key = "'key'", unless = "#result == null or #result.size() == 0")
  public List<PublishingHouse> findAll() {
    return publishingHouseRepositoryAdapter.findAll();
  }
}
