package com.mdt.architecture.domain.model.gateway;

import com.mdt.architecture.domain.model.Book;

import java.util.List;

public interface BookRepository {
  Book saveBook(Book book);
  Book findByIsbn(Long isbn);
  List<Book> findAll();
  List<Book> findAllByStatus(String status);
  int updateStatus(Long id, String status);
}
