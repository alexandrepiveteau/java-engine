package heigvd.engine.ot.sync;

public interface OperationEncoder<T, Encoding> {

  Encoding encode(T operation);

}
