package com.mdt.architecture.domain.usescase;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mdt.architecture.domain.model.Book;
import com.mdt.architecture.domain.model.gateway.BookRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

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

  @Override
  public List<Book> findAll() {
    return bookRepository.findAll();
  }

  @Override
  public List<Book> findAllByStatus(String status) {
    return bookRepository.findAllByStatus(status);
  }
}
