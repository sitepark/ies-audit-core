package com.sitepark.ies.audit.core.usecase.query.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public final class UserIdList implements Filter {

  @SuppressWarnings(
      "PMD.AvoidFieldNameMatchingTypeName") // so that when deserializing it has the desired format
  private final List<String> userIdList;

  UserIdList(@JsonProperty("userIdList") String... userIdList) {
    Objects.requireNonNull(userIdList, "userIdList is null");
    this.userIdList = List.of(userIdList);
  }

  public List<String> getUserIdList() {
    return this.userIdList;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.userIdList);
  }

  @Override
  public boolean equals(Object o) {
    return (o instanceof UserIdList that) && Objects.equals(this.userIdList, that.userIdList);
  }

  @Override
  public String toString() {
    return "UserIdList{" + "userIdList=" + userIdList + '}';
  }
}
