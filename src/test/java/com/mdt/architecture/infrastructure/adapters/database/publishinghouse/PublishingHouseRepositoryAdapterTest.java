package com.mdt.architecture.infrastructure.adapters.database.publishinghouse;

import static org.mockito.ArgumentMatchers.any;

import com.mdt.architecture.domain.model.publishingHouse.PublishingHouse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class PublishingHouseRepositoryAdapterTest {

  @Mock
  private PublishingHouseDataRepository publishingHouseDataRepository;

  @Mock
  private PublishingHouseRepositoryAdapter publishingHouseRepositoryAdapter;

  private PublishingHouseData newPublishingHouseData;

  @BeforeEach
  public void init() {
    newPublishingHouseData = new PublishingHouseData();
    newPublishingHouseData.setId("id2");
    newPublishingHouseData.setAdress("Calle13");
    newPublishingHouseData.setName("Babel");
    newPublishingHouseData.setIsActive(false);
  }

  void shouldSaveNewPublishingHouse() {
    Mockito.when(publishingHouseDataRepository.save(any(PublishingHouseData.class)))
            .thenReturn(getPublishingHouseData());
    PublishingHouse result = publishingHouseRepositoryAdapter.savePublishingHouse(getPublishingHouse());
    Assertions.assertTrue(newPublishingHouseData.getId().equals(publishingHouseRepositoryAdapter
            .savePublishingHouse(getPublishingHouse()).getId()));
  }

  private PublishingHouse getPublishingHouse() {
    PublishingHouse publishingHouse = new PublishingHouse();
    publishingHouse.builder()
                .name(newPublishingHouseData.getName())
                .id(newPublishingHouseData.getId())
                .adress(newPublishingHouseData.getAdress())
                .isActive(newPublishingHouseData.getIsActive())
                .build();
    return publishingHouse;
  }

  private PublishingHouseData getPublishingHouseData() {
    return newPublishingHouseData;
  }
}
