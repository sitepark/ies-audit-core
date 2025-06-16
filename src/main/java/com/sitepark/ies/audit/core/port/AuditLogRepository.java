package com.sitepark.ies.audit.core.port;

import com.sitepark.ies.audit.core.domain.entity.AuditBatch;
import com.sitepark.ies.audit.core.domain.entity.AuditLog;
import com.sitepark.ies.audit.core.usecase.query.Query;
import com.sitepark.ies.audit.core.usecase.query.Result;
import java.util.List;

public interface AuditLogRepository {

  String createAuditLog(AuditLog auditLog);

  String createAuditBatch(AuditBatch auditBatch);

  AuditLog getAuditLog(String id);

  List<AuditLog> getAuditLogsByBatchId(String batchId);

  Result<AuditLog> search(Query query);
}
