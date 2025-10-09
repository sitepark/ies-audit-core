package com.sitepark.ies.audit.core.usecase.query.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sitepark.ies.sharedkernel.json.UniquePropertyType;
import java.util.List;
import java.util.Objects;

@UniquePropertyType(uniqueProperty = "parentIdList")
public final class ParentIdList implements Filter {

  @SuppressWarnings(
      "PMD.AvoidFieldNameMatchingTypeName") // so that when deserializing it has the desired format
  private final List<String> parentIdList;

  ParentIdList(@JsonProperty("parentIdList") String... parentIdList) {
    Objects.requireNonNull(parentIdList, "parentIdList is null");
    this.parentIdList = List.of(parentIdList);
  }

  public List<String> getParentIdList() {
    return this.parentIdList;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.parentIdList);
  }

  @Override
  public boolean equals(Object o) {
    return (o instanceof ParentIdList that) && Objects.equals(this.parentIdList, that.parentIdList);
  }

  @Override
  public String toString() {
    return "ParentIdList{" + "parentIdList=" + parentIdList + '}';
  }
}
