package com.sitepark.ies.audit.core.usecase.query.limit;

public interface Limit {
  static OffsetLimit offset(Integer offset, Integer limit) {
    return new OffsetLimit(offset, limit);
  }
}
