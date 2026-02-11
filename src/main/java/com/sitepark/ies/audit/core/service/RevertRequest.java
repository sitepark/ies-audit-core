package com.sitepark.ies.audit.core.service;

import com.sitepark.ies.audit.core.domain.value.AuditLogTarget;
import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

public record RevertRequest(
    String id,
    AuditLogTarget target,
    String action,
    String backwardData,
    String forwardData,
    Instant changedAt,
    String parentId)
    implements Serializable {
  @Serial private static final long serialVersionUID = 1L;
}
