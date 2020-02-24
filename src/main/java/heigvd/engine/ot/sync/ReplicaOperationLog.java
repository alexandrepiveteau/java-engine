package heigvd.engine.ot.sync;

import heigvd.engine.ot.CommitException;

// TODO : Implement this.
public class ReplicaOperationLog<T, Encoding> extends EncodingOperationLog<T, Encoding> {

  public ReplicaOperationLog(final OperationDecoder<T, Encoding> decoder,
                             final OperationEncoder<T, Encoding> encoder) {
    super(decoder, encoder);
  }

  void pull() throws CommitException {

  }

  @Override
  public void commit() throws CommitException {
    // Step 1 : push all the transient operations to the remote log.
    // Step 2 : read all the remote operations from the log.
    pull();
  }

}
