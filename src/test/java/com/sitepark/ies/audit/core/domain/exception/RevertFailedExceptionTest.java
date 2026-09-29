package com.sitepark.ies.audit.core.domain.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.Serial;
import org.junit.jupiter.api.Test;

class RevertFailedExceptionTest {

  private static final class StubRevertFailedException extends RevertFailedException {

    @Serial private static final long serialVersionUID = 1L;

    StubRevertFailedException() {
      super();
    }

    StubRevertFailedException(String msg) {
      super(msg);
    }
  }

  @Test
  void testDefaultConstructorHasNullMessage() {
    RevertFailedException exception = new StubRevertFailedException();
    assertNull(exception.getMessage(), "Default constructor should produce null message");
  }

  @Test
  void testMessageConstructor() {
    RevertFailedException exception = new StubRevertFailedException("revert failed");
    assertEquals("revert failed", exception.getMessage(), "Unexpected exception message");
  }
}
