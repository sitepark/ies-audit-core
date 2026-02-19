package com.sitepark.ies.audit.core.domain.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.Serial;
import org.junit.jupiter.api.Test;

class RevertFailedExceptionTest {

  private static final class TestRevertFailedException extends RevertFailedException {

    @Serial private static final long serialVersionUID = 1L;

    TestRevertFailedException() {
      super();
    }

    TestRevertFailedException(String msg) {
      super(msg);
    }
  }

  @Test
  void testDefaultConstructorHasNullMessage() {
    RevertFailedException exception = new TestRevertFailedException();
    assertNull(exception.getMessage(), "Default constructor should produce null message");
  }

  @Test
  void testMessageConstructor() {
    RevertFailedException exception = new TestRevertFailedException("revert failed");
    assertEquals("revert failed", exception.getMessage(), "Unexpected exception message");
  }
}
