package com.mdt.architecture.infrastructure.schedulers;

import com.mdt.architecture.domain.usescase.BookUseCase;
import com.mdt.architecture.infrastructure.adapters.database.book.BookRepositoryAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ActiveItemsSchedulerImplTest {

  @Mock
  private BookUseCase bookUseCase;

  @Mock
  private BookRepositoryAdapter bookRepositoryAdapter;

  @InjectMocks
  private ActiveItemsSchedulerImpl activeItemsScheduler;

  @Test
  void shouldProcessTask() {
    activeItemsScheduler.processTaskActiveItems();
    Mockito.verify(bookRepositoryAdapter, Mockito.times(1))
        .findAllByStatus("SCHEDULED");
  }
}
