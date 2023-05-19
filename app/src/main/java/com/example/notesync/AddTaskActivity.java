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

public class AddTaskActivity extends AppCompatActivity {


    private TextView htime;
    private EditText titleText;
    private EditText taskText;
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        htime=findViewById(R.id.htime);
        taskText=findViewById(R.id.editTextText2);
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


    public void addbtn(View view) {
        String title = titleText.getText().toString().trim();
        String task = taskText.getText().toString().trim();

        if (!title.isEmpty() && !task.isEmpty()) {
            try (DbHelper dbHelper = new DbHelper(this)) {
                dbHelper.addTask(title, task);
            }

            titleText.setText("");
            taskText.setText("");

            Toast.makeText(this, "Task added successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Please enter all the information", Toast.LENGTH_SHORT).show();
        }


    }

    public void disbtn(View view) {
        titleText.setText("");
        taskText.setText("");
        Toast.makeText(this, "Task discarded successfully", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}