package com.sitepark.ies.audit.core.port;

import com.sitepark.ies.audit.core.domain.entity.AuditLog;

public interface ReversibleAuditHandler {
  boolean supports(String entityType, String action);

  void revert(AuditLog autoLog);
}
