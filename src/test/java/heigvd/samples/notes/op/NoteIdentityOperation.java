package heigvd.samples.notes.op;

import heigvd.samples.notes.NoteModel;
import org.json.JSONObject;

public class NoteIdentityOperation implements NoteOperation {

  @Override
  public JSONObject encode() {
    return null;
  }

  @Override
  public void apply(final NoteModel model) {
    // Identity operation, so we're not doing anything.
  }

}
