package com.sitepark.ies.audit.core.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sitepark.ies.audit.core.domain.value.AuditLogTarget;
import com.sitepark.ies.audit.core.port.AuditLogRepository;
import com.sitepark.ies.sharedkernel.security.Authentication;
import com.sitepark.ies.sharedkernel.security.ServiceAuthentication;
import com.sitepark.ies.sharedkernel.security.User;
import com.sitepark.ies.sharedkernel.security.UserAuthentication;
import jakarta.inject.Provider;
import java.time.Instant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreateAuditLogUseCaseTest {

  private static final Instant TIMESTAMP = Instant.parse("2024-01-01T00:00:00Z");

  private static final AuditLogTarget TARGET = new AuditLogTarget("User", "entity-1", "Test User");

  private static final CreateAuditLogRequest REQUEST =
      new CreateAuditLogRequest(
          TARGET, "CREATE", "{\"old\":null}", "{\"new\":\"value\"}", TIMESTAMP, "parent-1");

  private static final Authentication SERVICE_AUTH =
      ServiceAuthentication.builder().name("service-name").build();

  private static final User USER =
      User.builder().id("user-123").username("testuser").lastName("Test").build();

  private static final UserAuthentication USER_AUTH =
      UserAuthentication.builder().user(USER).build();

  private Provider<Authentication> authenticationProvider;
  private AuditLogRepository repository;
  private CreateAuditLogUseCase useCase;

  @BeforeEach
  void setUp() {
    this.authenticationProvider = mock();
    this.repository = mock();
    when(this.authenticationProvider.get()).thenReturn(SERVICE_AUTH);
    this.useCase = new CreateAuditLogUseCase(this.authenticationProvider, this.repository);
  }

  @Test
  void testCreateAuditLogReturnsId() {
    when(this.repository.createAuditLog(any())).thenReturn("new-id");
    String id = this.useCase.createAuditLog(REQUEST);
    assertEquals("new-id", id, "Unexpected audit log id returned from createAuditLog");
  }

  @Test
  void testCreateAuditLogCallsRepository() {
    this.useCase.createAuditLog(REQUEST);
    verify(this.repository).createAuditLog(any());
  }

  @Test
  void testCreateAuditLogSetsEntityType() {
    this.useCase.createAuditLog(REQUEST);
    verify(this.repository).createAuditLog(argThat(log -> "User".equals(log.entityType())));
  }

  @Test
  void testCreateAuditLogSetsEntityId() {
    this.useCase.createAuditLog(REQUEST);
    verify(this.repository).createAuditLog(argThat(log -> "entity-1".equals(log.entityId())));
  }

  @Test
  void testCreateAuditLogSetsEntityName() {
    this.useCase.createAuditLog(REQUEST);
    verify(this.repository).createAuditLog(argThat(log -> "Test User".equals(log.entityName())));
  }

  @Test
  void testCreateAuditLogSetsAction() {
    this.useCase.createAuditLog(REQUEST);
    verify(this.repository).createAuditLog(argThat(log -> "CREATE".equals(log.action())));
  }

  @Test
  void testCreateAuditLogSetsTimestamp() {
    this.useCase.createAuditLog(REQUEST);
    verify(this.repository).createAuditLog(argThat(log -> TIMESTAMP.equals(log.timestamp())));
  }

  @Test
  void testCreateAuditLogSetsAuthorityName() {
    this.useCase.createAuditLog(REQUEST);
    verify(this.repository)
        .createAuditLog(argThat(log -> "service-name".equals(log.authorityName())));
  }

  @Test
  void testCreateAuditLogSetsParentId() {
    this.useCase.createAuditLog(REQUEST);
    verify(this.repository).createAuditLog(argThat(log -> "parent-1".equals(log.parentId())));
  }

  @Test
  void testCreateAuditLogWithUserAuthSetsUserId() {
    when(this.authenticationProvider.get()).thenReturn(USER_AUTH);
    this.useCase.createAuditLog(REQUEST);
    verify(this.repository).createAuditLog(argThat(log -> "user-123".equals(log.userId())));
  }

  @Test
  void testCreateAuditLogWithNonUserAuthDoesNotSetUserId() {
    this.useCase.createAuditLog(REQUEST);
    verify(this.repository).createAuditLog(argThat(log -> log.userId() == null));
  }
}
