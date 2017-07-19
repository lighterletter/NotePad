package c4q.notepad;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by maxrosado on 7/19/17.
 */

public class NoteStuff {
    private static NoteStuff sNoteStuff;

    private List<Note> mNotes;

    public static NoteStuff get(Context context) {
        if(sNoteStuff == null) {
            sNoteStuff = new NoteStuff(context);
        }
        return sNoteStuff;
    }

    private NoteStuff(Context context) {
        mNotes = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Note note = new Note();
            note.setTitle("Note #" + i);
            note.setText("Some random note here");
            mNotes.add(note);
        }
    }

    public List<Note> getNotes() {
        return mNotes;
    }
}
