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
public interface TodolistItemDAO {
    @Query("SELECT * FROM todolist_item")
    List<Todolist_item> getAllTodolistItem();
    @Query("SELECT * FROM Todolist_item WHERE todo_id=:id")
    List<Todolist_item> getTodolistItems(Long id);
    @Query("DELETE FROM Todolist_item WHERE todo_id=:id")
    void deletedParent(Long id);
    @Query("UPDATE todolist_item SET item=:item WHERE id=:id")
    void updateItem(Long id, String item);
    @Query("UPDATE todolist_item SET is_check=:isCheck WHERE id=:id")
    void updateIsCheck(Long id, Boolean isCheck);

    @Insert
    void insertAll(Todolist_item todolistItem);
    @Delete
    void delete(Todolist_item todolistItem);
}
