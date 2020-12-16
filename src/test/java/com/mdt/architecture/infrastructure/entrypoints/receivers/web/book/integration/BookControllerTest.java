package com.mdt.architecture.infrastructure.entrypoints.receivers.web.book.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.zaxxer.hikari.HikariDataSource;
import java.util.Objects;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BookControllerTest {

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
  public void shouldGetBook() throws Exception {
    String url = "/api/v1/books/123456";

    mvc.perform(get(url)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json("{'isbn':123456}"));
  }

  @Test
  public void shouldGetBookNotFound() throws Exception {
    String url = "/api/v1/books/1234560";

    mvc.perform(get(url)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

}
