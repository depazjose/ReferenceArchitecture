package com.mdt.architecture.applications;

import com.mdt.architecture.MainApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MainApplicationTest {

  @Test
  void main() {
    MainApplication.main(new String[] {});
  }
}

