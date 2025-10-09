package com.sitepark.ies.audit.core.usecase.query.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sitepark.ies.sharedkernel.json.UniquePropertyType;
import java.util.Objects;

@UniquePropertyType(uniqueProperty = "entityName")
public final class EntityName implements Filter {

  @SuppressWarnings(
      "PMD.AvoidFieldNameMatchingTypeName") // so that when deserializing it has the desired format
  private final String entityName;

  EntityName(@JsonProperty("entityName") String entityName) {
    Objects.requireNonNull(entityName, "entityName is null");
    this.entityName = entityName;
  }

  public String getEntityName() {
    return this.entityName;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.entityName);
  }

  @Override
  public boolean equals(Object o) {
    return (o instanceof EntityName that) && Objects.equals(this.entityName, that.entityName);
  }

  @Override
  public String toString() {
    return "EntityName{" + "entityName='" + entityName + '\'' + '}';
  }
}
