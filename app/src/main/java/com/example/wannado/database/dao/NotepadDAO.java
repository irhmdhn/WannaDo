package com.example.wannado.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.wannado.database.entities.Notepad;

import java.util.List;

@Dao
public interface NotepadDAO {
    @Query("SELECT * FROM Notepad ORDER BY id DESC")
    List<Notepad> getNotepad();

    @Query("SELECT * FROM Notepad WHERE id=:id")
    Notepad getById(int id);
    @Query("UPDATE Notepad SET title=:title, desc=:desc, date=:date, time=:time WHERE id=:id")
    void update(int id, String title, String desc, String date, String time);

    @Insert
    void insertAll(Notepad notepad);

    @Delete
    void delete(Notepad notepad);
}
