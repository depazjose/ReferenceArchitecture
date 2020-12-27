package com.mdt.architecture.domain.usecase;

import com.mdt.architecture.domain.model.book.Book;
import com.mdt.architecture.domain.model.book.gateway.BookRepository;
import com.mdt.architecture.domain.model.event.gateway.EventSender;
import com.mdt.architecture.domain.usescase.BookUseCaseImpl;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class BookUseCaseImplTest {
  @Mock
  private BookRepository bookRepository;

  @Mock
  private EventSender sender;

  @InjectMocks
  private BookUseCaseImpl bookUseCase;

  private String bookAuthor = "J Tolkien";
  private String bookName = "The Lord of rings";
  private Map<String, Object> properties = new HashMap<>();
  private static final int SIZE = 10;
  private static final String ID = "100L";
  private static final Long ISBN = 1234567890L;

  @BeforeEach
  public void init() {
  }

  @Test
  void shouldCreateBook() {
    Book bookToSave = buildBook();
    Mockito.when(bookRepository.saveBook(bookToSave)).thenReturn(buildNewBook());
    Book result = bookUseCase.createBook(bookToSave);
    Assertions.assertNotNull(result);
  }

  @Test
  void shouldGetBook() {
    Mockito.when(bookRepository.findByIsbn(ISBN)).thenReturn(buildNewBook());
    Book result = bookUseCase.findByIsbn(ISBN);
    Assertions.assertNotNull(result);
  }

  private Book buildBook() {
    Book book = Book.builder()
        .isbn(ISBN).author(bookAuthor).name(bookName)
        .available(true).quantity(SIZE)
        .properties(properties).build();
    return book;
  }

  private Book buildNewBook() {
    Book book = Book.builder()
        .id(ID).isbn(ISBN).author(bookAuthor).name(bookName)
        .available(true).quantity(SIZE)
        .properties(properties).build();
    return book;
  }

}
