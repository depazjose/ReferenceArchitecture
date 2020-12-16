package com.mdt.architecture.infrastructure.entrypoints.receivers.web.book;

import static org.mockito.ArgumentMatchers.any;

import com.mdt.architecture.domain.model.book.Book;
import com.mdt.architecture.domain.usescase.BookUseCase;
import com.mdt.architecture.infrastructure.entrypoints.receivers.web.book.dto.BookRequest;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

  private static final int SIZE = 10;
  private static final long ID = 100L;
  private static final long ISBN = 1234567890L;

  @Mock
  private BookUseCase bookUseCase;

  @InjectMocks
  private BookController bookController;

  private String bookAuthor = "J Tolkien";
  private String bookName = "The Lord of rings";
  private Map<String, Object> properties = new HashMap<>();

  @BeforeEach
  void init() {
  }

  @Test
  void shouldGetBook() {
    Mockito.when(bookUseCase.findByIsbn(ISBN)).thenReturn(buildBook());
    Assertions.assertEquals(ISBN, bookController.getBook(ISBN).getIsbn());
  }

  @Test
  void shouldCreateNewBook() {

    BookRequest.CreationBookRequest request = buildBookRequest();
    Book newBook = buildNewBook();

    Mockito.when(bookUseCase.findByIsbn(ISBN)).thenReturn(null);
    Mockito.when(bookUseCase.createBook(any())).thenReturn(newBook);
    Assertions.assertNotNull(bookController.createBook(request, null).getBody().getId());
  }

  @Test
  void shouldGetAvailableBook() {
    Book newBook = buildNewBook();

    Mockito.when(bookUseCase.findByIsbn(any(Long.class))).thenReturn(newBook);

    Assertions.assertEquals(true,
        bookController.getBook(ISBN).getAvailable());
  }

  private Book buildBook() {
    return Book.builder()
        .isbn(ISBN).author(bookAuthor).name(bookName)
        .quantity(SIZE)
        .properties(properties).build();
  }

  private Book buildNewBook() {
    return Book.builder()
        .id(ID).isbn(ISBN).author(bookAuthor).name(bookName)
        .available(true).quantity(SIZE)
        .properties(properties).build();
  }

  private BookRequest.CreationBookRequest buildBookRequest() {
    BookRequest.CreationBookRequest creationBookRequest = new BookRequest.CreationBookRequest();
    creationBookRequest.setAuthor(bookAuthor);
    creationBookRequest.setName(bookName);
    creationBookRequest.setIsbn(ISBN);
    creationBookRequest.setQuantity(SIZE);
    creationBookRequest.setProperties(properties);
    return creationBookRequest;
  }

}
