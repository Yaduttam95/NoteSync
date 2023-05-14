package com.example.notesync;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

@SuppressWarnings("resource")
public class Notedesc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notedesc);

        TextView textTitle = findViewById(R.id.textTitle);
        TextView textNote = findViewById(R.id.textNote);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // Declare a variable to store the noteId
            long noteId = extras.getLong("noteId", -1); // Retrieve the noteId from extras
            if (noteId != -1) {
                // Use the noteId to retrieve the note details from the database
                DbHelper dbHelper = new DbHelper(this);
                Note note = dbHelper.getNoteById(noteId);
                if (note != null) {
                    textTitle.setText(note.getTitle());
                    textNote.setText(note.getNote());
                }
            }
        }
    }

    public void backbtn(View view) {
        finish();
    }

    @SuppressWarnings("resource")
    public void deletenote(View view) {
        long noteId = getIntent().getLongExtra("noteId", -1);
        DbHelper dbHelper = new DbHelper(this);
        dbHelper.deleteNote();

        Intent intent = new Intent(Notedesc.this, NoteWallActivity.class);
        intent.putExtra("deletedNoteId", noteId);
        startActivity(intent);
        finish();
    }

}
