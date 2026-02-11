package com.sitepark.ies.audit.core.service;

public interface ReverseActionHandler {
  String getEntityType();

  void revert(RevertRequest revertRequest);
}
