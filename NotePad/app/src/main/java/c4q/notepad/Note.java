package c4q.notepad;

import java.util.Calendar;
import java.util.UUID;

/**
 * Created by maxrosado on 7/16/17.
 */

public class Note {

    private String mTitle;
    private String mText;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }
}
