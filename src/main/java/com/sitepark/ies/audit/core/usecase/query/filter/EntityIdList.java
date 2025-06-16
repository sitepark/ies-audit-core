package com.sitepark.ies.audit.core.usecase.query.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public final class EntityIdList implements Filter {

  @SuppressWarnings(
      "PMD.AvoidFieldNameMatchingTypeName") // so that when deserializing it has the desired format
  private final List<String> entityIdList;

  EntityIdList(@JsonProperty("entityIdList") String... entityIdList) {
    Objects.requireNonNull(entityIdList, "entityIdList is null");
    this.entityIdList = List.of(entityIdList);
  }

  public List<String> getEntityIdList() {
    return this.entityIdList;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.entityIdList);
  }

  @Override
  public boolean equals(Object o) {
    return (o instanceof EntityIdList that) && Objects.equals(this.entityIdList, that.entityIdList);
  }

  @Override
  public String toString() {
    return "EntityIdList{" + "entityIdList=" + entityIdList + '}';
  }
}
