package com.example.notesync;

public class Note {
    private int id;
    private String title;
    private String note;
    private String time;
    private String date;

    public Note(int id, String title, String note, String time, String date) {
        this.id = id;
        this.title = title;
        this.note = note;
        this.time = time;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getNote() {
        return note;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }
}
