package com.sitepark.ies.audit.core.service;

import com.sitepark.ies.audit.core.domain.value.AuditLogTarget;
import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import org.jspecify.annotations.Nullable;

public record RevertRequest(
    String id,
    AuditLogTarget target,
    String action,
    @Nullable String backwardData,
    @Nullable String forwardData,
    Instant changedAt,
    @Nullable String parentId)
    implements Serializable {
  @Serial private static final long serialVersionUID = 1L;
}
