package c4q.notepad.recyclerhelpers;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import c4q.notepad.FinishedNoteListener;
import c4q.notepad.NoteDialogFragment;
import c4q.notepad.R;
import c4q.notepad.model.Note;
import io.realm.Realm;

/**
 * Created by maxrosado on 7/16/17.
 */

public class NoteViewHolder extends RecyclerView.ViewHolder {

    private static final String NOTE_DIALOG = "NoteDialog";

    private TextView titleTV;
    private TextView textTV;
    private Button deleteButton;

    public NoteViewHolder(View parent) {
        super(parent);
        titleTV = (TextView) itemView.findViewById(R.id.title_tv);
        textTV = (TextView) itemView.findViewById(R.id.text_tv);
        deleteButton = (Button) itemView.findViewById(R.id.delete_button);
    }

    public void bind(final Note note, final FinishedNoteListener listener) {
        textTV.setText(note.getText());
        titleTV.setText(note.getTitle());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editNote(note, listener);
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                note.deleteFromRealm();
                realm.commitTransaction();
                listener.updateUI();
            }
        });
    }

    private void editNote (Note note, FinishedNoteListener listener) {

        Bundle bundle = new Bundle();
        bundle.putSerializable("note", note);

        FragmentManager fm = ((AppCompatActivity) itemView.getContext()).getSupportFragmentManager();
        NoteDialogFragment noteDialogFragment = new NoteDialogFragment();
        noteDialogFragment.setfinishedNoteListener(listener);
        noteDialogFragment.setArguments(bundle);
        noteDialogFragment.show(fm, NOTE_DIALOG);
    }


}
