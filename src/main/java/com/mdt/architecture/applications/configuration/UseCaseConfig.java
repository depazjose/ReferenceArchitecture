package com.mdt.architecture.applications.configuration;

import com.mdt.architecture.domain.model.book.gateway.BookRepository;
import com.mdt.architecture.domain.usecase.BookUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

  @Bean
  public BookUseCaseImpl bookUseCaseImpl(
      final BookRepository bookRepository) {
    return new BookUseCaseImpl(bookRepository);
  }
}
