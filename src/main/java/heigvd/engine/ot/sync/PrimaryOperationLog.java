package heigvd.engine.ot.sync;

import heigvd.engine.ot.CommitException;

// TODO : Implement this
public class PrimaryOperationLog<T, Encoding> extends EncodingOperationLog<T, Encoding> {

  public PrimaryOperationLog(final OperationDecoder<T, Encoding> decoder,
                             final OperationEncoder<T, Encoding> encoder) {
    super(decoder, encoder);
  }

  @Override
  public void commit() throws CommitException {
    // The algorithm is as follows : take all of the operations, in order,
    // and transform them based on the information that was contained in
    // the log.
  }

}
