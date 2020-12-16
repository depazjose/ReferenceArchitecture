package com.mdt.architecture.applications.configuration;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

  private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES =
      new HashSet<String>(Arrays.asList("application/json"));

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
      .consumes(DEFAULT_PRODUCES_AND_CONSUMES)
      .produces(DEFAULT_PRODUCES_AND_CONSUMES)
      .select()
      .apis(
          RequestHandlerSelectors
              .basePackage(
                  "com.mdt.architecture.infrastructure.entrypoints"))
      .paths(PathSelectors.any())
      .build();
  }
}
