package com.mdt.architecture.infrastructure.adapters.h2repository;

import com.mdt.architecture.domain.model.Book;
import com.mdt.architecture.domain.model.gateway.BookRepository;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryAdapter implements BookRepository {

  private final BookDataRepository jpaBookRepository;

  public BookRepositoryAdapter(final BookDataRepository jpaBookRepository) {
    this.jpaBookRepository = jpaBookRepository;
  }

  @Override
  public Book saveBook(Book book) {

    BookData bookData = new BookData();
    bookData.setName(book.getName());
    bookData.setIsbn(book.getIsbn());
    bookData.setAutor(book.getAutor());
    bookData.setAvailable(book.getAvailable());
    bookData.setQuantity(book.getQuantity());
    
    BookData bookDataResult = jpaBookRepository.save(bookData); 

    Book bookResult = Book.builder()
    .id(bookDataResult.getId())
    .autor(bookDataResult.getAutor())
    .name(bookDataResult.getName())
    .isbn(bookDataResult.getIsbn())
    .available(bookDataResult.getAvailable())
    .quantity(bookDataResult.getQuantity())
    .build();

    return bookResult;
  }

  @Override
  public Book findByIsbn(Long isbn) {
    BookData bookData = jpaBookRepository.findByIsbn(isbn);
    Book book = Book.builder()
    .id(bookData.getId())
    .autor(bookData.getAutor())
    .name(bookData.getName())
    .isbn(bookData.getIsbn())
    .quantity(bookData.getQuantity())
    .build();
    
    return book;
  }

}
