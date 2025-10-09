package com.sitepark.ies.audit.core.usecase.query.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sitepark.ies.sharedkernel.json.UniquePropertyType;
import java.util.Objects;

@UniquePropertyType(uniqueProperty = "parentId")
public final class ParentId implements Filter {

  @SuppressWarnings(
      "PMD.AvoidFieldNameMatchingTypeName") // so that when deserializing it has the desired format
  private final String parentId;

  ParentId(@JsonProperty("parentId") String parentId) {
    Objects.requireNonNull(parentId, "parentId is null");
    this.parentId = parentId;
  }

  public String getParentId() {
    return this.parentId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.parentId);
  }

  @Override
  public boolean equals(Object o) {
    return (o instanceof ParentId that) && Objects.equals(this.parentId, that.parentId);
  }

  @Override
  public String toString() {
    return "ParentId{" + "parentId='" + parentId + '\'' + '}';
  }
}
