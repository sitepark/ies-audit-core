package com.sitepark.ies.audit.core.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jparams.verifier.tostring.ToStringVerifier;
import java.time.Instant;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class AuditLogTest {

  @Test
  @SuppressWarnings("PMD.JUnitTestsShouldIncludeAssert")
  void testEquals() {
    EqualsVerifier.forClass(AuditLog.class).verify();
  }

  @Test
  @SuppressWarnings("PMD.JUnitTestsShouldIncludeAssert")
  void testToString() {
    ToStringVerifier.forClass(AuditLog.class).verify();
  }

  @Test
  void testSetId() {
    AuditLog log = AuditLog.builder().id("123").build();
    assertEquals("123", log.id(), "Unexpected id");
  }

  @Test
  void testSetEntityType() {
    AuditLog log = AuditLog.builder().entityType("User").build();
    assertEquals("User", log.entityType(), "Unexpected entityType");
  }

  @Test
  void testSetEntityId() {
    AuditLog log = AuditLog.builder().entityId("entity-1").build();
    assertEquals("entity-1", log.entityId(), "Unexpected entityId");
  }

  @Test
  void testSetEntityName() {
    AuditLog log = AuditLog.builder().entityName("Test User").build();
    assertEquals("Test User", log.entityName(), "Unexpected entityName");
  }

  @Test
  void testSetAction() {
    AuditLog log = AuditLog.builder().action("CREATE").build();
    assertEquals("CREATE", log.action(), "Unexpected action");
  }

  @Test
  void testSetBackwardData() {
    AuditLog log = AuditLog.builder().backwardData("{\"old\":null}").build();
    assertEquals("{\"old\":null}", log.backwardData(), "Unexpected backwardData");
  }

  @Test
  void testSetForwardData() {
    AuditLog log = AuditLog.builder().forwardData("{\"new\":\"value\"}").build();
    assertEquals("{\"new\":\"value\"}", log.forwardData(), "Unexpected forwardData");
  }

  @Test
  void testSetTimestamp() {
    Instant timestamp = Instant.parse("2024-01-01T00:00:00Z");
    AuditLog log = AuditLog.builder().timestamp(timestamp).build();
    assertEquals(timestamp, log.timestamp(), "Unexpected timestamp");
  }

  @Test
  void testSetUserId() {
    AuditLog log = AuditLog.builder().userId("user-1").build();
    assertEquals("user-1", log.userId(), "Unexpected userId");
  }

  @Test
  void testSetAuthorityName() {
    AuditLog log = AuditLog.builder().authorityName("admin").build();
    assertEquals("admin", log.authorityName(), "Unexpected authorityName");
  }

  @Test
  void testSetParentId() {
    AuditLog log = AuditLog.builder().parentId("parent-1").build();
    assertEquals("parent-1", log.parentId(), "Unexpected parentId");
  }

  @Test
  void testToBuilder() {
    Instant timestamp = Instant.parse("2024-01-01T00:00:00Z");
    AuditLog original =
        AuditLog.builder()
            .id("123")
            .entityType("User")
            .entityId("entity-1")
            .entityName("Test User")
            .action("CREATE")
            .backwardData("{\"old\":null}")
            .forwardData("{\"new\":\"value\"}")
            .timestamp(timestamp)
            .userId("user-1")
            .authorityName("admin")
            .parentId("parent-1")
            .build();

    AuditLog copy = original.toBuilder().id("456").build();

    AuditLog expected =
        AuditLog.builder()
            .id("456")
            .entityType("User")
            .entityId("entity-1")
            .entityName("Test User")
            .action("CREATE")
            .backwardData("{\"old\":null}")
            .forwardData("{\"new\":\"value\"}")
            .timestamp(timestamp)
            .userId("user-1")
            .authorityName("admin")
            .parentId("parent-1")
            .build();

    assertEquals(expected, copy, "Unexpected audit log after toBuilder()");
  }
}
