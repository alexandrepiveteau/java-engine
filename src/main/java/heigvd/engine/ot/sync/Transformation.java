package heigvd.engine.ot.sync;

/**
 * An interface representing a transformation that happens through
 * operational transform. Transformations take an operation prior to the
 * application of another operation, consider the effects of the application
 * of the second operation, and transform the original operation to consider
 * that the effects of the second operation have been applied.
 * <p>
 * This operation transformation is guaranteed to success.
 *
 * @param <T> The type of the operations that are transformed.
 * @author Alexandre Piveteau (alexandre.piveteau@heig-vd.ch)
 */
@FunctionalInterface
public interface Transformation<T> {

  /**
   * Returns a {@link Transformation} that will not modify any of the
   * operations. If the operations are known to have no interactions with
   * each other, this is what should be used.
   *
   * @param <T> The type of the operations.
   * @return An identity {@link Transformation}.
   */
  static <T> Transformation<T> identity() {
    return (operation, ignored) -> operation;
  }

  /**
   * Transforms a first operation by taking in account the effects of
   * another operation.
   *
   * @param operation      The operation to transform.
   * @param afterOperation The effect to include.
   * @return The transformed operation.
   */
  T transform(T operation, T afterOperation);
}
