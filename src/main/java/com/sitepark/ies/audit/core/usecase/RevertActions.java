package com.sitepark.ies.audit.core.usecase;

import com.sitepark.ies.audit.core.domain.entity.AuditLog;
import com.sitepark.ies.audit.core.port.AuditLogRepository;
import com.sitepark.ies.audit.core.service.RevertService;
import com.sitepark.ies.sharedkernel.audit.AuditLogService;
import com.sitepark.ies.sharedkernel.audit.RevertRequest;
import jakarta.inject.Inject;
import java.time.Clock;
import java.time.Instant;
import java.util.List;

public final class RevertActions {

  private final AuditLogRepository repository;
  private final AuditLogService auditLogService;
  private final RevertService revertService;
  private final Clock clock;

  @Inject
  RevertActions(
      AuditLogRepository repository,
      AuditLogService auditLogService,
      RevertService revertService,
      Clock clock) {
    this.repository = repository;
    this.auditLogService = auditLogService;
    this.revertService = revertService;
    this.clock = clock;
  }

  public void revert(List<String> auditLogIds) {
    Instant now = Instant.now(this.clock);
    String batchId = auditLogIds.size() > 1 ? this.auditLogService.createAuditBatch(now) : null;
    repository
        .getByIds(auditLogIds)
        .forEach(
            auditLog -> {
              revert(auditLog, now, batchId);
            });
  }

  public void revert(AuditLog auditLog, Instant createAt, String batchId) {

    RevertRequest request =
        new RevertRequest(
            auditLog.entityType(),
            auditLog.entityId(),
            auditLog.action(),
            auditLog.oldData(),
            auditLog.newData(),
            createAt,
            batchId);

    revertService.revert(request);
  }
}
