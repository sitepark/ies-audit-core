package com.sitepark.ies.audit.core.domain.value;

import com.sitepark.ies.sharedkernel.domain.EntityRef;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import org.jspecify.annotations.Nullable;

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
public record AuditLogTarget(@Nullable String type, @Nullable String id, @Nullable String name)
    implements Serializable {

  @Serial private static final long serialVersionUID = 1L;

  public static AuditLogTarget of(EntityRef entityRef, @Nullable String name) {
    return new AuditLogTarget(entityRef.type(), entityRef.id(), name);
  }

  public static AuditLogTarget of(Class<?> type, @Nullable String id, @Nullable String name) {
    return new AuditLogTarget(EntityRef.toTypeString(type), id, name);
  }

  public EntityRef toEntityRef() {
    return EntityRef.of(
        Objects.requireNonNull(this.type(), "target has no type"),
        Objects.requireNonNull(this.id(), "target has no id"));
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
