package com.mdt.architecture.domain.usescase.publishinghouse;

import com.mdt.architecture.domain.model.publishingHouse.PublishingHouse;
import java.util.List;

public class PublishingHouseUseCaseImpl implements PublishingHouseUseCase {

  @Override
  public PublishingHouse savePublishingHouse(PublishingHouse publishingHouse) {
    System.out.println("saving publishing house if it doesnt exits");
    return null;
  }

  @Override
  public List<PublishingHouse> findAll() {
    System.out.println("all...");
    return null;
  }
}
