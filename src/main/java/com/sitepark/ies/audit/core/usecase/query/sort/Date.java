package com.sitepark.ies.audit.core.usecase.query.sort;

public class Date extends SortCriteria {

  public Date(Direction direction) {
    super(direction);
  }

  @Override
  public String toString() {
    return "CreatedAt [direction=" + getDirection() + "]";
  }
}
