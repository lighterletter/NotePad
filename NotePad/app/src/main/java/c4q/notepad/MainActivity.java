package c4q.notepad;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import c4q.notepad.model.Note;
import c4q.notepad.recyclerhelpers.NoteAdapter;

public class MainActivity extends AppCompatActivity implements FinishedNoteListener {

    private static final String NOTE_DIALOG = "NoteDialog";
    private static final String TAG = MainActivity.class.getName(); // for logs.

    private RecyclerView noteRecyclerView;
    private NoteAdapter noteAdapter;
    private FloatingActionButton floationgActionButton;
    private List<Note> notes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notes = new ArrayList<>();
        noteRecyclerView = (RecyclerView) findViewById(R.id.notes_rv);
        noteRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        noteAdapter = new NoteAdapter(notes, this);
        noteRecyclerView.setAdapter(noteAdapter);

        floationgActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floationgActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNoteDialog();
            }
        });
    }

    private void showNoteDialog() {
        FragmentManager manager = getSupportFragmentManager();
        NoteDialogFragment noteDialogFragment = new NoteDialogFragment();
        noteDialogFragment.setfinishedNoteListener(this);
        noteDialogFragment.show(manager, NOTE_DIALOG);
    }

    @Override
    public void createNewNote(Note note) {
        notes.add(note);
        noteAdapter.notifyDataSetChanged();
    }
}

