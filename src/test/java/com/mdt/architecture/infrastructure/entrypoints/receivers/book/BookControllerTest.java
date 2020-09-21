package com.mdt.architecture.infrastructure.entrypoints.receivers.book;

import static org.mockito.ArgumentMatchers.any;

import com.mdt.architecture.domain.usescase.BookUseCase;
import com.mdt.architecture.infrastructure.entrypoints.receivers.book.dto.BookRequest;
import java.util.HashMap;
import java.util.Map;

import com.mdt.architecture.domain.model.Book;

import com.mdt.architecture.infrastructure.entrypoints.receivers.book.dto.BookResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class BookControllerTest {

  private static final int SIZE = 10;
  private static final long ID = 100L;
  private static final long ISBN = 1234567890L;

  @Mock
  private BookUseCase bookUseCase;

  private BookController bookController;

  private Long id;
  private Long isbn;
  private String bookAuthor;
  private String bookName;
  private Map<String, Object> properties;
  private int elementSize;


  @BeforeEach
  void init() {
    id = ID;
    isbn = ISBN;
    bookAuthor = "J Tolkien";
    bookName = "The Lord of rings";
    properties = new HashMap<>();
    bookController = new BookController(bookUseCase);
    elementSize = SIZE;
  }

  @Test
  void shouldGetBook() {
    Mockito.when(bookUseCase.findByIsbn(isbn)).thenReturn(buildBook());

    Assertions.assertEquals(isbn, bookController.getBook(isbn).getIsbn());
  }

  @Test
  void shouldCreateNewBook() {

    BookRequest.CreationBookRequest request = buildBookRequest();
    Book newBook = buildNewBook();
    Book bookFound = Book.builder().build();

    Mockito.when(bookUseCase.findByIsbn(any(Long.class))).thenReturn(bookFound);
    Mockito.when(bookUseCase.createBook(any(Book.class))).thenReturn(newBook);

    Assertions.assertNotNull(bookController.createBook(request, null).getBody().getId());
  }

  @Test
  void shouldGetAvailableBook() {
    Book newBook = buildNewBook();

    Mockito.when(bookUseCase.findByIsbn(any(Long.class))).thenReturn(newBook);

    Assertions.assertEquals(true,
        bookController.getBook(isbn).getAvailable());
  }


  private Book buildBook() {
    Book book = Book.builder()
        .isbn(isbn).author(bookAuthor).name(bookName)
        .quantity(elementSize)
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

  /*
  generate book request
   */
  private BookRequest.CreationBookRequest buildBookRequest() {
    BookRequest.CreationBookRequest creationBookRequest = new BookRequest.CreationBookRequest();
    creationBookRequest.setAuthor(bookAuthor);
    creationBookRequest.setName(bookName);
    creationBookRequest.setIsbn(isbn);
    creationBookRequest.setQuantity(elementSize);
    creationBookRequest.setProperties(properties);
    creationBookRequest.setStartSaleDate(System.currentTimeMillis());
    return creationBookRequest;
  }

}
