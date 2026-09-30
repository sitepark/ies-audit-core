package com.sitepark.ies.audit.core.port;

import com.sitepark.ies.audit.core.service.ReverseActionHandler;
import org.jspecify.annotations.Nullable;

@SuppressWarnings("PMD.ImplicitFunctionalInterface")
public interface ReversalHandlerRegistry {
  /** Returns the handler for the entity type, or the one for all entity types if it is {@code null}. */
  ReverseActionHandler getHandler(@Nullable String entityType);
}
