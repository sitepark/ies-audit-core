package com.sitepark.ies.audit.core.usecase.query;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jparams.verifier.tostring.ToStringVerifier;
import java.util.ArrayList;
import java.util.List;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

class ResultTest {

  @Test
  @SuppressWarnings("PMD.JUnitTestsShouldIncludeAssert")
  void testEquals() {
    EqualsVerifier.forClass(Result.class).suppress(Warning.NULL_FIELDS).verify();
  }

  @Test
  @SuppressWarnings("PMD.JUnitTestsShouldIncludeAssert")
  void testToString() {
    ToStringVerifier.forClass(Result.class).verify();
  }

  @Test
  void testItemsAreCopiedOnConstruction() {
    List<String> items = new ArrayList<>(List.of("a", "b"));
    Result<String> result = new Result<>(items, 2, 0, 10);
    items.add("c");
    assertEquals(
        List.of("a", "b"), result.items(), "Modifying original list should not affect result");
  }

  @Test
  void testTotal() {
    Result<String> result = new Result<>(List.of("a"), 42, 0, 10);
    assertEquals(42, result.total(), "Unexpected total");
  }

  @Test
  void testOffset() {
    Result<String> result = new Result<>(List.of(), 0, 5, 10);
    assertEquals(5, result.offset(), "Unexpected offset");
  }

  @Test
  void testLimit() {
    Result<String> result = new Result<>(List.of(), 0, 0, 25);
    assertEquals(25, result.limit(), "Unexpected limit");
  }
}
