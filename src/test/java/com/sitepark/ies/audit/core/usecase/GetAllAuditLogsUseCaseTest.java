package com.sitepark.ies.audit.core.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sitepark.ies.audit.core.domain.entity.AuditLog;
import com.sitepark.ies.audit.core.port.AuditLogRepository;
import com.sitepark.ies.audit.core.port.AuthorizationService;
import com.sitepark.ies.audit.core.usecase.query.filter.Filter;
import com.sitepark.ies.sharedkernel.security.AccessDeniedException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GetAllAuditLogsUseCaseTest {

  private AuditLogRepository repository;
  private AuthorizationService authorizationService;
  private GetAllAuditLogsUseCase useCase;

  @BeforeEach
  void setUp() {
    this.repository = mock();
    this.authorizationService = mock();
    this.useCase = new GetAllAuditLogsUseCase(this.repository, this.authorizationService);
  }

  @Test
  void testGetAllAuditLogsThrowsWhenNotAuthorized() {
    when(this.authorizationService.isAuditLogsReadable()).thenReturn(false);
    assertThrows(
        AccessDeniedException.class,
        () -> this.useCase.getAllAuditLogs(mock(Filter.class)),
        "Should throw AccessDeniedException when audit logs are not readable");
  }

  @Test
  void testGetAllAuditLogsCallsRepository() {
    Filter filter = mock(Filter.class);
    when(this.authorizationService.isAuditLogsReadable()).thenReturn(true);
    when(this.repository.getAll(filter)).thenReturn(List.of());

    this.useCase.getAllAuditLogs(filter);

    verify(this.repository).getAll(filter);
  }

  @Test
  void testGetAllAuditLogsReturnsRepositoryResult() {
    Filter filter = mock(Filter.class);
    List<AuditLog> expected = List.of(AuditLog.builder().id("1").build());
    when(this.authorizationService.isAuditLogsReadable()).thenReturn(true);
    when(this.repository.getAll(filter)).thenReturn(expected);

    List<AuditLog> result = this.useCase.getAllAuditLogs(filter);

    assertEquals(expected, result, "Unexpected result from getAllAuditLogs");
  }
}
