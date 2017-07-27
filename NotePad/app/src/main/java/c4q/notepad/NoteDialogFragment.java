package c4q.notepad;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import c4q.notepad.model.Note;
import io.realm.Realm;

/**
 * Created by maxrosado on 7/19/17.
 */

public class NoteDialogFragment extends android.support.v4.app.DialogFragment {

    private FinishedNoteListener listener;
    private String dialogTitle = "New Note";
    private Note note = null;

    public void setfinishedNoteListener(FinishedNoteListener listener) {
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.note_dialog, null);

        EditText titleEditText = (EditText) v.findViewById(R.id.new_note_title_input);
        EditText noteEditText = (EditText) v.findViewById(R.id.new_note_text_body_input);

        Bundle bundle = getArguments();
        if (bundle != null) {
            // key / values
            dialogTitle = "Edit Note";
            note = (Note) getArguments().getSerializable("note");
            String savedTitle = note.getTitle();
            String savedText = note.getText();

            if (!savedTitle.isEmpty() && !savedText.isEmpty()) {
                titleEditText.setText(savedTitle);
                noteEditText.setText(savedText);
            }
        }

        return getAlertDialog(v, titleEditText, noteEditText);
    }

    private AlertDialog getAlertDialog(View v, final EditText titleed, final EditText noteed) {
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(dialogTitle)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String titleText = String.valueOf(titleed.getText());
                        String noteText = String.valueOf(noteed.getText());

                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();

                        if (note == null) {
                            note = realm.createObject(Note.class);
                        }

                        note.setTitle(titleText);
                        note.setText(noteText);

                        realm.insertOrUpdate(note);
                        realm.commitTransaction();
                        listener.updateUI();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
    }

}
