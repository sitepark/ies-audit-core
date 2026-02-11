package com.sitepark.ies.audit.core.usecase;

import com.sitepark.ies.audit.core.domain.entity.AuditLog;
import com.sitepark.ies.audit.core.domain.value.AuditLogTarget;
import com.sitepark.ies.audit.core.port.AuditLogRepository;
import com.sitepark.ies.audit.core.port.ReversalHandlerRegistry;
import com.sitepark.ies.audit.core.service.RevertRequest;
import jakarta.inject.Inject;
import java.time.Clock;
import java.time.Instant;

public final class RevertActionsUseCase {

  private final AuditLogRepository repository;
  private final ReversalHandlerRegistry handlerRegistry;
  private final Clock clock;

  @Inject
  RevertActionsUseCase(
      AuditLogRepository repository, ReversalHandlerRegistry handlerRegistry, Clock clock) {
    this.repository = repository;
    this.handlerRegistry = handlerRegistry;
    this.clock = clock;
  }

  public void revert(RevertActionsRequest request) {
    Instant timestamp = Instant.now(this.clock);
    repository
        .getByIds(request.auditLogIds())
        .forEach(
            auditLog -> {
              revert(auditLog, timestamp, request.auditParentId());
            });
  }

  public void revert(AuditLog auditLog, Instant createAt, String parentId) {

    RevertRequest request =
        new RevertRequest(
            auditLog.id(),
            new AuditLogTarget(auditLog.entityType(), auditLog.entityId(), auditLog.entityName()),
            auditLog.action(),
            auditLog.backwardData(),
            auditLog.forwardData(),
            createAt,
            parentId);

    this.handlerRegistry.getHandler(request.target().type()).revert(request);
  }
}
