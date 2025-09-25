package com.sitepark.ies.audit.core.domain.exception;

import com.sitepark.ies.sharedkernel.domain.DomainException;
import java.io.Serial;

/** Exception that is thrown when an audit revert operation fails. */
public abstract class RevertFailedException extends DomainException {

  @Serial private static final long serialVersionUID = 1L;

  public RevertFailedException() {}

  public RevertFailedException(String msg) {
    super(msg);
  }
}
