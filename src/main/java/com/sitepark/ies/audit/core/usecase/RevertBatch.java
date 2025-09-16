package com.sitepark.ies.audit.core.usecase;

import com.sitepark.ies.audit.core.port.AuditLogRepository;
import jakarta.inject.Inject;
import java.util.List;

public final class RevertBatch {

  private final AuditLogRepository repository;
  RevertActions revertActionsUseCase;

  @Inject
  RevertBatch(AuditLogRepository repository, RevertActions revertActionsUseCase) {
    this.repository = repository;
    this.revertActionsUseCase = revertActionsUseCase;
  }

  public void revertBatch(String batchId) {
    List<String> auditLogIds = this.repository.getAuditLogIdsByBatchId(batchId);
    if (auditLogIds.isEmpty()) {
      return;
    }
    this.revertActionsUseCase.revert(auditLogIds);
  }
}
