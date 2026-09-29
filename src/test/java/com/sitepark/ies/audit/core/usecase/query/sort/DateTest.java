package com.sitepark.ies.audit.core.usecase.query.sort;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class DateTest {

  @Test
  void testEquals() {
    EqualsVerifier.forClass(Date.class).verify();
  }

  @Test
  void testToString() {
    ToStringVerifier.forClass(Date.class).verify();
  }

  @Test
  void testConstructor() {
    Date date = new Date(Direction.ASC);
    assertEquals(Direction.ASC, date.getDirection(), "Unexpected direction");
  }
}
