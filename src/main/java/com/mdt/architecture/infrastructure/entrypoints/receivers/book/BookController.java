package com.mdt.architecture.infrastructure.entrypoints.receivers.book;

import com.mdt.architecture.domain.model.Book;
import com.mdt.architecture.domain.usescase.BookUseCase;

import com.mdt.architecture.infrastructure.entrypoints.receivers.book.dto.BookRequest;
import com.mdt.architecture.infrastructure.entrypoints.receivers.book.dto.BookResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/api/v1/books", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

   private Logger logger = LogManager.getLogger(BookController.class);

   private final BookUseCase bookUseCase;

   public BookController(final BookUseCase bookUseCase) {
      this.bookUseCase = bookUseCase;
   }

   @GetMapping(value = "/{isbn}", produces = MediaType.APPLICATION_JSON_VALUE)
   @ApiOperation(value = "get book by code")
   public BookResponse.BookDetailResponse getBook(final @PathVariable Long isbn) {
      return BookResponse.BookDetailResponse.fromModel(bookUseCase.findByIsbn(isbn));
   }

   @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
   @ApiOperation(value = "create new book")
   public BookResponse.BookDetailResponse createBook(
       final @RequestPart(name = "book") BookRequest.CreationBookRequest creationBookRequest,
       final @ApiParam(value = "Portada file")
       @RequestPart(name = "image", required = false) MultipartFile file) {

      Book result = bookUseCase.createBook(
          BookRequest.CreationBookRequest.toModel(creationBookRequest));

      return BookResponse.BookDetailResponse.fromModel(result);
   }

}
