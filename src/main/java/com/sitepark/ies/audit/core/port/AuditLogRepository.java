package com.sitepark.ies.audit.core.port;

import com.sitepark.ies.audit.core.domain.entity.AuditLog;
import com.sitepark.ies.audit.core.usecase.query.Query;
import com.sitepark.ies.audit.core.usecase.query.Result;
import com.sitepark.ies.audit.core.usecase.query.filter.Filter;
import java.util.List;

public interface AuditLogRepository {

  String createAuditLog(AuditLog auditLog);

  List<AuditLog> getAll(Filter filter);

  List<AuditLog> getByIds(List<String> ids);

  List<String> getRecursiveChildIds(String parentId);

  Result<AuditLog> search(Query query);
}
