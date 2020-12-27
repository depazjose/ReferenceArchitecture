package com.mdt.architecture.infrastructure.adapters.database;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = BookData.class, idClass = String.class)
public interface BookDataRepository extends CrudRepository<BookData, String> {

  BookData save(BookData book);

  BookData findByIsbn(Long isbn);

  List<BookData> findAll();

  List<BookData> findByStatus(String status);

}
