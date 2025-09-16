package com.sitepark.ies.audit.core.usecase.query.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.Objects;

public final class DateRange implements Filter {

  private final Instant from;

  private final Instant to;

  DateRange(@JsonProperty("dateRange") Instant from, Instant to) {
    if (from == null && to == null) {
      throw new IllegalArgumentException("Both from and to are null");
    }
    this.from = from;
    this.to = to;
  }

  public Instant getFrom() {
    return this.from;
  }

  public Instant getTo() {
    return this.to;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.from, this.to);
  }

  @Override
  public boolean equals(Object o) {
    return (o instanceof DateRange that)
        && Objects.equals(this.from, that.from)
        && Objects.equals(this.to, that.to);
  }

  @Override
  public String toString() {
    return "DateRange{" + "from=" + from + ", to=" + to + '}';
  }
}
