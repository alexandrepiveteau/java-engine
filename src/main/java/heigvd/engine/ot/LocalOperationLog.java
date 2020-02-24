package heigvd.engine.ot;

public class LocalOperationLog<T> extends AbstractOperationLog<T> {

  @Override
  public void commit() {
    getCommittedOperations().addAll(getTransientOperations());
    getTransientOperations().clear();
  }

}
