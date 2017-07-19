package c4q.notepad;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by maxrosado on 7/16/17.
 */

public class NoteViewHolder extends RecyclerView.ViewHolder {

    TextView mTitle;
    TextView mText;
    Note mNote;
    CardView mCardView;


    public NoteViewHolder(View parent) {
        super(parent);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "You clicked me!", Toast.LENGTH_SHORT).show();
            }
        });

        mCardView = (CardView) itemView.findViewById(R.id.item_note);
        mTitle = (TextView) itemView.findViewById(R.id.title_tv);
        mText = (TextView) itemView.findViewById(R.id.text_tv);
    }

    public void bind(Note note) {
        mNote = note;
        mText.setText(note.getText());
        mTitle.setText(note.getTitle());
    }
}
