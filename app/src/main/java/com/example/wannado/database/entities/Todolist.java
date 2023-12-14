package com.example.wannado.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

@Entity
public class Todolist {
    @PrimaryKey(autoGenerate = true)
    public Long id;

    public String title;
}


