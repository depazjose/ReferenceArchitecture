package com.mdt.architecture.infrastructure.adapters.database.publishinghouse;

import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = PublishingHouseData.class, idClass = Long.class)
public interface PublishingHouseDataRepository extends JpaSpecificationExecutor<PublishingHouseData> {

  PublishingHouseData save(PublishingHouseData publishingHouseData);

  List<PublishingHouseData> findAll();
}