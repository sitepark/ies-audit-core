package com.sitepark.ies.audit.core.usecase.query.filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class FilterTest {

  @Test
  void testId() {
    UserId filter = Filter.userId("123");
    assertEquals("123", filter.getUserId(), "unexpected id");
  }

  @Test
  void testIdList() {
    UserIdList filter = Filter.userIdList("123");
    assertEquals(List.of("123"), filter.getUserIdList(), "unexpected idList");
  }

  @Test
  void testOr() {
    Filter a = mock();
    Filter b = mock();
    Or filter = Filter.or(a, b);
    assertEquals(Arrays.asList(a, b), filter.getOr(), "unexpected or");
  }

  @Test
  void testAnd() {
    Filter a = mock();
    Filter b = mock();
    And filter = Filter.and(a, b);
    assertEquals(Arrays.asList(a, b), filter.getAnd(), "unexpected and");
  }

  @Test
  void testNot() {
    Filter a = mock();
    Not filter = Filter.not(a);
    assertEquals(a, filter.getNot(), "unexpected not");
  }

  @Test
  void testSerialize() throws Exception {

    Filter filter =
        Filter.or(
            Filter.userIdList("6"), Filter.entityIdList("5"), Filter.and(Filter.entityId("1")));

    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(filter);

    assertEquals(
        """
        {"or":[{"userIdList":["6"]},{"entityIdList":["5"]},{"and":[{"entityId":"1"}]}]}\
        """,
        json,
        "unexpected json-data");
  }

  @Test
  void testIdFactory() {
    Id filter = Filter.id("123");
    assertEquals("123", filter.getId(), "Unexpected id");
  }

  @Test
  void testIdListFactory() {
    IdList filter = Filter.idList("a", "b");
    assertEquals(List.of("a", "b"), filter.getIdList(), "Unexpected idList");
  }

  @Test
  void testActionFactory() {
    Action filter = Filter.action("CREATE");
    assertEquals("CREATE", filter.getAction(), "Unexpected action");
  }

  @Test
  void testActionListFactory() {
    ActionList filter = Filter.actionList("CREATE", "UPDATE");
    assertEquals(List.of("CREATE", "UPDATE"), filter.getActionList(), "Unexpected actionList");
  }

  @Test
  void testAuthorityNameFactory() {
    AuthorityName filter = Filter.authorityName("admin");
    assertEquals("admin", filter.getAuthorityName(), "Unexpected authorityName");
  }

  @Test
  void testDateRangeFactory() {
    Instant from = Instant.parse("2024-01-01T00:00:00Z");
    Instant to = Instant.parse("2024-12-31T23:59:59Z");
    DateRange filter = Filter.dateRange(from, to);
    assertEquals(from, filter.getFrom(), "Unexpected from");
  }

  @Test
  void testEntityIdFactory() {
    EntityId filter = Filter.entityId("entity-1");
    assertEquals("entity-1", filter.getEntityId(), "Unexpected entityId");
  }

  @Test
  void testEntityIdListFactory() {
    EntityIdList filter = Filter.entityIdList("e1", "e2");
    assertEquals(List.of("e1", "e2"), filter.getEntityIdList(), "Unexpected entityIdList");
  }

  @Test
  void testEntityNameFactory() {
    EntityName filter = Filter.entityName("Test Entity");
    assertEquals("Test Entity", filter.getEntityName(), "Unexpected entityName");
  }

  @Test
  void testEntityTypeFactory() {
    EntityType filter = Filter.entityType("User");
    assertEquals("User", filter.getEntityType(), "Unexpected entityType");
  }

  @Test
  void testEntityTypeListFactory() {
    EntityTypeList filter = Filter.entityTypeList("User", "Role");
    assertEquals(List.of("User", "Role"), filter.getEntityTypeList(), "Unexpected entityTypeList");
  }

  @Test
  void testOnlyRootFactory() {
    OnlyRoot filter = Filter.onlyRoot(true);
    assertEquals(true, filter.getOnlyRoot(), "Unexpected onlyRoot");
  }

  @Test
  void testParentIdFactory() {
    ParentId filter = Filter.parentId("parent-1");
    assertEquals("parent-1", filter.getParentId(), "Unexpected parentId");
  }

  @Test
  void testParentIdListFactory() {
    ParentIdList filter = Filter.parentIdList("p1", "p2");
    assertEquals(List.of("p1", "p2"), filter.getParentIdList(), "Unexpected parentIdList");
  }

  // Note: Deserialization tests have been moved to the infrastructure layer
  // where the Jackson deserializer is implemented. The core module only defines
  // the type mappings via FilterTypeRegistry and @PolymorphicType annotations.
}
