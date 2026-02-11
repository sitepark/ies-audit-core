package com.sitepark.ies.audit.core.usecase;

import com.sitepark.ies.audit.core.domain.entity.AuditLog;
import com.sitepark.ies.audit.core.port.AuditLogRepository;
import com.sitepark.ies.sharedkernel.security.Authentication;
import com.sitepark.ies.sharedkernel.security.UserAuthentication;
import jakarta.inject.Inject;
import jakarta.inject.Provider;

public class CreateAuditLogUseCase {
  private final Provider<Authentication> authenticationProvider;

  private final AuditLogRepository repository;

  @Inject
  CreateAuditLogUseCase(
      Provider<Authentication> authenticationProvider, AuditLogRepository repository) {
    this.authenticationProvider = authenticationProvider;
    this.repository = repository;
  }

  public String createAuditLog(CreateAuditLogRequest request) {

    Authentication authentication = this.authenticationProvider.get();

    AuditLog.Builder builder =
        AuditLog.builder()
            .action(request.action())
            .entityType(request.target().type())
            .entityId(request.target().id())
            .entityName(request.target().name())
            .timestamp(request.changedAt())
            .authorityName(authentication.name())
            .parentId(request.parentId())
            .backwardData(request.backwardData())
            .forwardData(request.forwardData());

    if (authentication instanceof UserAuthentication userBasedAuthentication) {
      builder.userId(userBasedAuthentication.user().id());
    }

    return repository.createAuditLog(builder.build());
  }
}
