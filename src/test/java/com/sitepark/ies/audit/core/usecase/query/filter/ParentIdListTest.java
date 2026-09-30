package com.sitepark.ies.audit.core.usecase.query.filter;

import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class ParentIdListTest {

  @Test
  void testEquals() {
    EqualsVerifier.forClass(ParentIdList.class).verify();
  }

  @Test
  void testToString() {
    ToStringVerifier.forClass(ParentIdList.class).verify();
  }
}
