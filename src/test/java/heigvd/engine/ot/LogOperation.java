package heigvd.engine.ot;

@FunctionalInterface
public interface LogOperation {

  static LogOperation decrement() {
    return new DecrementOperation();
  }

  static LogOperation increment() {
    return new IncrementOperation();
  }

  int apply(int value);

}
