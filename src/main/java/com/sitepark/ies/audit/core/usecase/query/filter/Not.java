package com.sitepark.ies.audit.core.usecase.query.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public final class Not implements Filter {

  @SuppressWarnings(
      "PMD.AvoidFieldNameMatchingTypeName") // so that when deserializing it has the desired format
  private final Filter not;

  Not(@JsonProperty("not") Filter not) {
    Objects.requireNonNull(not, "not is null");
    this.not = not;
  }

  public Filter getNot() {
    return this.not;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.not);
  }

  @Override
  public boolean equals(Object o) {
    return (o instanceof Not that) && Objects.equals(this.not, that.not);
  }

  @Override
  public String toString() {
    return "Not{" + "not=" + not + '}';
  }
}
