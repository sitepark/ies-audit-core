package com.sitepark.ies.audit.core.usecase.query.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sitepark.ies.sharedkernel.json.UniquePropertyType;
import java.util.Objects;

@UniquePropertyType(uniqueProperty = "entityType")
public final class EntityType implements Filter {

  @SuppressWarnings(
      "PMD.AvoidFieldNameMatchingTypeName") // so that when deserializing it has the desired format
  private final String entityType;

  EntityType(@JsonProperty("entityType") String entityType) {
    Objects.requireNonNull(entityType, "entityType is null");
    this.entityType = entityType;
  }

  public String getEntityType() {
    return this.entityType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.entityType);
  }

  @Override
  public boolean equals(Object o) {
    return (o instanceof EntityType that) && Objects.equals(this.entityType, that.entityType);
  }

  @Override
  public String toString() {
    return "EntityType{" + "entityType='" + entityType + '\'' + '}';
  }
}
