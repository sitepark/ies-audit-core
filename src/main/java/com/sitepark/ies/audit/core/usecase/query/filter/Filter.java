package com.sitepark.ies.audit.core.usecase.query.filter;

@SuppressWarnings("PMD.TooManyMethods")
public interface Filter {

  static UserId userId(String id) {
    return new UserId(id);
  }

  static UserIdList userIdList(String... idList) {
    return new UserIdList(idList);
  }

  static EntityId entityId(String roleId) {
    return new EntityId(roleId);
  }

  static EntityIdList entityIdList(String... roleIdList) {
    return new EntityIdList(roleIdList);
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
