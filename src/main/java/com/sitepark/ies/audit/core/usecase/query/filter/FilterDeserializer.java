package com.sitepark.ies.audit.core.usecase.query.filter;

import com.sitepark.ies.audit.core.domain.databind.UniquePropertyPolymorphicDeserializer;
import java.io.Serial;

public class FilterDeserializer extends UniquePropertyPolymorphicDeserializer<Filter> {

  @Serial private static final long serialVersionUID = 1L;

  public FilterDeserializer() {
    super(Filter.class);
    super.register("userId", UserId.class);
    super.register("userIdList", UserIdList.class);
    super.register("entityId", EntityId.class);
    super.register("entityIdList", EntityIdList.class);
    super.register("and", And.class);
    super.register("or", Or.class);
    super.register("not", Not.class);
  }
}
