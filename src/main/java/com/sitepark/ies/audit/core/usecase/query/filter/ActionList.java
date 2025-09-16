package com.sitepark.ies.audit.core.usecase.query.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public final class ActionList implements Filter {

  @SuppressWarnings(
      "PMD.AvoidFieldNameMatchingTypeName") // so that when deserializing it has the desired format
  private final List<String> actionList;

  ActionList(@JsonProperty("actionList") String... entityIdList) {
    Objects.requireNonNull(entityIdList, "actionList is null");
    this.actionList = List.of(entityIdList);
  }

  public List<String> getActionList() {
    return this.actionList;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.actionList);
  }

  @Override
  public boolean equals(Object o) {
    return (o instanceof ActionList that) && Objects.equals(this.actionList, that.actionList);
  }

  @Override
  public String toString() {
    return "ActionList{" + "actionList=" + actionList + '}';
  }
}
