package com.sitepark.ies.audit.core.usecase;

import com.sitepark.ies.audit.core.port.AuditLogRepository;
import com.sitepark.ies.audit.core.service.RevertService;
import jakarta.inject.Inject;
import java.util.List;

public final class RevertActions {

  private final AuditLogRepository repository;
  private final RevertService revertService;

  @Inject
  RevertActions(AuditLogRepository repository, RevertService revertService) {
    this.repository = repository;
    this.revertService = revertService;
  }

  public void revert(List<String> auditLogIds) {
    auditLogIds.stream().map(repository::getAuditLog).forEach(revertService::revert);
  }
}
