package c4q.notepad;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import c4q.notepad.model.Note;
import c4q.notepad.recyclerhelpers.NoteAdapter;

public class MainActivity extends AppCompatActivity {

    private static final String NOTE_DIALOG = "NoteDialog";

    private RecyclerView noteRecyclerView;
    private NoteAdapter noteAdapter;
    private FloatingActionButton floationgActionButton;
    private FragmentManager fragmentManager;

    private List<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notes = new ArrayList<>();
        fragmentManager = getSupportFragmentManager();

        noteRecyclerView = (RecyclerView) findViewById(R.id.notes_rv);
        noteRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        noteAdapter = new NoteAdapter(notes, fragmentManager);
        noteRecyclerView.setAdapter(noteAdapter);

        floationgActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floationgActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNoteDialog();
                addNewNote(v);
            }
        });

    }

    private void showNoteDialog() {
        FragmentManager manager = getSupportFragmentManager();
        NoteDialogFragment noteDialogFragment = new NoteDialogFragment();
        noteDialogFragment.show(manager, NOTE_DIALOG);
    }

    private void addNewNote(View v) {
        Note newNote = new Note();
        newNote.setText("This is my note.");
        newNote.setTitle("This is the note I created programaticlly");
        notes.add(newNote);
        noteAdapter.notifyDataSetChanged();
    }
}
