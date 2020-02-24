package heigvd.engine.ot.sync;

import heigvd.engine.ot.DecrementOperation;
import heigvd.engine.ot.IncrementOperation;
import heigvd.engine.ot.LogOperation;

public class LogOperationDecoder implements OperationDecoder<LogOperation, EncodedOperation> {

  @Override
  public LogOperation decode(final EncodedOperation encodedOperation) {
    switch (encodedOperation){
      case Decrement:
        return new DecrementOperation();
      case Increment:
        return new IncrementOperation();
      default:
        throw new IllegalArgumentException("Could not decode operation.");
    }
  }

}
