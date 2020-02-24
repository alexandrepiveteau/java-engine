package heigvd.engine.ot;

import heigvd.engine.ot.LogOperation;
import heigvd.engine.ot.OperationLog;

public class LogOperationReducer {

  // No construction available.
  private LogOperationReducer() {}

  public static int reduce(OperationLog<LogOperation> log) {
    int basis = 0;
    for (int i = 0; i < log.size(); i++) {
      basis = log.atIndex(i).apply(basis);
    }
    return basis;
  }

}
