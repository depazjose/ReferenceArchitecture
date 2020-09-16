package com.mdt.architecture.applications.configuration;

import com.mdt.architecture.domain.model.gateway.BookRepository;
import com.mdt.architecture.domain.usescase.BookUseCaseImpl;

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
