package heigvd.samples.notes.op;

import heigvd.samples.notes.NoteModel;
import org.json.JSONObject;

public class NoteSetContentOperation implements NoteOperation {

  private String uuid;
  private String content;

  public NoteSetContentOperation(String uuid, String content) {
    this.uuid = uuid;
    this.content = content;
  }

  @Override
  public void apply(final NoteModel model) {
    if (!model.notes.containsKey(uuid)) throw new IllegalStateException();
    model.notes.put(uuid, content);
  }

  @Override
  public JSONObject encode() {
    return null;
  }

}
