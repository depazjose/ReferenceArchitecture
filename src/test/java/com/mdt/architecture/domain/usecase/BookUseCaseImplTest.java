package com.mdt.architecture.domain.usecase;

import static org.mockito.ArgumentMatchers.any;

import com.mdt.architecture.domain.model.Book;
import com.mdt.architecture.domain.model.gateway.BookRepository;
import com.mdt.architecture.domain.usescase.BookUseCaseImpl;
import com.zaxxer.hikari.HikariDataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.junit.AfterClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookUseCaseImplTest {
 
  @Mock  
  private BookRepository bookRepository;
  
  @InjectMocks
  private BookUseCaseImpl bookUseCase;

  @Autowired
  private static HikariDataSource hikariDataSource;

  private Long id;
  private Long isbn;
  private String bookAuthor;
  private String bookName;
  private Map<String, Object> properties;
  private int elementSize;
  private static final int SIZE = 10;
  private static final long ID = 100L;
  private static final long ISBN = 1234567890L;

  @BeforeEach
  public void init() {
    id = ID;
    isbn = ISBN;
    bookAuthor = "J Tolkien";
    bookName = "The Lord of rings";
    properties = new HashMap<>();
    elementSize = SIZE;
  }

  @AfterClass
  public static void closeConnection() {
    if (Objects.nonNull(hikariDataSource)) {
      hikariDataSource.close();
    }
  }

  @Test
  public void shouldGetBook() {
    Mockito.when(bookRepository.findByIsbn(isbn)).thenReturn(Book.builder().build());  
    Assertions.assertNotNull(bookUseCase.findByIsbn(isbn));
  }

  @Test
  public void shouldCreateBook() {
    Mockito.when(bookRepository.saveBook(any(Book.class))).thenReturn(buildNewBook());
    Book result = bookUseCase.createBook(buildBook());
    Assertions.assertNotNull(result);
  }

  private Book buildBook() {
    Book book = Book.builder()
        .isbn(isbn).author(bookAuthor).name(bookName)
        .available(true).quantity(elementSize)
        .properties(properties).build();
    return book;
  }

  private Book buildNewBook() {
    Book book = Book.builder()
        .id(id).isbn(isbn).author(bookAuthor).name(bookName)
        .available(true).quantity(elementSize)
        .properties(properties).build();
    return book;
  }

}
