package com.mdt.architecture.domain.usecase;

import com.mdt.architecture.domain.model.book.Book;
import com.mdt.architecture.domain.model.book.gateway.BookRepository;
import java.util.List;
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

  @Override
  public List<Book> findAll() {
    return bookRepository.findAll();
  }

  @Override
  public List<Book> findAllByStatus(String status) {
    return bookRepository.findAllByStatus(status);
  }

  @Override
  public int updateStatus(Book book) {
    return bookRepository.updateStatus(book);
  }

}
