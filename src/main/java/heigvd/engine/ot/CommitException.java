package heigvd.engine.ot;

/**
 * An {@link Exception} class that will be used when something goes badly
 * during the commit process of ab {@link OperationLog}.
 */
public abstract class CommitException extends Exception {

  /**
   * {@inheritDoc}
   */
  public CommitException() {
    super();
  }

  /**
   * {@inheritDoc}
   */
  public CommitException(final String message) {
    super(message);
  }

  /**
   * {@inheritDoc}
   */
  public CommitException(final String message, final Throwable cause) {
    super(message, cause);
  }

  /**
   * {@inheritDoc}
   */
  public CommitException(final Throwable cause) {
    super(cause);
  }

}
