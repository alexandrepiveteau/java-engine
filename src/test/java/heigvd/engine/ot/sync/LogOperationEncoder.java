package heigvd.engine.ot.sync;

import heigvd.engine.ot.DecrementOperation;
import heigvd.engine.ot.IncrementOperation;
import heigvd.engine.ot.LogOperation;

public class LogOperationEncoder implements OperationEncoder<LogOperation, EncodedOperation> {

  @Override
  public EncodedOperation encode(final LogOperation operation) {
    Class<? extends LogOperation> clazz = operation.getClass();
    if (clazz.equals(DecrementOperation.class)) {
      return EncodedOperation.Decrement;
    } else if (clazz.equals(IncrementOperation.class)) {
      return EncodedOperation.Increment;
    } else {
      throw new IllegalArgumentException("Non encodable operation.");
    }
  }

}
