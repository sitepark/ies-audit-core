package com.sitepark.ies.audit.core.usecase;

import com.sitepark.ies.audit.core.domain.entity.AuditLog;
import com.sitepark.ies.audit.core.port.AuditLogRepository;
import com.sitepark.ies.audit.core.usecase.query.Query;
import com.sitepark.ies.audit.core.usecase.query.Result;
import jakarta.inject.Inject;

public final class SearchAuditLogs {

  private final AuditLogRepository repository;

  @Inject
  SearchAuditLogs(AuditLogRepository repository) {
    this.repository = repository;
  }

  public Result<AuditLog> searchAuditLogs(Query query) {

    return this.repository.search(query);
  }
}
