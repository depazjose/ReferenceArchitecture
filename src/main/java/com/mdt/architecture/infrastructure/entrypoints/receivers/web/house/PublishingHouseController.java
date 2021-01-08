package com.mdt.architecture.infrastructure.entrypoints.receivers.web.house;

import com.mdt.architecture.domain.model.publishingHouse.PublishingHouse;
import com.mdt.architecture.domain.usescase.publishinghouse.PublishingHouseUseCase;
import com.mdt.architecture.infrastructure.entrypoints.receivers.web.house.dto.PublishingHouseRequest;
import com.mdt.architecture.infrastructure.entrypoints.receivers.web.house.dto.PublishingHouseResponse;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/house", produces = MediaType.APPLICATION_JSON_VALUE)
public class PublishingHouseController {

  private static Logger logger = LogManager.getLogger(PublishingHouseController.class);

  private final PublishingHouseUseCase publishingHouseUseCase;

  public PublishingHouseController(PublishingHouseUseCase publishingHouseUseCase) {
    this.publishingHouseUseCase = publishingHouseUseCase;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "get all publishing houses")
  public List<PublishingHouseResponse.PublishingHouseDetailResponse> getAllPublishingHouses() {
    return PublishingHouseResponse.PublishingHouseDetailResponse.fromModel(publishingHouseUseCase.findAll());
  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "post a new house")
  public PublishingHouse createPublishingHouse(
          @RequestBody PublishingHouseRequest.CreationPublishingHouseRequest creationPublishingHouseRequest) {

    return publishingHouseUseCase.savePublishingHouse(PublishingHouseRequest.CreationPublishingHouseRequest
            .toModel(creationPublishingHouseRequest));
  }
}
