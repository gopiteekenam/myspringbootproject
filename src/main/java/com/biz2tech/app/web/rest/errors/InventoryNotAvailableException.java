package com.biz2tech.app.web.rest.errors;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class InventoryNotAvailableException extends AbstractThrowableProblem {

  private static final long serialVersionUID = 8490636260008534083L;

  public InventoryNotAvailableException() {
    super(ErrorConstants.INVENTORY_NOT_FOUND_TYPE, "Inventory not available",
        Status.NOT_ACCEPTABLE);
  }
}
