package com.sitepark.ies.audit.core.domain.exception;

import java.io.Serial;

/**
 * The <code>UserRepositoryException</code> is the base class for all exceptions related to user
 * repository operations. It serves as the root exception for handling repository errors and
 * unexpected conditions.
 */
public abstract class AuditException extends RuntimeException {

  @Serial private static final long serialVersionUID = 1L;

  public AuditException() {}

  public AuditException(String message) {
    super(message);
  }

  public AuditException(String message, Throwable t) {
    super(message, t);
  }
}
