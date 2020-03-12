package heigvd.samples.notes.op;

import heigvd.samples.notes.NoteModel;
import org.json.JSONObject;

import java.util.UUID;

public class NoteCreateOperation implements NoteOperation {

  @Override
  public void apply(final NoteModel model) {
    model.notes.put(UUID.randomUUID().toString(), "");
  }

  @Override
  public JSONObject encode() {
    return new JSONObject();
  }

}
