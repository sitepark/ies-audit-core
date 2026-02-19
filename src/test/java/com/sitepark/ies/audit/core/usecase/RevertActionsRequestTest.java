package com.sitepark.ies.audit.core.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jparams.verifier.tostring.ToStringVerifier;
import java.util.ArrayList;
import java.util.List;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

class RevertActionsRequestTest {

  @Test
  @SuppressWarnings("PMD.JUnitTestsShouldIncludeAssert")
  void testEquals() {
    EqualsVerifier.forClass(RevertActionsRequest.class).suppress(Warning.NULL_FIELDS).verify();
  }

  @Test
  @SuppressWarnings("PMD.JUnitTestsShouldIncludeAssert")
  void testToString() {
    ToStringVerifier.forClass(RevertActionsRequest.class).verify();
  }

  @Test
  void testAuditLogIdsAreCopiedOnConstruction() {
    List<String> ids = new ArrayList<>(List.of("1", "2"));
    RevertActionsRequest request = new RevertActionsRequest(ids, "parent-1");
    ids.add("3");
    assertEquals(
        List.of("1", "2"),
        request.auditLogIds(),
        "Modifying original list should not affect request");
  }

  @Test
  void testAuditLogIdsAccessorReturnsCopy() {
    RevertActionsRequest request = new RevertActionsRequest(List.of("1", "2"), "parent-1");
    List<String> firstCall = request.auditLogIds();
    List<String> secondCall = request.auditLogIds();
    assertEquals(firstCall, secondCall, "Each call to auditLogIds() should return equal list");
  }
}
