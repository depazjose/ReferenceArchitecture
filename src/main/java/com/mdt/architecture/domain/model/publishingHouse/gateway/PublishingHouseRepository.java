package com.mdt.architecture.domain.model.publishingHouse.gateway;

import com.mdt.architecture.domain.model.publishingHouse.PublishingHouse;
import java.util.List;

public interface PublishingHouseRepository {

  PublishingHouse savePublishingHouse(PublishingHouse publishingHouse);

  List<PublishingHouse> findAll();
}
