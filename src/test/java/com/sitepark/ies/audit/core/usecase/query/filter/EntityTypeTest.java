package com.sitepark.ies.audit.core.usecase.query.filter;

import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class EntityTypeTest {

  @Test
  void testEquals() {
    EqualsVerifier.forClass(EntityType.class).verify();
  }

  @Test
  void testToString() {
    ToStringVerifier.forClass(EntityType.class).verify();
  }
}
