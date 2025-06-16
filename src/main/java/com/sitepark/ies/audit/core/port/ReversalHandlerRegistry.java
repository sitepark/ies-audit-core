package com.sitepark.ies.audit.core.port;

import java.util.List;

@SuppressWarnings("PMD.ImplicitFunctionalInterface")
public interface ReversalHandlerRegistry {
  List<ReversibleAuditHandler> getAll();
}
