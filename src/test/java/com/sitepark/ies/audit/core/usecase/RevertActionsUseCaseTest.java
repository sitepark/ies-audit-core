package com.sitepark.ies.audit.core.usecase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sitepark.ies.audit.core.domain.entity.AuditLog;
import com.sitepark.ies.audit.core.port.AuditLogRepository;
import com.sitepark.ies.audit.core.port.ReversalHandlerRegistry;
import com.sitepark.ies.audit.core.service.ReverseActionHandler;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RevertActionsUseCaseTest {

  private static final Instant FIXED_INSTANT = Instant.parse("2024-01-01T00:00:00Z");

  private static final AuditLog AUDIT_LOG =
      AuditLog.builder()
          .id("log-1")
          .entityType("User")
          .entityId("entity-1")
          .entityName("Test User")
          .action("CREATE")
          .backwardData("{\"old\":null}")
          .forwardData("{\"new\":\"value\"}")
          .build();

  private AuditLogRepository repository;
  private ReversalHandlerRegistry handlerRegistry;
  private ReverseActionHandler handler;
  private RevertActionsUseCase useCase;

  @BeforeEach
  void setUp() {
    this.repository = mock();
    this.handlerRegistry = mock();
    this.handler = mock();
    when(this.handlerRegistry.getHandler(any())).thenReturn(this.handler);
    when(this.repository.getByIds(any())).thenReturn(List.of(AUDIT_LOG));
    this.useCase =
        new RevertActionsUseCase(
            this.repository, this.handlerRegistry, Clock.fixed(FIXED_INSTANT, ZoneOffset.UTC));
  }

  @Test
  void testRevertGetsAuditLogsByIds() {
    List<String> ids = List.of("log-1");
    RevertActionsRequest request = new RevertActionsRequest(ids, "parent-1");

    this.useCase.revert(request);

    verify(this.repository).getByIds(ids);
  }

  @Test
  void testRevertLooksUpHandlerByEntityType() {
    RevertActionsRequest request = new RevertActionsRequest(List.of("log-1"), "parent-1");

    this.useCase.revert(request);

    verify(this.handlerRegistry).getHandler("User");
  }

  @Test
  void testRevertCallsHandlerRevert() {
    RevertActionsRequest request = new RevertActionsRequest(List.of("log-1"), "parent-1");

    this.useCase.revert(request);

    verify(this.handler).revert(any());
  }

  @Test
  void testRevertPassesCorrectAuditLogId() {
    RevertActionsRequest request = new RevertActionsRequest(List.of("log-1"), "parent-1");

    this.useCase.revert(request);

    verify(this.handler).revert(argThat(req -> "log-1".equals(req.id())));
  }

  @Test
  void testRevertPassesCorrectEntityType() {
    RevertActionsRequest request = new RevertActionsRequest(List.of("log-1"), "parent-1");

    this.useCase.revert(request);

    verify(this.handler).revert(argThat(req -> "User".equals(req.target().type())));
  }

  @Test
  void testRevertPassesCorrectParentId() {
    RevertActionsRequest request = new RevertActionsRequest(List.of("log-1"), "parent-1");

    this.useCase.revert(request);

    verify(this.handler).revert(argThat(req -> "parent-1".equals(req.parentId())));
  }

  @Test
  void testRevertPassesCorrectTimestamp() {
    RevertActionsRequest request = new RevertActionsRequest(List.of("log-1"), "parent-1");

    this.useCase.revert(request);

    verify(this.handler).revert(argThat(req -> FIXED_INSTANT.equals(req.changedAt())));
  }

  @Test
  void testRevertWithAuditLogDirectlyCallsHandler() {
    this.useCase.revert(AUDIT_LOG, FIXED_INSTANT, "parent-1");
    verify(this.handler).revert(any());
  }

  @Test
  void testRevertWithAuditLogPassesEntityId() {
    this.useCase.revert(AUDIT_LOG, FIXED_INSTANT, "parent-1");
    verify(this.handler).revert(argThat(req -> "entity-1".equals(req.target().id())));
  }
}
