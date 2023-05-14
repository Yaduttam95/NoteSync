package com.example.notesync;

public class Task {
    private int id;
    private String title;
    private String task;

    public Task(int id, String title, String task) {
        this.id = id;
        this.title = title;
        this.task = task;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTask() {
        return task;
    }
}

