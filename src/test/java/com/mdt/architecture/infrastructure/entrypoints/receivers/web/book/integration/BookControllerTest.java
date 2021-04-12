package com.mdt.architecture.infrastructure.entrypoints.receivers.web.book.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.mdt.architecture.infrastructure.adapters.database.BookData;
import com.zaxxer.hikari.HikariDataSource;
import java.util.Objects;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class BookControllerTest {

  @Autowired
  private MongoTemplate mongoTemplate;

  @Autowired
  private MockMvc mvc;

  @Autowired
  private static HikariDataSource hikariDataSource;

  @AfterClass
  public static void closeConnection() {
    if (Objects.nonNull(hikariDataSource)) {
      hikariDataSource.close();
    }
  }

  @Test
  void shouldGetBook() throws Exception {
    BookData bookData = new BookData();
    bookData.setIsbn(123456L);
    bookData.setAuthor("Author");
    bookData.setStatus("OPEN");
    bookData.setProperties("{}");
    bookData.setAvailable(true);
    mongoTemplate.createCollection("book_data");
    mongoTemplate.insert(bookData);
    String url = "/api/v1/books/123456";

    mvc.perform(get(url)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json("{'isbn':123456}"));
  }

  @Test
  void shouldGetBookNotFound() throws Exception {
    String url = "/api/v1/books/1234560";

    mvc.perform(get(url)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

}
