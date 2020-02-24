package heigvd.engine.ot.sync;

import heigvd.engine.ot.CommitException;

import java.util.Iterator;
import java.util.stream.Collectors;

// TODO : Test this.
public class PrimaryOperationLog<T, Encoding> extends EncodingOperationLog<T, Encoding> {

  private Transformation<T> transformation;

  public PrimaryOperationLog(final OperationDecoder<T, Encoding> decoder,
                             final OperationEncoder<T, Encoding> encoder,
                             final Transformation<T> transformation) {
    super(decoder, encoder);
    this.transformation = transformation;
  }

  @Override
  public void commit() throws CommitException {
    throw new CommitNotAllowedException("Primary logs can't commit().");
  }

  // TODO : Test this.
  public Protocol<Encoding> createProtocol() {
    return (lastIndex, ops) -> {

      // Step 1 : Get the mapped operations.
      Iterator<T> iterator = ops.stream()
          .map(getDecoder()::decode)
          .iterator();

      // Step 2 : For each operation, transform it with the end of the log.
      // Step 3 : Each transformed operation is appended to the log.
      while (iterator.hasNext()) {
        T operation = iterator.next();
        for (int i = lastIndex + 1; i < sizeCommitted(); i++) {
          operation = transformation.transform(operation, atIndex(i));
        }
        getCommittedOperations().add(operation);
      }

      // Step 4 : Return the sublist corresponding to the updated bits.
      return getCommittedOperations().subList(lastIndex + 1, lastIndex + 1 + ops.size())
          .stream()
          .map(getEncoder()::encode)
          .collect(Collectors.toUnmodifiableList());
    };
  }

}
