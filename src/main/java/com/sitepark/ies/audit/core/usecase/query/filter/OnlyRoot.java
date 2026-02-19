package com.sitepark.ies.audit.core.usecase.query.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sitepark.ies.sharedkernel.json.UniquePropertyType;
import java.util.Objects;

@UniquePropertyType(uniqueProperty = "onlyRoot")
public final class OnlyRoot implements Filter {

  @SuppressWarnings(
      "PMD.AvoidFieldNameMatchingTypeName") // so that when deserializing it has the desired format
  private final boolean onlyRoot;

  OnlyRoot(@JsonProperty("onlyRoot") boolean onlyRoot) {
    this.onlyRoot = onlyRoot;
  }

  public boolean getOnlyRoot() {
    return this.onlyRoot;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.onlyRoot);
  }

  @Override
  public boolean equals(Object o) {
    return (o instanceof OnlyRoot that) && Objects.equals(this.onlyRoot, that.onlyRoot);
  }

  @Override
  public String toString() {
    return "OnlyRoot{" + "onlyRoot='" + onlyRoot + '\'' + '}';
  }
}
