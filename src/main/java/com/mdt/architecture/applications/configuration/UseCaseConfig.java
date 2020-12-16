package com.mdt.architecture.applications.configuration;

import com.mdt.architecture.domain.model.book.gateway.BookRepository;
import com.mdt.architecture.domain.model.event.gateway.EventSender;
import com.mdt.architecture.domain.usescase.BookUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

  @Bean
  public BookUseCaseImpl bookUseCaseImpl(
      final BookRepository bookRepository,
      final EventSender eventSender) {
    return new BookUseCaseImpl(bookRepository, eventSender);
  }
}
