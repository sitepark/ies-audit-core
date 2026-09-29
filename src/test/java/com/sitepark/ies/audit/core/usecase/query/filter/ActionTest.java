package com.sitepark.ies.audit.core.usecase.query.filter;

import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class ActionTest {

  @Test
  void testEquals() {
    EqualsVerifier.forClass(Action.class).verify();
  }

  @Test
  void testToString() {
    ToStringVerifier.forClass(Action.class).verify();
  }
}
