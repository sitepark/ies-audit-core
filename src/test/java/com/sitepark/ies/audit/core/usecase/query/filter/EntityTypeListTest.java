package com.sitepark.ies.audit.core.usecase.query.filter;

import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class EntityTypeListTest {

  @Test
  void testEquals() {
    EqualsVerifier.forClass(EntityTypeList.class).verify();
  }

  @Test
  void testToString() {
    ToStringVerifier.forClass(EntityTypeList.class).verify();
  }
}
