package com.sitepark.ies.audit.core.domain.value;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jparams.verifier.tostring.ToStringVerifier;
import com.sitepark.ies.sharedkernel.domain.EntityRef;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class AuditLogTargetTest {

  @Test
  @SuppressWarnings("PMD.JUnitTestsShouldIncludeAssert")
  void testEquals() {
    EqualsVerifier.forClass(AuditLogTarget.class).verify();
  }

  @Test
  @SuppressWarnings("PMD.JUnitTestsShouldIncludeAssert")
  void testToString() {
    ToStringVerifier.forClass(AuditLogTarget.class).verify();
  }

  @Test
  void testOfWithEntityRef() {
    EntityRef entityRef = EntityRef.of("user", "entity-1");
    AuditLogTarget target = AuditLogTarget.of(entityRef, "Test User");
    assertEquals(
        new AuditLogTarget("user", "entity-1", "Test User"),
        target,
        "Unexpected audit log target from EntityRef");
  }

  @Test
  void testOfWithClass() {
    AuditLogTarget target = AuditLogTarget.of(String.class, "id-1", "name-1");
    AuditLogTarget expected =
        new AuditLogTarget(EntityRef.toTypeString(String.class), "id-1", "name-1");
    assertEquals(expected, target, "Unexpected audit log target from Class");
  }
}
