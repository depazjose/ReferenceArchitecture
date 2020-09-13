package com.mdt.architecture.domain.usescase;

import com.mdt.architecture.domain.model.Book;

public interface BookUseCase {
 
  Book createBook(Book book);
 
  Book findByIsbn(Long isbn);
}
