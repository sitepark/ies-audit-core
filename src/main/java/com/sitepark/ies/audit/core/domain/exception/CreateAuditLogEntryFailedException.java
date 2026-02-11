package com.sitepark.ies.audit.core.domain.exception;

import com.sitepark.ies.audit.core.domain.value.AuditLogTarget;
import com.sitepark.ies.sharedkernel.domain.DomainException;
import java.io.Serial;

/**
 * The <code>CreateAuditLogEntryFailedException</code> exception is thrown when the creation of an
 * audit log entry fails.
 */
public class CreateAuditLogEntryFailedException extends DomainException {

  @Serial private static final long serialVersionUID = 1L;

  private final AuditLogTarget target;

  public CreateAuditLogEntryFailedException(AuditLogTarget target, Throwable t) {
    super(t);
    this.target = target;
  }

  public AuditLogTarget getTarget() {
    return this.target;
  }

  @Override
  public String getMessage() {
    return "Create audit-log entry failed: " + this.target;
  }
}
