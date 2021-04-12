package com.mdt.architecture.domain.usecase;

import com.mdt.architecture.domain.model.book.Book;
import java.util.List;

public interface BookUseCase {
  Book createBook(Book book);

  Book findByIsbn(Long isbn);

  List<Book> findAll();

  List<Book> findAllByStatus(String status);

  int updateStatus(Book book);
}
