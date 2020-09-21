package com.mdt.architecture.domain.usescase;

import com.mdt.architecture.domain.model.Book;

import java.util.List;

public interface BookUseCase {
 
  Book createBook(Book book);
 
  Book findByIsbn(Long isbn);

  List<Book> findAll();

  List<Book> findAllByStatus(String status);

  int updateStatus(Long id, String status);
}
