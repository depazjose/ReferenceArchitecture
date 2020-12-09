package com.mdt.architecture.domain.usescase;

import com.mdt.architecture.domain.model.Book;
import com.mdt.architecture.domain.model.gateway.BookRepository;
import com.mdt.architecture.domain.shared.BookNotFoundException;
import java.util.List;
import java.util.Objects;
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
    Book result  = bookRepository.findByIsbn(isbn);

    if (Objects.isNull(result)) {
      throw new BookNotFoundException(isbn, "ISBN");
    }

    return result;
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
  public int updateStatus(Long id, String status) {
    return bookRepository.updateStatus(id, status);
  }


}
