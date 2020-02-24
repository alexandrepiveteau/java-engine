package heigvd.engine.ot;

public class DecrementOperation implements LogOperation {

  @Override
  public int apply(final int value) {
    return value - 1;
  }

}
