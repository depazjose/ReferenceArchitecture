package com.mdt.architecture.infrastructure.adapters.database.book;

import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@RepositoryDefinition(domainClass = BookData.class, idClass = Long.class)
public interface BookDataRepository extends JpaSpecificationExecutor<BookData> {

  BookData save(BookData book);

  BookData findByIsbn(Long isbn);

  List<BookData> findAll();

  @Modifying
  @Transactional
  @Query("UPDATE BookData SET status =:statusSet "
      + "where id =:id")
  int updateStatusById(
      @Param("id") Long id,
      @Param("statusSet") String statusSet);

}
