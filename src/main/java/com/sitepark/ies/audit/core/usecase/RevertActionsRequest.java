package com.sitepark.ies.audit.core.usecase;

import java.util.List;
import org.jspecify.annotations.Nullable;

public record RevertActionsRequest(List<String> auditLogIds, @Nullable String auditParentId) {
  public RevertActionsRequest {
    auditLogIds = List.copyOf(auditLogIds);
  }

  @Override
  public List<String> auditLogIds() {
    return List.copyOf(auditLogIds);
  }
}
