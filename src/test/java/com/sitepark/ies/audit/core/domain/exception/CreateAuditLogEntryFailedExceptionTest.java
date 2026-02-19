package com.sitepark.ies.audit.core.domain.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sitepark.ies.audit.core.domain.value.AuditLogTarget;
import org.junit.jupiter.api.Test;

class CreateAuditLogEntryFailedExceptionTest {

  private static final AuditLogTarget TARGET = new AuditLogTarget("User", "entity-1", "Test User");

  @Test
  void testGetTarget() {
    CreateAuditLogEntryFailedException exception =
        new CreateAuditLogEntryFailedException(TARGET, new RuntimeException("cause"));
    assertEquals(TARGET, exception.getTarget(), "Unexpected target");
  }

  @Test
  void testGetMessage() {
    CreateAuditLogEntryFailedException exception =
        new CreateAuditLogEntryFailedException(TARGET, new RuntimeException("cause"));
    assertEquals(
        "Create audit-log entry failed: " + TARGET,
        exception.getMessage(),
        "Unexpected exception message");
  }
}
