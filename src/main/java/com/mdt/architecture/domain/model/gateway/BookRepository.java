package com.mdt.architecture.domain.model.gateway;

import com.mdt.architecture.domain.model.Book;

public interface BookRepository {
  Book saveBook(Book book);
  Book findByIsbn(Long isbn);
}
