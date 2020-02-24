package heigvd.engine.ot;

/**
 * An interface describing a log of operations that might be shared in a
 * centralized, multi-size system. The operations are appended by sites
 * individually, who each see the log as a pair of committed and transient
 * operations list.
 * <p>
 * Committed operations are common to all sites. They are known to have
 * happened in the past, and their ordering is fixed. Instead of storing the
 * whole log, it is definitely possible to instead keep a copy of the model
 * at the end of the committed history.
 * <p>
 * Transient operations are site-specific usually. They might be transformed
 * or completely disappear during the commit process. Therefore, it is very
 * important that they are written in a distinct way in the model.
 *
 * @param <T> The type of the operations contained in this log.
 * @author Alexandre Piveteau (alexandre.piveteau@heig-vd.ch)
 */
public interface OperationLog<T> {

  /**
   * Appends a new {@link T} to this {@link OperationLog}. This method
   * considers that the current knowledge of the client corresponds to
   * what is actually inside the {@link OperationLog}.
   *
   * @param operation The operation to append to the log.
   */
  void append(T operation);

  /**
   * Returns the operation at a certain position in the log. If the
   * operation does not exist, a {@link RuntimeException} will be thrown, so
   * please make sure to catch these !
   *
   * @param index The index of the operation that we're trying to fetch.
   * @return The instance of the operation if it exists,
   * @throws IndexOutOfBoundsException If the index does not exist.
   * @throws RuntimeException          If the operation could not be found.
   */
  T atIndex(int index);

  /**
   * Returns true if the {@link #size()} of this {@link OperationLog} is
   * 0, and false otherwise.
   */
  default boolean isEmpty() {
    return size() == 0;
  }

  /**
   * Tries to commit the current transient operations into the centralized
   * log. The operation should normally succeed, but in some rare cases, it
   * might not (for instance, if there are some connectivity issues).
   *
   * @throws CommitException If there was an exception while committing.
   */
  void commit() throws CommitException;

  /**
   * Returns the current commit index. Operations that are defined prior to
   * this index will be considered as committed, and operations defined
   * posterior to this will be considered as not committed.
   */
  int commitIndex();

  /**
   * Returns the current size of the log. This will be an ever-increasing
   * sequence of numbers, assuming that the log is not purged.
   */
  int size();

  /**
   * Returns the size of the operations in the log that are properly
   * committed, and therefore shared amongst the different sites that share
   * this log.
   */
  default int sizeCommitted() {
    return commitIndex() + 1;
  }

  /**
   * Returns the size of the operations in the log that are not committed
   * and remain site-specific, and that will be committed on the next commit
   * request.
   */
  default int sizeTransient() {
    return size() - sizeCommitted();
  }

}
