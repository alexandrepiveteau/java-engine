package heigvd.samples.notes.op;

import heigvd.samples.notes.NoteModel;

/**
 * Ann interface describing the different operations that are supported by
 * the note synchronization framework.
 */
public interface NoteOperation extends NoteCodeable {

  void apply(NoteModel model);

}
