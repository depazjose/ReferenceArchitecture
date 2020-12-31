package com.mdt.architecture.infrastructure.adapters.database.publishinghouse;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublishingHouseDataRepository extends MongoRepository<PublishingHouseData, Long> {

}
