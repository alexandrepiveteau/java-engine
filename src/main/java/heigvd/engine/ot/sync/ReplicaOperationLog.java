package heigvd.engine.ot.sync;

import heigvd.engine.ot.CommitException;

import java.util.List;
import java.util.stream.Collectors;

// TODO : Implement this.
public class ReplicaOperationLog<T, Encoding> extends EncodingOperationLog<T, Encoding> {

  private Protocol<Encoding> protocol;

  public ReplicaOperationLog(final OperationDecoder<T, Encoding> decoder,
                             final OperationEncoder<T, Encoding> encoder,
                             final Protocol<Encoding> protocol) {
    super(decoder, encoder);
    this.protocol = protocol;
  }

  @Override
  public void commit() throws CommitException {

    // NOTE : All the operations here should be performed atomically.
    // Step 1 : push all the transient operations to the remote log.
    List<Encoding> encodings = getTransientOperations().stream()
        .map(getEncoder()::encode)
        .collect(Collectors.toList());

    // Step 2 : Retrieve the committed operations, while we're giving out
    //          our local operations.
    List<T> committed = protocol.pushAllTransientForCommit(
        sizeCommitted() - 1,
        encodings
    ).stream()
        .map(getDecoder()::decode)
        .collect(Collectors.toList());

    // Step 3 : Clear the transient operations, and commit the server
    //          contents locally.
    getTransientOperations().clear();
    getCommittedOperations().addAll(committed);
  }

}
