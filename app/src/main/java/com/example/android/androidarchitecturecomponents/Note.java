package com.example.android.androidarchitecturecomponents;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//table name
@Entity(tableName = "note_table")
public class Note {
    //pry key
    @PrimaryKey(autoGenerate = true)
    private int id;

    //member variables for the table

    private String title;
    private String description;
    private int priority;

    public Note(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    //allows for the id to be recreated
    public void setId(int id) {
        this.id = id;
    }

    //in order to ensure persistence add getter methods


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}
