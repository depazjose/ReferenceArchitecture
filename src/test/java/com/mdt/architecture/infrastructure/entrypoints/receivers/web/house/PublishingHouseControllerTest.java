package com.mdt.architecture.infrastructure.entrypoints.receivers.web.house;

import static org.mockito.ArgumentMatchers.any;

import com.mdt.architecture.domain.model.publishingHouse.PublishingHouse;
import com.mdt.architecture.domain.usescase.publishinghouse.PublishingHouseUseCase;
import com.mdt.architecture.infrastructure.entrypoints.receivers.web.house.dto.PublishingHouseRequest;
import com.mdt.architecture.infrastructure.entrypoints.receivers.web.house.dto.PublishingHouseResponse;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PublishingHouseControllerTest {

  @Mock
  private PublishingHouseUseCase publishingHouseUseCase;

  @InjectMocks
  private PublishingHouseController publishingHouseController;

  private String name = "el tiempo";
  private String adress = "calle 5";
  private Boolean isActive = true;
  private static final Long INITIALID = 1L;

  @BeforeEach
  void init() {
  }

  @Test
  void shouldCreateNewBook() {

    PublishingHouseRequest.CreationPublishingHouseRequest request = buildPublishingHouseRequest();
    PublishingHouse newHouse = buildNewPublishingHouse();

    Mockito.when(publishingHouseUseCase.savePublishingHouse(any())).thenReturn(newHouse);
    PublishingHouse result = publishingHouseController.createPublishingHouse(request);
    Assertions.assertNotNull(result);
    Assertions.assertNotNull(result.getId());
    Assertions.assertEquals(INITIALID, result.getId());
  }

  @Test
  void shouldGetAllHouses() {
    Mockito.when(publishingHouseUseCase.findAll()).thenReturn(getAllHouses());
    List<PublishingHouseResponse.PublishingHouseDetailResponse> publishingHouses = publishingHouseController
            .getAllPublishingHouses();

    Assertions.assertNotNull(publishingHouseController.getAllPublishingHouses());
    Assertions.assertEquals(publishingHouses.stream().map(PublishingHouseResponse.PublishingHouseDetailResponse::getId)
                    .distinct().count(), publishingHouses.size());
  }

  private PublishingHouse buildNewPublishingHouse() {
    return PublishingHouse.builder()
                .id(INITIALID)
                .name(name)
                .adress(adress)
                .isActive(isActive)
                .build();
  }

  private PublishingHouseRequest.CreationPublishingHouseRequest buildPublishingHouseRequest() {
    PublishingHouseRequest.CreationPublishingHouseRequest creationHouseRequest
            = new PublishingHouseRequest.CreationPublishingHouseRequest();
    creationHouseRequest.setAdress(adress);
    creationHouseRequest.setName(name);
    creationHouseRequest.setIsActive(isActive);
    return creationHouseRequest;
  }

  private List<PublishingHouse> getAllHouses() {
    List<PublishingHouse> publishingHouses = new ArrayList<>();

    PublishingHouse data1 = new PublishingHouse();
    data1.setId(2L);
    PublishingHouse data2 = new PublishingHouse();
    data2.setId(3L);
    publishingHouses.add(buildNewPublishingHouse());
    publishingHouses.add(data1);
    publishingHouses.add(data2);
    return publishingHouses;
  }
}
