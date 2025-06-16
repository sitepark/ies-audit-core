package com.sitepark.ies.audit.core.service;

import com.sitepark.ies.audit.core.domain.entity.AuditLog;
import com.sitepark.ies.audit.core.port.ReversalHandlerRegistry;

public class RevertService {

  private final ReversalHandlerRegistry handlerRegistry;

  public RevertService(ReversalHandlerRegistry handlerRegistry) {
    this.handlerRegistry = handlerRegistry;
  }

  public void revert(AuditLog auditLog) {
    this.handlerRegistry.getAll().stream()
        .filter(h -> h.supports(auditLog.getEntityType().getCode(), auditLog.getAction().getCode()))
        .findFirst()
        .orElseThrow(() -> new UnsupportedOperationException("No handler found"))
        .revert(auditLog);
  }
}
