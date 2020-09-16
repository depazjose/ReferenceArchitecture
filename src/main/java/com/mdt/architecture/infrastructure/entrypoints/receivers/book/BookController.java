package com.mdt.architecture.infrastructure.entrypoints.receivers.book;

import com.mdt.architecture.domain.model.Book;
import com.mdt.architecture.domain.shared.BookFoundException;
import com.mdt.architecture.domain.usescase.BookUseCase;

import com.mdt.architecture.infrastructure.entrypoints.receivers.book.dto.BookRequest.CreationBookRequest;
import com.mdt.architecture.infrastructure.entrypoints.receivers.book.dto.BookResponse.BookDetailResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

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
   public BookDetailResponse getBook(final @PathVariable Long isbn) {
      return BookDetailResponse.fromModel(bookUseCase.findByIsbn(isbn));
   }

   @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
   @ApiOperation(value ="get all books")
   public ResponseEntity<List<BookDetailResponse>> getAllBook() {

      return new ResponseEntity<List<BookDetailResponse>>(
              BookDetailResponse.fromModel(bookUseCase.findAll()), HttpStatus.ACCEPTED);
   }

   @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
   @ApiOperation(value = "create new book")
   public ResponseEntity<BookDetailResponse> createBook(
       final @Valid @RequestPart(name = "book") CreationBookRequest creationBookRequest,
       final @ApiParam(value = "Portada file")
       @RequestPart(name = "image", required = false) MultipartFile file) {


      if (Objects.nonNull(bookUseCase.findByIsbn(creationBookRequest.getIsbn()).getId())) {
        throw new BookFoundException(creationBookRequest.getIsbn());
      }

      Book result = bookUseCase.createBook(
          CreationBookRequest.toModel(creationBookRequest));

      return new ResponseEntity<>(BookDetailResponse.fromModel(result), HttpStatus.CREATED);
   }

}
