package heigvd.engine.ot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractOperationLog<T> implements OperationLog<T> {

  private List<T> committedOps = new ArrayList<>();
  private List<T> transientOps = new ArrayList<>();

  /**
   * Returns the (mutable) list of committed operations. This might be
   * modified to actually perform the commit operations.
   */
  protected List<T> getCommittedOperations() {
    return this.committedOps;
  }

  /**
   * Returns the (mutable) list of committed operations. This might be
   * modified to actually perform the commit operations.
   */
  protected List<T> getTransientOperations() {
    return this.transientOps;
  }

  @Override
  public void append(final T operation) {
    this.transientOps.add(operation);
  }

  @Override
  public T atIndex(final int index) {
    Objects.checkIndex(index, size());
    if (index < committedOps.size()) {
      return committedOps.get(index);
    } else {
      return transientOps.get(index - sizeCommitted());
    }
  }

  @Override
  public int commitIndex() {
    return committedOps.size() - 1;
  }

  @Override
  public int size() {
    return committedOps.size() + transientOps.size();
  }

}
