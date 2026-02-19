package com.sitepark.ies.audit.core.usecase.query.filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jparams.verifier.tostring.ToStringVerifier;
import java.time.Instant;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class DateRangeTest {

  @Test
  @SuppressWarnings("PMD.JUnitTestsShouldIncludeAssert")
  void testEquals() {
    EqualsVerifier.forClass(DateRange.class).verify();
  }

  @Test
  @SuppressWarnings("PMD.JUnitTestsShouldIncludeAssert")
  void testToString() {
    ToStringVerifier.forClass(DateRange.class).verify();
  }

  @Test
  void testBothFromAndToNullThrows() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new DateRange(null, null),
        "Both from and to being null should throw IllegalArgumentException");
  }

  @Test
  void testGetFrom() {
    Instant from = Instant.parse("2024-01-01T00:00:00Z");
    DateRange filter = new DateRange(from, null);
    assertEquals(from, filter.getFrom(), "Unexpected from value");
  }

  @Test
  void testGetTo() {
    Instant to = Instant.parse("2024-12-31T23:59:59Z");
    DateRange filter = new DateRange(null, to);
    assertEquals(to, filter.getTo(), "Unexpected to value");
  }
}
