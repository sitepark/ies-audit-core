package com.sitepark.ies.audit.core.port;

import com.sitepark.ies.audit.core.service.ReverseActionHandler;

@SuppressWarnings("PMD.ImplicitFunctionalInterface")
public interface ReversalHandlerRegistry {
  ReverseActionHandler getHandler(String entityType);
}
