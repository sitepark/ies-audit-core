package com.sitepark.ies.audit.core.usecase;

import com.sitepark.ies.audit.core.domain.entity.AuditLog;
import com.sitepark.ies.audit.core.port.AuditLogRepository;
import com.sitepark.ies.audit.core.port.AuthorizationService;
import com.sitepark.ies.audit.core.usecase.query.Query;
import com.sitepark.ies.audit.core.usecase.query.Result;
import com.sitepark.ies.sharedkernel.security.AccessDeniedException;
import jakarta.inject.Inject;

public final class SearchAuditLogsUseCase {

  private final AuditLogRepository repository;

  private final AuthorizationService authorizationService;

  @Inject
  SearchAuditLogsUseCase(AuditLogRepository repository, AuthorizationService authorizationService) {
    this.repository = repository;
    this.authorizationService = authorizationService;
  }

  public Result<AuditLog> searchAuditLogs(Query query) {
    if (!this.authorizationService.isAuditLogsReadable()) {
      throw new AccessDeniedException("Access denied: Audit logs are not readable");
    }
    return this.repository.search(query);
  }
}
