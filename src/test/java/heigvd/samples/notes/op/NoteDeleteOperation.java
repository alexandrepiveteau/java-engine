package heigvd.samples.notes.op;

import heigvd.samples.notes.NoteModel;
import org.json.JSONObject;

public class NoteDeleteOperation implements NoteOperation {

  private String uuid;

  public NoteDeleteOperation(String uuid) {
    this.uuid = uuid;
  }

  @Override
  public void apply(final NoteModel model) {
    model.notes.remove(uuid);
  }

  @Override
  public JSONObject encode() {
    return null;
  }

}
