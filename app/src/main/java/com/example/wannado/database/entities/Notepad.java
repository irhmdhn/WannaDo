package com.example.wannado.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Notepad {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;
    public String desc;
    public String date;
    public String time;
}
