package com.sitepark.ies.audit.core.usecase;

import java.util.List;

public record RevertActionsRequest(List<String> auditLogIds, String auditParentId) {
  public RevertActionsRequest {
    auditLogIds = List.copyOf(auditLogIds);
  }

  @Override
  public List<String> auditLogIds() {
    return List.copyOf(auditLogIds);
  }
}
