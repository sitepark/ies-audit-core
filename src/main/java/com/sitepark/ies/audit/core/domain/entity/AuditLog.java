package com.sitepark.ies.audit.core.domain.entity;

import java.time.Instant;
import java.util.Objects;

/**
 * Represents a single audit entry describing a domain-relevant change within the system.
 *
 * <p>An {@code AuditLog} captures a state change that occurred on a specific domain entity, such as
 * a user, role, or privilege, and is typically emitted by a bounded context as part of a business
 * operation. Audit logs are domain-agnostic and do not contain any logic themselves— they serve as
 * traceable, interpretable records for inspection, compliance, and potential reversal.
 *
 * <p>This record contains metadata about the change, including:
 *
 * <ul>
 *   <li>the affected entity type and identifier
 *   <li>the nature of the action performed (e.g. create, update, remove)
 *   <li>the previous and new state in serialized form (usually JSON)
 *   <li>information about the user and authority responsible for the change
 *   <li>an optional batch ID linking this entry to a group of related changes
 * </ul>
 *
 * <p><strong>Typical use cases include:</strong>
 *
 * <ul>
 *   <li>Auditable change history for sensitive data
 *   <li>Undo support for reversible business operations
 *   <li>Monitoring and reporting across bounded contexts
 * </ul>
 */
@SuppressWarnings("PMD.DataClass")
public final class AuditLog {

  private final String id;
  private final String entityType;
  private final String entityId;
  private final String entityName;
  private final String action;
  private final String backwardData;
  private final String forwardData;
  private final Instant timestamp;
  private final String userId;
  private final String authorityName;
  private final String parentId;

  private AuditLog(Builder builder) {
    this.id = builder.id;
    this.entityType = builder.entityType;
    this.entityId = builder.entityId;
    this.entityName = builder.entityName;
    this.action = builder.action;
    this.backwardData = builder.backwardData;
    this.forwardData = builder.forwardData;
    this.timestamp = builder.timestamp;
    this.userId = builder.userId;
    this.authorityName = builder.authorityName;
    this.parentId = builder.parentId;
  }

  public static Builder builder() {
    return new Builder();
  }

  public String id() {
    return id;
  }

  public String entityType() {
    return entityType;
  }

  public String entityId() {
    return entityId;
  }

  public String entityName() {
    return entityName;
  }

  public String action() {
    return action;
  }

  public String backwardData() {
    return backwardData;
  }

  public String forwardData() {
    return forwardData;
  }

  public Instant timestamp() {
    return timestamp;
  }

  public String userId() {
    return userId;
  }

  public String authorityName() {
    return authorityName;
  }

  public String parentId() {
    return parentId;
  }

  public Builder toBuilder() {
    return new Builder(this);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        entityType,
        entityId,
        entityName,
        action,
        backwardData,
        forwardData,
        timestamp,
        userId,
        authorityName,
        parentId);
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof AuditLog other)) return false;
    return Objects.equals(id, other.id)
        && Objects.equals(entityType, other.entityType)
        && Objects.equals(entityId, other.entityId)
        && Objects.equals(entityName, other.entityName)
        && Objects.equals(action, other.action)
        && Objects.equals(backwardData, other.backwardData)
        && Objects.equals(forwardData, other.forwardData)
        && Objects.equals(timestamp, other.timestamp)
        && Objects.equals(userId, other.userId)
        && Objects.equals(authorityName, other.authorityName)
        && Objects.equals(parentId, other.parentId);
  }

  @Override
  public String toString() {
    return "AuditLog [id="
        + id
        + ", entityType="
        + entityType
        + ", entityId="
        + entityId
        + ", entityName="
        + entityName
        + ", action="
        + action
        + ", backwardData="
        + backwardData
        + ", forwardData="
        + forwardData
        + ", timestamp="
        + timestamp
        + ", userId="
        + userId
        + ", authorityName="
        + authorityName
        + ", parentId="
        + parentId
        + "]";
  }

  @SuppressWarnings("PMD.TooManyMethods")
  public static final class Builder {
    private String id;
    private String entityType;
    private String entityId;
    private String entityName;
    private String action;
    private String backwardData;
    private String forwardData;
    private Instant timestamp;
    private String userId;
    private String authorityName;
    private String parentId;

    public Builder() {}

    public Builder(AuditLog log) {
      this.id = log.id;
      this.entityType = log.entityType;
      this.entityId = log.entityId;
      this.entityName = log.entityName;
      this.action = log.action;
      this.backwardData = log.backwardData;
      this.forwardData = log.forwardData;
      this.timestamp = log.timestamp;
      this.userId = log.userId;
      this.authorityName = log.authorityName;
      this.parentId = log.parentId;
    }

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder entityType(String entityType) {
      this.entityType = entityType;
      return this;
    }

    public Builder entityId(String entityId) {
      this.entityId = entityId;
      return this;
    }

    public Builder entityName(String entityName) {
      this.entityName = entityName;
      return this;
    }

    public Builder action(String action) {
      this.action = action;
      return this;
    }

    public Builder backwardData(String oldData) {
      this.backwardData = oldData;
      return this;
    }

    public Builder forwardData(String newData) {
      this.forwardData = newData;
      return this;
    }

    public Builder timestamp(Instant timestamp) {
      this.timestamp = timestamp;
      return this;
    }

    public Builder userId(String userId) {
      this.userId = userId;
      return this;
    }

    public Builder authorityName(String authorityName) {
      this.authorityName = authorityName;
      return this;
    }

    public Builder parentId(String parentId) {
      this.parentId = parentId;
      return this;
    }

    public AuditLog build() {
      return new AuditLog(this);
    }
  }
}
