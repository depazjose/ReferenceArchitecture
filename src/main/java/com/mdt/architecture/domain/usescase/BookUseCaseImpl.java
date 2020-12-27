package com.mdt.architecture.domain.usescase;

import com.mdt.architecture.domain.model.book.Book;
import com.mdt.architecture.domain.model.book.gateway.BookRepository;
import com.mdt.architecture.domain.model.event.Payload;
import com.mdt.architecture.domain.model.event.gateway.EventSender;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookUseCaseImpl implements BookUseCase {

  private final BookRepository bookRepository;
  private final EventSender eventSender;

  @Override
  public Book createBook(Book book) {
    Book result = bookRepository.saveBook(book);
    Payload payload = new Payload();
    payload.setId(result.getId());
    payload.setBarCode(result.getIsbn());
    eventSender.sendMessage(payload);
    return result;
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
