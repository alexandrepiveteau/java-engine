package heigvd.engine.ot.sync;

public interface OperationDecoder<T, Encoding> {

  T decode(Encoding encoding);

}
