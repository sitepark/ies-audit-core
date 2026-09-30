package com.sitepark.ies.audit.core.usecase;

import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class CreateAuditLogRequestTest {

  @Test
  void testEquals() {
    EqualsVerifier.forClass(CreateAuditLogRequest.class).verify();
  }

  @Test
  void testToString() {
    ToStringVerifier.forClass(CreateAuditLogRequest.class).verify();
  }
}
