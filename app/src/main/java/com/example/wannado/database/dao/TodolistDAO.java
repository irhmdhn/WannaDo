package com.example.wannado.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.wannado.database.entities.Todolist;
import com.example.wannado.database.entities.Todolist_item;

import java.util.List;

@Dao
public interface TodolistDAO {

    @Query("SELECT * FROM Todolist")
    List<Todolist> getTodolist();

    @Query("SELECT * FROM Todolist WHERE id=:id")
    Todolist getId(int id);

//    @Query("SELECT * FROM Todolist_item WHERE todo_id=:todo_id")
//    Todolist_item getTodoItems(int todo_id);

    @Insert
    void insertAll(Todolist... todolists);

    @Delete
    void delete(Todolist todolist);
}
