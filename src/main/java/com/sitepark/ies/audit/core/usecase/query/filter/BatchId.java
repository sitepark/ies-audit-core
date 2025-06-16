package com.sitepark.ies.audit.core.usecase.query.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public final class BatchId implements Filter {

  @SuppressWarnings(
      "PMD.AvoidFieldNameMatchingTypeName") // so that when deserializing it has the desired format
  private final String batchId;

  BatchId(@JsonProperty("batchId") String batchId) {
    Objects.requireNonNull(batchId, "batchId is null");
    this.batchId = batchId;
  }

  public String getBatchId() {
    return this.batchId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.batchId);
  }

  @Override
  public boolean equals(Object o) {
    return (o instanceof BatchId that) && Objects.equals(this.batchId, that.batchId);
  }

  @Override
  public String toString() {
    return "BatchId{" + "batchId='" + batchId + '\'' + '}';
  }
}
