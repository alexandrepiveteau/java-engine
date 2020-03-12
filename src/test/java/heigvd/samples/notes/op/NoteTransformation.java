package heigvd.samples.notes.op;

import heigvd.engine.ot.sync.Transformation;

public class NoteTransformation implements Transformation<NoteOperation> {

  @Override
  public NoteOperation transform(final NoteOperation operation,
                                 final NoteOperation afterOperation) {
    return null;
  }

}
