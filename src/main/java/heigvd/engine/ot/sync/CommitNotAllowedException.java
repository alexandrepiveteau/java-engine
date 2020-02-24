package heigvd.engine.ot.sync;

import heigvd.engine.ot.CommitException;

public class CommitNotAllowedException extends CommitException {

  public CommitNotAllowedException(final String message) {
    super(message);
  }

}
