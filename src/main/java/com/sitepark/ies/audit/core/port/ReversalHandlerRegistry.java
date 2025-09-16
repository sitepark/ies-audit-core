package com.sitepark.ies.audit.core.port;

import com.sitepark.ies.sharedkernel.audit.ReversibleAuditHandler;

@SuppressWarnings("PMD.ImplicitFunctionalInterface")
public interface ReversalHandlerRegistry {
  ReversibleAuditHandler getHandler(String entityType);
}
