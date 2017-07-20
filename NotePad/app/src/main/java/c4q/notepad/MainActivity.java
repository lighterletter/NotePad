package c4q.notepad;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import c4q.notepad.model.Note;
import c4q.notepad.recyclerhelpers.NoteAdapter;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private NoteAdapter mAdapter;
    private FloatingActionButton mFloatingActionButton;
    private List<Note> mNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNotes = new ArrayList<>();

        mRecyclerView = (RecyclerView) findViewById(R.id.notes_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new NoteAdapter(mNotes);
        mRecyclerView.setAdapter(mAdapter);

        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewNote(v);
            }
        });
    }

    private void addNewNote(View v) {
        Note newNote = new Note();
        newNote.setText("This is my note.");
        newNote.setTitle("This is the note I created programaticlly");
        mNotes.add(newNote);
        mAdapter.notifyDataSetChanged();
        Snackbar.make(v, "Created new Note!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }
}
