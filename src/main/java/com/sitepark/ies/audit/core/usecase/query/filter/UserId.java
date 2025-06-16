package com.sitepark.ies.audit.core.usecase.query.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public final class UserId implements Filter {

  @SuppressWarnings(
      "PMD.AvoidFieldNameMatchingTypeName") // so that when deserializing it has the desired format
  private final String userId;

  UserId(@JsonProperty("userId") String userId) {
    Objects.requireNonNull(userId, "userId is null");
    this.userId = userId;
  }

  public String getUserId() {
    return this.userId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.userId);
  }

  @Override
  public boolean equals(Object o) {
    return (o instanceof UserId that) && Objects.equals(this.userId, that.userId);
  }

  @Override
  public String toString() {
    return "UserId{" + "userId='" + userId + '\'' + '}';
  }
}
