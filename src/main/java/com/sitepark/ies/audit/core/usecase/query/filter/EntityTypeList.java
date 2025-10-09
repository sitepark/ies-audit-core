package com.sitepark.ies.audit.core.usecase.query.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sitepark.ies.sharedkernel.json.UniquePropertyType;
import java.util.List;
import java.util.Objects;

@UniquePropertyType(uniqueProperty = "entityTypeList")
public final class EntityTypeList implements Filter {

  @SuppressWarnings(
      "PMD.AvoidFieldNameMatchingTypeName") // so that when deserializing it has the desired format
  private final List<String> entityTypeList;

  EntityTypeList(@JsonProperty("entityTypeList") String... entityTypeList) {
    Objects.requireNonNull(entityTypeList, "entityTypeList is null");
    this.entityTypeList = List.of(entityTypeList);
  }

  public List<String> getEntityTypeList() {
    return this.entityTypeList;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.entityTypeList);
  }

  @Override
  public boolean equals(Object o) {
    return (o instanceof EntityTypeList that)
        && Objects.equals(this.entityTypeList, that.entityTypeList);
  }

  @Override
  public String toString() {
    return "EntityTypeList{" + "entityTypeList=" + entityTypeList + '}';
  }
}
