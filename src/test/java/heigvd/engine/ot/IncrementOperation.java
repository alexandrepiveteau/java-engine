package heigvd.engine.ot;

public class IncrementOperation implements LogOperation {

  @Override
  public int apply(final int value) {
    return value + 1;
  }

}
