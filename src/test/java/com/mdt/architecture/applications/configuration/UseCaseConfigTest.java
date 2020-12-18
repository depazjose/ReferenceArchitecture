package com.mdt.architecture.applications.configuration;

import com.mdt.architecture.domain.model.book.gateway.BookRepository;
import com.mdt.architecture.domain.model.event.gateway.EventSender;
import com.mdt.architecture.domain.usescase.BookUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UseCaseConfigTest {

  private UseCaseConfig configuration = new UseCaseConfig();

  @Mock
  private BookRepository bookRepository;

  @Mock
  private EventSender eventSender;

  @Test
  void shouldCreateBookUseCase() {
    final BookUseCaseImpl bookUseCase = configuration.bookUseCaseImpl(bookRepository, eventSender);
  }
}
