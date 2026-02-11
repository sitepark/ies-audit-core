package com.sitepark.ies.audit.core.usecase;

import com.sitepark.ies.audit.core.domain.entity.AuditLog;
import com.sitepark.ies.audit.core.port.AuditLogRepository;
import com.sitepark.ies.audit.core.port.AuthorizationService;
import com.sitepark.ies.audit.core.usecase.query.filter.Filter;
import com.sitepark.ies.sharedkernel.security.AccessDeniedException;
import jakarta.inject.Inject;
import java.util.List;

public final class GetAllAuditLogsUseCase {

  private final AuditLogRepository repository;

  private final AuthorizationService authorizationService;

  @Inject
  GetAllAuditLogsUseCase(AuditLogRepository repository, AuthorizationService authorizationService) {
    this.repository = repository;
    this.authorizationService = authorizationService;
  }

  public List<AuditLog> getAllAuditLogs(Filter filter) {
    if (!this.authorizationService.isAuditLogsReadable()) {
      throw new AccessDeniedException("Access denied: Audit logs are not readable");
    }
    return this.repository.getAll(filter);
  }
}
