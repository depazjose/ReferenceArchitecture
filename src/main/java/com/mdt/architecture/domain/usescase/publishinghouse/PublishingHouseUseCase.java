package com.mdt.architecture.domain.usescase.publishinghouse;

import com.mdt.architecture.domain.model.publishingHouse.PublishingHouse;
import java.util.List;

public interface PublishingHouseUseCase {

  PublishingHouse savePublishingHouse(PublishingHouse publishingHouse);

  List<PublishingHouse> findAll();
}
