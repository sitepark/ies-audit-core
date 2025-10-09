package com.sitepark.ies.audit.core.usecase.query.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sitepark.ies.sharedkernel.json.UniquePropertyType;
import java.util.Objects;

@UniquePropertyType(uniqueProperty = "authorityName")
public final class AuthorityName implements Filter {

  @SuppressWarnings(
      "PMD.AvoidFieldNameMatchingTypeName") // so that when deserializing it has the desired format
  private final String authorityName;

  AuthorityName(@JsonProperty("authorityName") String authorityName) {
    Objects.requireNonNull(authorityName, "authorityName is null");
    this.authorityName = authorityName;
  }

  public String getAuthorityName() {
    return this.authorityName;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.authorityName);
  }

  @Override
  public boolean equals(Object o) {
    return (o instanceof AuthorityName that)
        && Objects.equals(this.authorityName, that.authorityName);
  }

  @Override
  public String toString() {
    return "EntityType{" + "authorityName='" + authorityName + '\'' + '}';
  }
}
