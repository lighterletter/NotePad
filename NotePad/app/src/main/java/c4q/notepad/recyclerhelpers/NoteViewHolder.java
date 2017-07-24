package c4q.notepad.recyclerhelpers;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import c4q.notepad.NoteDialogFragment;
import c4q.notepad.R;
import c4q.notepad.model.Note;

/**
 * Created by maxrosado on 7/16/17.
 */

public class NoteViewHolder extends RecyclerView.ViewHolder {

    private static final String NOTE_DIALOG = "NoteDialog";

    private TextView titleTV;
    private TextView textTV;

    public NoteViewHolder(View parent, final FragmentManager fragmentManager) {
        super(parent);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editNote(fragmentManager);
            }
        });

        titleTV = (TextView) itemView.findViewById(R.id.title_tv);
        textTV = (TextView) itemView.findViewById(R.id.text_tv);
    }

    public void bind(Note note) {
        textTV.setText(note.getText());
        titleTV.setText(note.getTitle());
    }

    public void editNote (FragmentManager fragmentManager) {
        NoteDialogFragment noteDialogFragment = new NoteDialogFragment();
        noteDialogFragment.show(fragmentManager, NOTE_DIALOG);
    }


}
