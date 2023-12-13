package com.example.wannado.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Todolist_item{
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String item;
    public int todo_id;
}
