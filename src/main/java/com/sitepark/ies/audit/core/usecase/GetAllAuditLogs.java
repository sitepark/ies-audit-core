package com.sitepark.ies.audit.core.usecase;

import com.sitepark.ies.audit.core.domain.entity.AuditLog;
import com.sitepark.ies.audit.core.port.AccessControl;
import com.sitepark.ies.audit.core.port.AuditLogRepository;
import com.sitepark.ies.audit.core.usecase.query.filter.Filter;
import com.sitepark.ies.sharedkernel.security.AccessDeniedException;
import jakarta.inject.Inject;
import java.util.List;

public final class GetAllAuditLogs {

  private final AuditLogRepository repository;

  private final AccessControl accessControl;

  @Inject
  GetAllAuditLogs(AuditLogRepository repository, AccessControl accessControl) {
    this.repository = repository;
    this.accessControl = accessControl;
  }

  public List<AuditLog> getAllAuditLogs(Filter filter) {
    if (!this.accessControl.isAuditLogsReadable()) {
      throw new AccessDeniedException("Access denied: Audit logs are not readable");
    }
    return this.repository.getAll(filter);
  }
}
