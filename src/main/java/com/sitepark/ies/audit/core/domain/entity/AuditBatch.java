package com.sitepark.ies.audit.core.domain.entity;

import java.time.Instant;
import java.util.Objects;

/**
 * Represents a group of related audit log entries that were created as part of a single user or
 * system action.
 *
 * <p>An {@code AuditBatch} provides contextual information for one or more {@code AuditLog} entries
 * that belong together semantically — for example, the removal of multiple users or the assignment
 * of several privileges in a single operation.
 *
 * <p>This entity allows the system to:
 *
 * <ul>
 *   <li>Group and label changes under a common description
 *   <li>Track when and by whom the action was triggered
 *   <li>Support structured undo or analysis for entire change sets
 * </ul>
 *
 * <p>The {@code id} typically matches the {@code batchId} field in {@code AuditLog} entries and
 * serves as a unique identifier across the system.
 */
@SuppressWarnings("PMD.DataClass")
public final class AuditBatch {

  private final String id;
  private final Instant timestamp;
  private final String userId;
  private final String authorityName;

  private AuditBatch(Builder builder) {
    this.id = builder.id;
    this.timestamp = builder.timestamp;
    this.userId = builder.userId;
    this.authorityName = builder.authorityName;
  }

  public static Builder builder() {
    return new Builder();
  }

  public String id() {
    return id;
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

  public Builder toBuilder() {
    return new Builder(this);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, timestamp, userId, authorityName);
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof AuditBatch other)) return false;
    return Objects.equals(id, other.id)
        && Objects.equals(timestamp, other.timestamp)
        && Objects.equals(userId, other.userId)
        && Objects.equals(authorityName, other.authorityName);
  }

  public String toString() {
    return "AuditLog [id="
        + id
        + ", timestamp="
        + timestamp
        + ", userId="
        + userId
        + ", authorityName="
        + authorityName
        + "]";
  }

  @SuppressWarnings("PMD.TooManyMethods")
  public static final class Builder {
    private String id;
    private Instant timestamp;
    private String userId;
    private String authorityName;

    public Builder() {}

    public Builder(AuditBatch log) {
      this.id = log.id;
      this.timestamp = log.timestamp;
      this.userId = log.userId;
      this.authorityName = log.authorityName;
    }

    public Builder id(String id) {
      this.id = id;
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

    public AuditBatch build() {
      return new AuditBatch(this);
    }
  }
}
