package com.mdt.architecture.domain.usecase.publishinghouse;

import com.mdt.architecture.domain.model.publishingHouse.PublishingHouse;
import com.mdt.architecture.domain.model.publishingHouse.gateway.PublishingHouseRepository;
import com.mdt.architecture.domain.shared.PublishingHouseNameException;
import com.mdt.architecture.domain.usescase.publishinghouse.PublishingHouseUseCaseImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class PublishingHouseUseCaseImplTest {

  @Mock
  private PublishingHouseRepository publishingHouseRepository;

  @InjectMocks
  private PublishingHouseUseCaseImpl publishingHouseUseCase;

  @BeforeEach
  public void init() {

  }

  @Test
  void shouldSaveNewPublishingHouse() {
    Mockito.when(publishingHouseRepository.savePublishingHouse(getNewBook())).thenReturn(getNewBook());
    PublishingHouse result = publishingHouseUseCase.savePublishingHouse(getNewBook());
    Assertions.assertNotNull(result);
  }

  @Test
  void shouldFailedToSaveNewPublishingHouseWithOutName() {
    Mockito.when(publishingHouseRepository.savePublishingHouse(bookWithOutName())).thenReturn(bookWithOutName());
    Assertions.assertThrows(PublishingHouseNameException.class, () ->
            publishingHouseUseCase.savePublishingHouse(bookWithOutName()));
  }

  @Test
  void shouldGetAllHouses() {
    Mockito.when(publishingHouseRepository.findAll()).thenReturn(publishingHouseList());
    List<PublishingHouse> publishingHouses = publishingHouseUseCase.findAll();
    Assertions.assertNotNull(publishingHouses);
  }

  private PublishingHouse getNewBook() {

    return PublishingHouse.builder().id("id1")
                .adress("Cra1")
                .name("Editorial Norma")
                .isActive(true)
                .build();

  }

  private PublishingHouse bookWithOutName() {
    PublishingHouse publishingHouse = new PublishingHouse();
    publishingHouse.setName(null);
    return publishingHouse;
  }

  private List<PublishingHouse> publishingHouseList() {
    List<PublishingHouse> publishingHouses = new ArrayList<>();
    publishingHouses.add(getNewBook());
    publishingHouses.add(getNewBook());
    publishingHouses.add(getNewBook());
    return publishingHouses;
  }
}
