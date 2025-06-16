package com.sitepark.ies.audit.core.port;

import java.util.List;

public interface ReversalHandlerRegistry {
  List<ReversibleAuditHandler> getAll();
}
