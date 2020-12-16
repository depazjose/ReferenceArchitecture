package com.mdt.architecture.infrastructure.schedulers;

import com.mdt.architecture.domain.usescase.BookUseCase;
import com.mdt.architecture.infrastructure.adapters.database.BookRepositoryAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
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
