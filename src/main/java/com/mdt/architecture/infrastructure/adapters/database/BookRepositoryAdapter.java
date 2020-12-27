package com.mdt.architecture.infrastructure.adapters.database;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mdt.architecture.domain.model.book.Book;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryAdapter {

  private final BookDataRepository jpaBookRepository;

  public BookRepositoryAdapter(final BookDataRepository jpaBookRepository) {
    this.jpaBookRepository = jpaBookRepository;
  }

  public Book saveBook(Book book) {
    BookData bookData = new BookData();
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
      bookData.setStartSaleDate(book.getStartSaleDate().atZone(ZoneId.of("UTC")));
    }
    bookData.setStatus(book.getStatus());

    return Book.fromModel(jpaBookRepository.save(bookData));
  }

  public Book findByIsbn(Long isbn) {
    BookData bookData = jpaBookRepository.findByIsbn(isbn);

    if (Objects.nonNull(bookData)) {
      return Book.fromModel(bookData);
    }

    return null;
  }

  public List<Book> findAll() {
    return jpaBookRepository.findAll().stream()
            .map(Book::fromModel)
            .collect(Collectors.toList());
  }

  public List<Book> findAllByStatus(String status) {
    Specification<BookData> specification = criteriaByStatus(status);
    return jpaBookRepository.findByStatus(status).stream()
            .map(Book::fromModel)
            .collect(Collectors.toList());
  }

  public int updateStatus(Book book) {
    BookData bookData = new BookData();
    bookData.setId(book.getId());
    bookData.setIsbn(book.getIsbn());
    bookData.setAuthor(book.getAuthor());
    bookData.setName(book.getName());
    bookData.setAvailable(book.getAvailable());
    bookData.setQuantity(book.getQuantity());
    bookData.setStatus(book.getStatus());
    try {
      bookData.setProperties(new ObjectMapper().writeValueAsString(book.getProperties()));
    } catch (JsonProcessingException e) {
      bookData.setProperties("{}");
    }
    if (Objects.nonNull(book.getStartSaleDate())) {
      bookData.setStartSaleDate(book.getStartSaleDate().atZone(ZoneId.of("UTC")));
    }
    return Objects.nonNull(jpaBookRepository.save(bookData)) ? 1 : 0;
  }

  private Specification<BookData> criteriaByStatus(String status) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), status);
  }
}
