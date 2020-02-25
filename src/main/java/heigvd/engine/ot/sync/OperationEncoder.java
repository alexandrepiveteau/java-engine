package heigvd.engine.ot.sync;

public interface OperationEncoder<T, Encoding> {

  static <T> OperationEncoder<T, T> identity() {
    return t -> t;
  }

  Encoding encode(T operation);

}
