package com.example.notesync;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteWallActivity extends AppCompatActivity implements NoteAdapter.OnNoteClickListener {
    private NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_wall);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        @SuppressWarnings("resource") DbHelper dbHelper = new DbHelper(this);

        List<Note> noteList = dbHelper.getAllNotes();

        noteAdapter = new NoteAdapter(noteList, this);
        recyclerView.setAdapter(noteAdapter);

        long deletedNoteId = getIntent().getLongExtra("deletedNoteId", -1);
        if (deletedNoteId != -1) {
            int deletedNoteIndex = -1;
            for (int i = 0; i < noteList.size(); i++) {
                if (noteList.get(i).getId() == deletedNoteId) {
                    deletedNoteIndex = i;
                    break;
                }
            }

            if (deletedNoteIndex != -1) {
                noteList.remove(deletedNoteIndex);
                noteAdapter.notifyItemRemoved(deletedNoteIndex);
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }


    @Override
    public void onNoteClick(int position) {
        Note clickedNote = noteAdapter.getItem(position);
        if (clickedNote != null) {
            long noteId = clickedNote.getId();
            openNoteDetailActivity(noteId);
        }
    }

    private void openNoteDetailActivity(long noteId) {
        Intent intent = new Intent(this, Notedesc.class);
        intent.putExtra("noteId", noteId);
        startActivity(intent);
    }

    public void addnotebtn(View view) {
        Intent intent = new Intent(this, AddNoteActivity.class);
        startActivity(intent);
    }
}
