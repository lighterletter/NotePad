package c4q.notepad;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import c4q.notepad.model.Note;

/**
 * Created by maxrosado on 7/19/17.
 */

public class NoteDialogFragment extends android.support.v4.app.DialogFragment {


    private FinishedNoteListener listener;

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
            String savedTitle = bundle.getString("note_title", ""); // empty for default
            String savedText = bundle.getString("note_text", "");
            if (!savedTitle.isEmpty() && !savedText.isEmpty()){
                titleEditText.setText(savedTitle);
                noteEditText.setText(savedText);
            }
        }

        return getAlertDialog(v, titleEditText, noteEditText);
    }

    private AlertDialog getAlertDialog(View v, final EditText titleed, final EditText noteed) {
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("New Note")
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String title = String.valueOf(titleed.getText());
                        String note = String.valueOf(noteed.getText());
                        Note newNote = new Note(); //TODO: We get duplicates on update because of this.
                        newNote.setTitle(title);
                        newNote.setText(note);
                        listener.createNewNote(newNote);
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
