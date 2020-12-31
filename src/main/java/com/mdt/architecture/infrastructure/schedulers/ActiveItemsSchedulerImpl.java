package com.mdt.architecture.infrastructure.schedulers;

import com.mdt.architecture.domain.model.book.Book;
import com.mdt.architecture.domain.usescase.BookUseCase;
import com.mdt.architecture.infrastructure.adapters.database.book.BookRepositoryAdapter;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.util.Pair;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@ConditionalOnProperty(name = "inventory.active-items.enabled")
public class ActiveItemsSchedulerImpl {

  private Logger logger = LogManager.getLogger(ActiveItemsSchedulerImpl.class);

  private final BookUseCase bookUseCase;

  private final BookRepositoryAdapter bookRepositoryAdapter;

  public ActiveItemsSchedulerImpl(final BookUseCase bookUseCase, final BookRepositoryAdapter bookRepositoryAdapter) {
    this.bookUseCase = bookUseCase;
    this.bookRepositoryAdapter = bookRepositoryAdapter;
  }

  @Scheduled(cron = "${inventory.active-items.cron-period}")
  @SchedulerLock(name = "scheduledUpdateStatus", lockAtMostFor = "14m", lockAtLeastFor = "59s")
  @Transactional
  public void processTaskActiveItems() {
    logger.info("Executing..");
    LocalDateTime localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

    List<Book> books = bookRepositoryAdapter.findAllByStatus("SCHEDULED");

    List<Book> booksToUpdate = books.stream()
        .filter(book -> Objects.nonNull(book.getStartSaleDate()))
        .filter(book -> {
          LocalDateTime startDate = book.getStartSaleDate().truncatedTo(ChronoUnit.MINUTES);
          return startDate.compareTo(localDateTime) <= 0;
        })
        .collect(Collectors.toList());

    List<Book> bookUpdateFAiled = booksToUpdate.stream()
        .map(book -> Pair.of(bookRepositoryAdapter.updateStatus(book.getId(), "OPEN"), book))
        .peek(pair -> {
          if (pair.getFirst() == 1) {
            logger.info(pair.getSecond().getId());
          }
        })
        .filter(result -> result.getFirst() != 1)
        .map(Pair::getSecond)
        .collect(Collectors.toList());

    bookUpdateFAiled.stream().forEach(book -> {
      String message = String.format("Book with ID %s and ISBN %s was not OPEN.", book.getId(), book.getIsbn());
      logger.error(message);
    });
    logger.info(" End");
  }
}
