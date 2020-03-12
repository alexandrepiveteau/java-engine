package heigvd.samples.notes.op;

import org.json.JSONObject;

/**
 * An interface that will be implemented by instances that can be encoded
 * into a specific format used by the note synchronization framework.
 */
public interface NoteCodeable {

  /**
   * Encodes the current instance into a {@link JSONObject}. All the
   * information that will necessary to re-create the instance should be
   * encoded here.
   *
   * @return The current instance in a {@link JSONObject}.
   */
  JSONObject encode();

}
