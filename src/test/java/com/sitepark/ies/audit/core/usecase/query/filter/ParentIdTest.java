package com.sitepark.ies.audit.core.usecase.query.filter;

import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class ParentIdTest {

  @Test
  void testEquals() {
    EqualsVerifier.forClass(ParentId.class).verify();
  }

  @Test
  void testToString() {
    ToStringVerifier.forClass(ParentId.class).verify();
  }
}
