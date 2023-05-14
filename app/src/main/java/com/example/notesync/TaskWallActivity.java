package com.example.notesync;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;


public class TaskWallActivity extends AppCompatActivity implements OnTaskClickListener {

    List<Task> taskList;
    TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_wall);

        taskList = getAllTasksFromDatabase();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        adapter = new TaskAdapter(taskList);
        adapter.setOnTaskClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private List<Task> getAllTasksFromDatabase() {
        List<Task> taskList;
        DbHelper dbHelper = new DbHelper(this);
        taskList = dbHelper.getAllTasks();
        return taskList;
    }

    @Override
    public void onTaskClick(int position) {
        deleteTaskFromDatabase(position);
    }

    @Override
    public void onDeleteClick(int position) {

        deleteTaskFromDatabase(position);
    }

    private void deleteTaskFromDatabase(int position) {
        Task task = taskList.get(position);
        DbHelper dbHelper = new DbHelper(this);
        dbHelper.deleteTask(task.getId());

        taskList.remove(position);
        adapter.notifyItemRemoved(position);
    }

}
