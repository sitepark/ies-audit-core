package com.sitepark.ies.audit.core.usecase.query.filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sitepark.ies.audit.core.domain.databind.DatabindModule;
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
  @SuppressWarnings("PMD.JUnitTestContainsTooManyAsserts")
  void testDeserialize() throws Exception {

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new DatabindModule());

    String json =
        """
        {"or":[{"userIdList":["6"]},{"entityIdList":["5"]},{"and":[{"entityId":"1"}]}]}
        """;

    Filter filter = objectMapper.readValue(json, Filter.class);

    Filter expected =
        Filter.or(
            Filter.userIdList("6"), Filter.entityIdList("5"), Filter.and(Filter.entityId("1")));

    assertEquals(expected, filter, "unexpected filter");
  }
}
