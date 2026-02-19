package com.sitepark.ies.audit.core.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sitepark.ies.audit.core.domain.entity.AuditLog;
import com.sitepark.ies.audit.core.port.AuditLogRepository;
import com.sitepark.ies.audit.core.port.AuthorizationService;
import com.sitepark.ies.audit.core.usecase.query.Query;
import com.sitepark.ies.audit.core.usecase.query.Result;
import com.sitepark.ies.sharedkernel.security.AccessDeniedException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SearchAuditLogsUseCaseTest {

  private AuditLogRepository repository;
  private AuthorizationService authorizationService;
  private SearchAuditLogsUseCase useCase;

  @BeforeEach
  void setUp() {
    this.repository = mock();
    this.authorizationService = mock();
    this.useCase = new SearchAuditLogsUseCase(this.repository, this.authorizationService);
  }

  @Test
  void testSearchAuditLogsThrowsWhenNotAuthorized() {
    when(this.authorizationService.isAuditLogsReadable()).thenReturn(false);
    assertThrows(
        AccessDeniedException.class,
        () -> this.useCase.searchAuditLogs(Query.builder().build()),
        "Should throw AccessDeniedException when audit logs are not readable");
  }

  @Test
  void testSearchAuditLogsCallsRepository() {
    Query query = Query.builder().build();
    when(this.authorizationService.isAuditLogsReadable()).thenReturn(true);
    when(this.repository.search(query)).thenReturn(new Result<>(List.of(), 0, 0, 0));

    this.useCase.searchAuditLogs(query);

    verify(this.repository).search(query);
  }

  @Test
  void testSearchAuditLogsReturnsRepositoryResult() {
    Query query = Query.builder().build();
    Result<AuditLog> expected = new Result<>(List.of(AuditLog.builder().id("1").build()), 1, 0, 10);
    when(this.authorizationService.isAuditLogsReadable()).thenReturn(true);
    when(this.repository.search(query)).thenReturn(expected);

    Result<AuditLog> result = this.useCase.searchAuditLogs(query);

    assertEquals(expected, result, "Unexpected result from searchAuditLogs");
  }
}
