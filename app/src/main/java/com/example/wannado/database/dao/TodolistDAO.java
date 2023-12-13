package com.example.wannado.database.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.wannado.database.relation.TodolistRelation;

import java.util.List;

@Dao
public interface TodolistDAO {
    @Transaction
    @Query("SELECT * FROM Todolist")
    public List<TodolistRelation> getTodolistItems();
}
