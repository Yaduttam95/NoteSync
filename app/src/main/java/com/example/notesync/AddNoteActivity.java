package com.example.notesync;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddNoteActivity extends AppCompatActivity {

    private TextView htime;
    private EditText titleText;
    private EditText noteText;
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        htime=findViewById(R.id.htime);
        noteText=findViewById(R.id.editTextText2);
        titleText=findViewById(R.id.editText);
        handler.postDelayed(dateRunnable, 0);


    }

    private final Runnable dateRunnable = new Runnable() {
        @Override
        public void run() {
            Calendar calendar = Calendar.getInstance();
            @SuppressLint("SimpleDateFormat") SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
            String hotime = timeFormat.format(calendar.getTime());
            htime.setText(hotime);
            handler.postDelayed(this, 1000);
        }
    };
    @SuppressWarnings("resource")
    public void addNote(View view) {
        String title = titleText.getText().toString().trim();
        String note = noteText.getText().toString().trim();
        String currentTime = htime.getText().toString().trim();
        String currentDate = getCurrentDate().trim();

        if (!title.isEmpty() && !note.isEmpty() && !currentTime.isEmpty() && !currentDate.isEmpty()) {
            DbHelper dbHelper = new DbHelper(this);
            dbHelper.addNote(title, note, currentTime, currentDate);
            System.out.println(currentDate);

            titleText.setText("");
            noteText.setText("");

            Toast.makeText(this, "Note added successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please enter all the information", Toast.LENGTH_SHORT).show();
        }
    }

    public String getCurrentDate() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        return dateFormat.format(currentDate);
    }

    public void disbtn(View view) {
        titleText.setText("");
        noteText.setText("");
        Toast.makeText(this, "Note discarded successfully", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(AddNoteActivity.this, MainActivity.class);
        startActivity(intent);
    }
}