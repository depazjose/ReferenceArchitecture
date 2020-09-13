package com.mdt.architecture.domain.usescase;

import com.mdt.architecture.domain.model.Book;
import com.mdt.architecture.domain.model.gateway.BookRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookUseCaseImpl implements BookUseCase {

  private final BookRepository bookRepository;

  @Override
  public Book createBook(Book book) {
    return bookRepository.saveBook(book);
  }

  @Override
  public Book findByIsbn(Long isbn) {
    return bookRepository.findByIsbn(isbn);
  }   
    
}
