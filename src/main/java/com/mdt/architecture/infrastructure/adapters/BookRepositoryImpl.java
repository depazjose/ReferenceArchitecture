package com.mdt.architecture.infrastructure.adapters;

import com.mdt.architecture.domain.model.book.Book;
import com.mdt.architecture.domain.model.book.gateway.BookRepository;
import com.mdt.architecture.infrastructure.adapters.database.book.BookRepositoryAdapter;
import java.util.List;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class BookRepositoryImpl implements BookRepository {

  private final BookRepositoryAdapter bookRepositoryAdapter;

  public BookRepositoryImpl(final BookRepositoryAdapter bookRepositoryAdapter) {
    this.bookRepositoryAdapter = bookRepositoryAdapter;
  }

  @Override
  public Book saveBook(Book book) {
    return bookRepositoryAdapter.saveBook(book);
  }

  @Override
  public Book findByIsbn(Long isbn) {
    return bookRepositoryAdapter.findByIsbn(isbn);
  }

  @Override
  @Cacheable(value = "Books", key = "'key'", unless = "#result == null or #result.size() == 0")
  public List<Book> findAll() {
    return bookRepositoryAdapter.findAll();
  }

  @Override
  public List<Book> findAllByStatus(String status) {
    return bookRepositoryAdapter.findAllByStatus(status);
  }

  @Override
  public int updateStatus(Long id, String status) {
    return bookRepositoryAdapter.updateStatus(id, status);
  }
}

