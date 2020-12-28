package com.mdt.architecture.infrastructure.entrypoints.receivers.web.book;

import com.mdt.architecture.domain.model.publishingHouse.PublishingHouse;
import com.mdt.architecture.domain.usescase.publishinghouse.PublishingHouseUseCase;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/house", produces = MediaType.APPLICATION_JSON_VALUE)
public class PublishingHouseController {

  private final PublishingHouseUseCase publishingHouseUseCase;

  public PublishingHouseController(final PublishingHouseUseCase publishingHouseUseCase) {
    this.publishingHouseUseCase = publishingHouseUseCase;
  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public void createBook(@RequestBody PublishingHouse publishingHouse) {

    publishingHouseUseCase.savePublishingHouse(publishingHouse);

  }
}
