package com.mdt.architecture.infrastructure.schedulers;

import com.mdt.architecture.domain.usescase.BookUseCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "inventory.active-items.enabled")
public class ActiveItemsSchedulerImpl {

    private Logger logger = LogManager.getLogger(ActiveItemsSchedulerImpl.class);

    private final BookUseCase bookUseCase;

    public ActiveItemsSchedulerImpl(final BookUseCase bookUseCase) {
        this.bookUseCase = bookUseCase;
    }

    @Scheduled(cron = "${inventory.active-items.cron-period}")
    public void processTaskActiveItems() {
        logger.info("Executing..");
        bookUseCase.findAllByStatus("SCHEDULED").stream().forEach(book -> logger.info(book.toString()));
    }

}
