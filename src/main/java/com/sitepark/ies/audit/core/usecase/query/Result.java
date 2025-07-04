package com.sitepark.ies.audit.core.usecase.query;

import java.util.List;

public record Result<T>(List<T> items, int total, int offset, int limit) {
  public Result {
    items = List.copyOf(items);
  }
}
