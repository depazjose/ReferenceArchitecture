package com.mdt.architecture.domain.model.publishingHouse.gateway;

import com.mdt.architecture.domain.model.publishingHouse.PublishingHouse;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface PublishingHouseRepository {

  PublishingHouse savePublishingHouse(PublishingHouse publishingHouse);

  List<PublishingHouse> findAll();
}
