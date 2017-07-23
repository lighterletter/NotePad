package c4q.notepad.recyclerhelpers;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import c4q.notepad.model.Note;
import c4q.notepad.R;

/**
 * Created by maxrosado on 7/16/17.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {

    private List<Note> mNoteList;
    private FragmentManager mFragmentManager;

    public NoteAdapter(List<Note> noteList) {
        mNoteList = noteList;
    }

    public NoteAdapter(List<Note>data, FragmentManager fragmentManager){
        this.mNoteList = data;
        this.mFragmentManager = fragmentManager;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NoteViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note_layout, parent, false), this.mFragmentManager);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        holder.bind(mNoteList.get(position));
    }

    @Override
    public int getItemCount() {
        return mNoteList.size();
    }
}
