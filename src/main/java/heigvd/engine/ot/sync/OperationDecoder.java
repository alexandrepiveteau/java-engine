package heigvd.engine.ot.sync;

public interface OperationDecoder<T, Encoding> {

  static <T> OperationDecoder<T, T> identity() {
    return t -> t;
  }

  T decode(Encoding encoding);

}
