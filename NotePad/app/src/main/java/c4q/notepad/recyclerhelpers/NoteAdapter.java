package c4q.notepad.recyclerhelpers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import c4q.notepad.model.Note;
import c4q.notepad.R;

/**
 * Created by maxrosado on 7/16/17.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {

    private List<Note> mNoteList;

    public NoteAdapter(List<Note> noteList) {
        mNoteList = noteList;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note_layout, parent, false);
        NoteViewHolder noteViewHolder = new NoteViewHolder(v);
        return noteViewHolder;
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        Note note = mNoteList.get(position);
        holder.bind(note);
    }

    @Override
    public int getItemCount() {
        return mNoteList.size();
    }
}
