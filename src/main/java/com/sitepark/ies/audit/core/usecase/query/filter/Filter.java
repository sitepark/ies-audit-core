package com.sitepark.ies.audit.core.usecase.query.filter;

import com.sitepark.ies.sharedkernel.json.UseUniquePropertyDeserializer;
import java.time.Instant;

@UseUniquePropertyDeserializer(
    types = {
      Action.class,
      ActionList.class,
      And.class,
      AuthorityName.class,
      DateRange.class,
      EntityId.class,
      EntityIdList.class,
      EntityName.class,
      EntityType.class,
      EntityTypeList.class,
      Id.class,
      IdList.class,
      Not.class,
      OnlyRoot.class,
      Or.class,
      ParentId.class,
      ParentIdList.class,
      UserId.class,
      UserIdList.class
    })
@SuppressWarnings("PMD.TooManyMethods")
public interface Filter {

  static Id id(String id) {
    return new Id(id);
  }

  static IdList idList(String... idList) {
    return new IdList(idList);
  }

  static Action action(String action) {
    return new Action(action);
  }

  static ActionList actionList(String... actionList) {
    return new ActionList(actionList);
  }

  static AuthorityName authorityName(String authorityName) {
    return new AuthorityName(authorityName);
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

  static EntityName entityName(String entityName) {
    return new EntityName(entityName);
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

  static OnlyRoot onlyRoot(boolean onlyRoot) {
    return new OnlyRoot(onlyRoot);
  }

  static ParentId parentId(String id) {
    return new ParentId(id);
  }

  static ParentIdList parentIdList(String... idList) {
    return new ParentIdList(idList);
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
