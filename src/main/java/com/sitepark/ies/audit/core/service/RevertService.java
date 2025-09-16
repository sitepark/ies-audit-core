package com.sitepark.ies.audit.core.service;

import com.sitepark.ies.audit.core.port.ReversalHandlerRegistry;
import com.sitepark.ies.sharedkernel.audit.RevertRequest;
import jakarta.inject.Inject;

@SuppressWarnings("PMD.LawOfDemeter")
public class RevertService {

  private final ReversalHandlerRegistry handlerRegistry;

  @Inject
  public RevertService(ReversalHandlerRegistry handlerRegistry) {
    this.handlerRegistry = handlerRegistry;
  }

  public void revert(RevertRequest request) {
    this.handlerRegistry.getHandler(request.entityType()).revert(request);
  }
}
