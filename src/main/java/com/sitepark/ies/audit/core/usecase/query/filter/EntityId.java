package com.sitepark.ies.audit.core.usecase.query.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public final class EntityId implements Filter {

  @SuppressWarnings(
      "PMD.AvoidFieldNameMatchingTypeName") // so that when deserializing it has the desired format
  private final String entityId;

  EntityId(@JsonProperty("entityId") String entityId) {
    Objects.requireNonNull(entityId, "entityId is null");
    this.entityId = entityId;
  }

  public String getEntityId() {
    return this.entityId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.entityId);
  }

  @Override
  public boolean equals(Object o) {
    return (o instanceof EntityId that) && Objects.equals(this.entityId, that.entityId);
  }

  @Override
  public String toString() {
    return "EntityId{" + "entityId='" + entityId + '\'' + '}';
  }
}
