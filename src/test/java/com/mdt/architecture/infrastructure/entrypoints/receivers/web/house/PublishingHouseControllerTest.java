package com.mdt.architecture.infrastructure.entrypoints.receivers.web.house;

import static org.mockito.ArgumentMatchers.any;

import com.mdt.architecture.domain.model.publishingHouse.PublishingHouse;
import com.mdt.architecture.domain.usescase.publishinghouse.PublishingHouseUseCase;
import com.mdt.architecture.infrastructure.entrypoints.receivers.web.house.dto.PublishingHouseRequest;
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
  private String id = "id3";

  @BeforeEach
  void init() {
  }

  @Test
  void shouldCreateNewBook() {

    PublishingHouseRequest.CreationPublishingHouseRequest request = buildPublishingHouseRequest();
    PublishingHouse newHouse = buildNewPublishingHouse();

    Mockito.when(publishingHouseUseCase.savePublishingHouse(any())).thenReturn(newHouse);
    Assertions.assertNotNull(publishingHouseController.createPublishingHouse(request));
    Assertions.assertEquals(id, publishingHouseController.createPublishingHouse(request).getId());
  }

  @Test
  void shouldGetAllHouses() {
    Mockito.when(publishingHouseUseCase.findAll()).thenReturn(getAllHouses());
    Assertions.assertNotNull(publishingHouseController.getAllPublishingHouses());
    Assertions.assertTrue(getAllHouses().get(0).getId().equals(id));
  }

  private PublishingHouse buildNewPublishingHouse() {
    return PublishingHouse.builder()
                .id(id)
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
    publishingHouses.add(buildNewPublishingHouse());
    publishingHouses.add(buildNewPublishingHouse());
    publishingHouses.add(buildNewPublishingHouse());
    return publishingHouses;
  }
}
