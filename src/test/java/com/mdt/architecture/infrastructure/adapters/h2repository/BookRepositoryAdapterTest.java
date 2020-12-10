package com.mdt.architecture.infrastructure.adapters.h2repository;

import static org.mockito.ArgumentMatchers.any;

import com.mdt.architecture.domain.model.Book;
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
class BookRepositoryAdapterTest {
  
  @Mock
  private BookDataRepository jpaBookRepository;  

  @InjectMocks
  private BookRepositoryAdapter bookRepositoryAdapter;  

  private Long id;
  private Long isbn;
  private String bookAutHor;
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
    bookAutHor = "J Tolkien";
    bookName = "The Lord of rings";
    properties = new HashMap<>();
    elementSize = SIZE;
  }

  @Test
  public void shouldRetrieveBookData() {

    Mockito.when(jpaBookRepository.findByIsbn(isbn)).thenReturn(builBookData());
    Assertions.assertNotNull(bookRepositoryAdapter.findByIsbn(isbn));
  }

  @Test
  public void shouldCreateBookData() {
    Mockito.when(jpaBookRepository.save(any(BookData.class)))
        .thenReturn(builBookData());
    Assertions.assertEquals(
        isbn,
        bookRepositoryAdapter.saveBook(buildBook()).getIsbn());
  }

  private BookData builBookData() {
    Book book = buildBook();
    BookData bookData = new BookData();
    bookData.setId(id);
    bookData.setName(book.getName());
    bookData.setIsbn(book.getIsbn());
    bookData.setAuthor(book.getAuthor());
    bookData.setAvailable(book.getAvailable());
    bookData.setQuantity(book.getQuantity());
    return bookData;
  }

  private Book buildBook() {
    Book book = Book.builder()
        .isbn(isbn).author(bookAutHor).name(bookName)
        .available(true).quantity(elementSize)
        .properties(properties).build();
    return book;
  }

}
