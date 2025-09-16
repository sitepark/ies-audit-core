package com.sitepark.ies.audit.core.usecase.query.filter;

import java.time.Instant;

@SuppressWarnings("PMD.TooManyMethods")
public interface Filter {

  static Action action(String action) {
    return new Action(action);
  }

  static ActionList actionList(String... actionList) {
    return new ActionList(actionList);
  }

  static AuthorityName authorityName(String authorityName) {
    return new AuthorityName(authorityName);
  }

  static BatchId batchId(String batchId) {
    return new BatchId(batchId);
  }

  static DateRange dateRange(Instant from, Instant to) {
    return new DateRange(from, to);
  }

  static EntityId entityId(String roleId) {
    return new EntityId(roleId);
  }

  static EntityIdList entityIdList(String... entityIdList) {
    return new EntityIdList(entityIdList);
  }

  static EntityType entityType(String entityType) {
    return new EntityType(entityType);
  }

  static EntityTypeList entityTypeList(String... entityTypeList) {
    return new EntityTypeList(entityTypeList);
  }

  static UserId userId(String id) {
    return new UserId(id);
  }

  static UserIdList userIdList(String... idList) {
    return new UserIdList(idList);
  }

  static Or or(Filter... filterList) {
    return new Or(filterList);
  }

  static And and(Filter... filterList) {
    return new And(filterList);
  }

  static Not not(Filter filter) {
    return new Not(filter);
  }
}
