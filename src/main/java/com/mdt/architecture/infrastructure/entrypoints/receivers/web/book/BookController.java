package com.mdt.architecture.infrastructure.entrypoints.receivers.web.book;

import com.mdt.architecture.domain.model.book.Book;
import com.mdt.architecture.domain.shared.BookFoundException;
import com.mdt.architecture.domain.shared.BookNotFoundException;
import com.mdt.architecture.domain.usecase.BookUseCase;
import com.mdt.architecture.infrastructure.entrypoints.receivers.web.book.dto.BookRequest;
import com.mdt.architecture.infrastructure.entrypoints.receivers.web.book.dto.BookResponse.BookDetailResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;
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

@RestController
@RequestMapping(value = "/api/v1/books", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

  private static Logger logger = LogManager.getLogger(BookController.class);

  private final BookUseCase bookUseCase;

  public BookController(final BookUseCase bookUseCase) {
    this.bookUseCase = bookUseCase;
  }

  @GetMapping(value = "/{isbn}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "get book by code")
  public BookDetailResponse getBook(final @PathVariable Long isbn) {
    Book result = bookUseCase.findByIsbn(isbn);

    if (Objects.isNull(result)) {
      throw new BookNotFoundException(isbn, "ISBN");
    }

    return BookDetailResponse.fromModel(result);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "get all books")
  public ResponseEntity<List<BookDetailResponse>> getAllBook() {

    return new ResponseEntity<>(BookDetailResponse.fromModel(bookUseCase.findAll()), HttpStatus.ACCEPTED);
  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "create new book")
  public ResponseEntity<BookDetailResponse> createBook(
      final @Valid @RequestPart(name = "book") BookRequest.CreationBookRequest creationBookRequest,
      final @ApiParam(value = "Cover file")
      @RequestPart(name = "image", required = false) MultipartFile file) {

    if (Objects.nonNull(bookUseCase.findByIsbn(creationBookRequest.getIsbn()))) {
      throw new BookFoundException(creationBookRequest.getIsbn());
    }

    Book result = bookUseCase.createBook(BookRequest.CreationBookRequest.toModel(creationBookRequest));

    return new ResponseEntity<>(BookDetailResponse.fromModel(result), HttpStatus.CREATED);
  }

}
