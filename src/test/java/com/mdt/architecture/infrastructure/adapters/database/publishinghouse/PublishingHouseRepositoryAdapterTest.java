package com.mdt.architecture.infrastructure.adapters.database.publishinghouse;

import static org.mockito.ArgumentMatchers.any;

import com.mdt.architecture.domain.model.publishingHouse.PublishingHouse;
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
public class PublishingHouseRepositoryAdapterTest {

  @Mock
  private PublishingHouseDataRepository publishingHouseDataRepository;

  @InjectMocks
  private PublishingHouseRepositoryAdapter publishingHouseRepositoryAdapter;

  private PublishingHouseData newPublishingHouseData;

  @BeforeEach
  public void init() {
    newPublishingHouseData = new PublishingHouseData();
    newPublishingHouseData.setAddress("Calle13");
    newPublishingHouseData.setName("Babel");
    newPublishingHouseData.setIsActive(false);
  }

  @Test
  void shouldSaveNewPublishingHouse() {
    Mockito.when(publishingHouseDataRepository.save(any(PublishingHouseData.class)))
            .thenReturn(buildANewPublishingHouseData());
    PublishingHouse result = publishingHouseRepositoryAdapter.savePublishingHouse(getPublishingHouse());
    Assertions.assertNotNull(result);
    Assertions.assertTrue(newPublishingHouseData.getId().equals(result.getId()));
  }

  @Test
  void shouldGetAllHouses() {
    Mockito.when(publishingHouseDataRepository.findAll()).thenReturn(publishingHouseList());
    List<PublishingHouse> publishingHouses = publishingHouseRepositoryAdapter.findAll();
    Assertions.assertNotNull(publishingHouses);
    Assertions.assertEquals(publishingHouses.stream().map(PublishingHouse::getId).distinct().count(),
            publishingHouses.size());
  }

  private PublishingHouse getPublishingHouse() {
    PublishingHouse publishingHouse = new PublishingHouse();
    publishingHouse.builder()
                .name(newPublishingHouseData.getName())
                .id(newPublishingHouseData.getId())
                .adress(newPublishingHouseData.getAddress())
                .isActive(newPublishingHouseData.getIsActive())
                .build();
    return publishingHouse;
  }

  private PublishingHouseData getPublishingHouseData() {
    return newPublishingHouseData;
  }

  private PublishingHouseData buildANewPublishingHouseData() {
    newPublishingHouseData.setId(123L);
    return newPublishingHouseData;
  }

  private List<PublishingHouseData> publishingHouseList() {
    List<PublishingHouseData> publishingHousesData = new ArrayList<>();
    PublishingHouseData data1 = new PublishingHouseData();
    data1.setId(124L);
    publishingHousesData.add(buildANewPublishingHouseData());
    return publishingHousesData;
  }
}
