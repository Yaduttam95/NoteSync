package com.example.notesync;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView htime;
    private TextView hdate;
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView greetingText = findViewById(R.id.greeting_text);
        htime=findViewById(R.id.htime);
        hdate=findViewById(R.id.hdate);

        Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        String greetingMessage;

        if (hourOfDay < 12) {
            greetingMessage = "Good\nMorning👋";
        } else if (hourOfDay < 16) {
            greetingMessage = "Good\nAfternoon👋";
        } else {
            greetingMessage = "Good\nEvening👋";
        }

        greetingText.setText(greetingMessage);
        handler.postDelayed(dateRunnable, 0);

    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    private final Runnable dateRunnable = new Runnable() {
        @Override
        public void run() {
            Calendar calendar = Calendar.getInstance();
            @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("MMM-d");
            @SuppressLint("SimpleDateFormat") SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
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

    public void taskWall(View view) {
        Intent intent = new Intent(MainActivity.this, TaskWallActivity.class);
        startActivity(intent);
    }

    public void addTask(View view) {
        Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
        startActivity(intent);
    }


    private void showInfoBox() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        @SuppressLint("InflateParams") View bottomSheetView = getLayoutInflater().inflate(R.layout.bottomsheet, null);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }

    public void bottom(View view) {
        showInfoBox();
    }

}