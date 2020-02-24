package heigvd.engine.ot.sync;

import heigvd.engine.ot.CommitException;

import java.util.List;

/**
 * An interface describing the synchronization protocol between a replica
 * and a master. Masters are generally not able to push to replicas, but
 * replicas can initiate a synchronization whenever they want by sending
 * their new operations.
 * <p>
 * Although not mandatory, a {@link Protocol} will generally work with
 * encoded representations of the operations rather than the raw objects
 * themselves.
 *
 * @param <T> The type of the encoding of the operations.
 * @author Alexandre Piveteau (alexandre.piveteau@heig-vd.ch)
 */
public interface Protocol<T> {

  /**
   * The only synchronization method that is allowed : a site will push
   * all of its operations to a replica, which will perform appropriate
   * transformations if necessary, and will send back the transformed log
   * of operations to append locally at the last index.
   *
   * @param lastIndex The index of the last synced operation.
   * @param ops       The operations that should be transformed and committed.
   * @return The committed operations.
   * @throws CommitException If something went wrong in the protocol.
   */
  List<T> pushAllTransientForCommit(int lastIndex, List<T> ops) throws CommitException;

}
