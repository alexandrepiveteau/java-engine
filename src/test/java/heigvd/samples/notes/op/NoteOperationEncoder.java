package heigvd.samples.notes.op;

import heigvd.engine.ot.sync.OperationEncoder;
import org.json.JSONObject;

public class NoteOperationEncoder implements OperationEncoder<NoteOperation, JSONObject> {

  @Override
  public JSONObject encode(final NoteOperation operation) {
    return operation.encode();
  }

}
