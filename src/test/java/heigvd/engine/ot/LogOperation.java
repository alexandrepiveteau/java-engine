package heigvd.engine.ot;

public interface LogOperation {

  static LogOperation decrement() {
    return new DecrementOperation();
  }

  static LogOperation increment() {
    return new IncrementOperation();
  }

}
