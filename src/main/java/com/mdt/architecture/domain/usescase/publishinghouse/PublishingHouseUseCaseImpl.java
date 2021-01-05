package com.mdt.architecture.domain.usescase.publishinghouse;

import com.mdt.architecture.domain.model.publishingHouse.PublishingHouse;
import com.mdt.architecture.domain.model.publishingHouse.gateway.PublishingHouseRepository;
import com.mdt.architecture.domain.shared.PublishingHouseNameException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PublishingHouseUseCaseImpl implements PublishingHouseUseCase {

  private final PublishingHouseRepository publishingHouseRepository;

  public PublishingHouseUseCaseImpl(PublishingHouseRepository publishingHouseRepository) {
    this.publishingHouseRepository = publishingHouseRepository;
  }

  @Override
  public PublishingHouse savePublishingHouse(PublishingHouse publishingHouse) {
    if (publishingHouse.getName() == null) {
      throw new PublishingHouseNameException();
    } else {
      return publishingHouseRepository.savePublishingHouse(publishingHouse);
    }
  }

  @Override
  public List<PublishingHouse> findAll() {
    return publishingHouseRepository.findAll();
  }
}
