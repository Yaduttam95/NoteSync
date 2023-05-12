package com.example.notesync;

public class Note {
    private final int id;
    private final String title;
    private final String note;

    public Note(int id, String title, String note) {
        this.id = id;
        this.title = title;
        this.note = note;
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

}
