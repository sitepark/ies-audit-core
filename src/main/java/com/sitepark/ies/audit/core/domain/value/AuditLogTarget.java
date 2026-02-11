package com.sitepark.ies.audit.core.domain.value;

import com.sitepark.ies.sharedkernel.domain.EntityRef;
import java.io.Serial;
import java.io.Serializable;

/**
 * Represents the target of an audit log entry, describing the specific object that was modified or
 * accessed in an audit trail.
 *
 * <p>This record captures essential information about the audited object, including its type,
 * unique identifier, and optional name.
 *
 * @param type The type or category of the audited object (e.g., "User", "Document")
 * @param id The unique identifier of the specific object
 * @param name An optional human-readable name of the object
 */
public record AuditLogTarget(String type, String id, String name) implements Serializable {

  @Serial private static final long serialVersionUID = 1L;

  public static AuditLogTarget of(EntityRef entityRef, String name) {
    return new AuditLogTarget(entityRef.type(), entityRef.id(), name);
  }

  public static AuditLogTarget of(Class<?> type, String id, String name) {
    return new AuditLogTarget(EntityRef.toTypeString(type), id, name);
  }

  @Override
  public String toString() {
    return "AuditLogTarget{"
        + "type='"
        + type
        + '\''
        + ", id='"
        + id
        + '\''
        + ", name='"
        + name
        + '\''
        + '}';
  }
}
