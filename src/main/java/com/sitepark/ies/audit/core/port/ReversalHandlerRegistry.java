package com.sitepark.ies.audit.core.port;

import com.sitepark.ies.sharedkernel.audit.ReverseActionHandler;

@SuppressWarnings("PMD.ImplicitFunctionalInterface")
public interface ReversalHandlerRegistry {
  ReverseActionHandler getHandler(String entityType);
}
