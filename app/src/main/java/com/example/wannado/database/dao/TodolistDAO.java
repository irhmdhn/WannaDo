package com.example.wannado.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.wannado.database.entities.Todolist;
import com.example.wannado.database.entities.Todolist_item;

import java.util.Collection;
import java.util.List;

@Dao
public interface TodolistDAO {

    @Query("SELECT * FROM Todolist ORDER BY id DESC")
    List<Todolist> getTodolist();

    @Query("SELECT * FROM Todolist WHERE id=:id")
    Todolist getId(Long id);


    @Query("UPDATE Todolist SET title=:title WHERE id=:id")
    void update(Long id, String title);

    @Insert
    long insertAll(Todolist todolists);

    @Delete
    void delete(Todolist todolist);
}
