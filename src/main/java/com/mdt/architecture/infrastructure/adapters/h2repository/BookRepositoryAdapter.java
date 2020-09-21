package com.mdt.architecture.infrastructure.adapters.h2repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mdt.architecture.domain.model.Book;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class BookRepositoryAdapter {

  private final BookDataRepository jpaBookRepository;

  public BookRepositoryAdapter(final BookDataRepository jpaBookRepository) {
    this.jpaBookRepository = jpaBookRepository;
  }

  public Book saveBook(Book book) {
    BookData bookData = new BookData();
    bookData.setId(0L);
    bookData.setName(book.getName());
    bookData.setIsbn(book.getIsbn());
    bookData.setAuthor(book.getAuthor());
    bookData.setAvailable(book.getAvailable());
    bookData.setQuantity(book.getQuantity());
    try {
      bookData.setProperties(new ObjectMapper().writeValueAsString(book.getProperties()));
    } catch (JsonProcessingException e) {
      bookData.setProperties("{}");
    }
    if (Objects.nonNull(book.getStartSaleDate())) {
      bookData.setStartSaleDate(Timestamp.valueOf(book.getStartSaleDate()));
    }
    bookData.setStatus(book.getStatus());

    BookData bookDataResult = jpaBookRepository.save(bookData); 

    return Book.fromModel(bookDataResult);
  }

  public Book findByIsbn(Long isbn) {
    BookData bookData = jpaBookRepository.findByIsbn(isbn);

    if (Objects.nonNull(bookData)) {
      return Book.fromModel(bookData);
    }

    return Book.builder().build();
  }

  public List<Book> findAll() {
    return jpaBookRepository.findAll().stream()
            .map(Book::fromModel)
            .collect(Collectors.toList());
  }

  public List<Book> findAllByStatus(String status) {
    Specification<BookData> specification = criteriaByStatus(status);
    return jpaBookRepository.findAll(specification).stream()
            .map(Book::fromModel)
            .collect(Collectors.toList());
  }

  public int updateStatus(Long id, String status) {
    return jpaBookRepository.updateStatusById(id, status);
  }

  private Specification<BookData> criteriaByStatus(String status) {
    return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), status));
  }
}
