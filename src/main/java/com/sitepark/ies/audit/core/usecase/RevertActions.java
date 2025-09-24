package com.sitepark.ies.audit.core.usecase;

import com.sitepark.ies.audit.core.domain.entity.AuditLog;
import com.sitepark.ies.audit.core.port.AuditLogRepository;
import com.sitepark.ies.audit.core.service.RevertService;
import com.sitepark.ies.sharedkernel.audit.AuditLogService;
import com.sitepark.ies.sharedkernel.audit.CreateAuditLogRequest;
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
    String batchId = auditLogIds.size() > 1 ? this.createBatchRemoveLog(now) : null;
    repository
        .getByIds(auditLogIds)
        .forEach(
            auditLog -> {
              revert(auditLog, now, batchId);
            });
  }

  private String createBatchRemoveLog(Instant now) {
    return this.auditLogService.createAuditLog(
        new CreateAuditLogRequest(null, null, null, "REVERT_BATCH", null, null, now, null));
  }

  public void revert(AuditLog auditLog, Instant createAt, String parentId) {

    RevertRequest request =
        new RevertRequest(
            auditLog.id(),
            auditLog.entityType(),
            auditLog.entityId(),
            auditLog.entityName(),
            auditLog.action(),
            auditLog.oldData(),
            auditLog.newData(),
            createAt,
            parentId);

    revertService.revert(request);
  }
}
