package com.example.notesync;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView greetingText,htime,hdate;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        greetingText = findViewById(R.id.greeting_text);
        htime=findViewById(R.id.htime);
        hdate=findViewById(R.id.hdate);

        Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        String greetingMessage;

        if (hourOfDay >= 0 && hourOfDay < 12) {
            greetingMessage = "Good\nMorning👋";
        } else if (hourOfDay >= 12 && hourOfDay < 16) {
            greetingMessage = "Good\nAfternoon👋";
        } else {
            greetingMessage = "Good\nEvening👋";
        }

        greetingText.setText(greetingMessage);
        handler.postDelayed(dateRunnable, 0);

    }

    private Runnable dateRunnable = new Runnable() {
        @Override
        public void run() {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
            String hodate = dateFormat.format(calendar.getTime());
            String hotime = timeFormat.format(calendar.getTime());
            htime.setText(hotime);
            hdate.setText(hodate);
            handler.postDelayed(this, 1000);
        }
    };

    public void notewall(View view) {
        Intent intent = new Intent(MainActivity.this, NoteWallActivity.class);
        startActivity(intent);

    }

    public void addNote(View view) {
        Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
        startActivity(intent);


    }
}