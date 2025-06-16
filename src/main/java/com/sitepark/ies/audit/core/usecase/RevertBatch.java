package com.sitepark.ies.audit.core.usecase;

import com.sitepark.ies.audit.core.port.AuditLogRepository;
import com.sitepark.ies.audit.core.service.RevertService;
import jakarta.inject.Inject;

public final class RevertBatch {

  private final AuditLogRepository repository;
  private final RevertService revertService;

  @Inject
  RevertBatch(AuditLogRepository repository, RevertService revertService) {
    this.repository = repository;
    this.revertService = revertService;
  }

  public void revertBatch(String batchId) {
    this.repository.getAuditLogsByBatchId(batchId).forEach(revertService::revert);
  }
}
