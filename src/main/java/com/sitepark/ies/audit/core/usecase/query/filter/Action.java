package com.sitepark.ies.audit.core.usecase.query.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public final class Action implements Filter {

  @SuppressWarnings(
      "PMD.AvoidFieldNameMatchingTypeName") // so that when deserializing it has the desired format
  private final String action;

  Action(@JsonProperty("action") String action) {
    Objects.requireNonNull(action, "action is null");
    this.action = action;
  }

  public String getAction() {
    return this.action;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.action);
  }

  @Override
  public boolean equals(Object o) {
    return (o instanceof Action that) && Objects.equals(this.action, that.action);
  }

  @Override
  public String toString() {
    return "Action{" + "action='" + action + '\'' + '}';
  }
}
