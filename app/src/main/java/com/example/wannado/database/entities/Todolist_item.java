package com.example.wannado.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Todolist_item{
    @PrimaryKey(autoGenerate = true)
    public Long id;

    public String item;
    @ColumnInfo(name = "is_check")
    public Boolean isCheck = false;
    public Long todo_id;
}
