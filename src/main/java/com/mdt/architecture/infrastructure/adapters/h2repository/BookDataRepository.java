package com.mdt.architecture.infrastructure.adapters.h2repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = BookData.class, idClass = Long.class)
public interface BookDataRepository extends JpaSpecificationExecutor<BookData> {

  BookData save(BookData book);

  BookData findByIsbn(Long isbn);

  List<BookData> findAll();

}
