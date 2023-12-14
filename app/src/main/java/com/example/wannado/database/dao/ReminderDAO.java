package com.example.wannado.database.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Insert;
import androidx.room.Delete;
import androidx.room.Update;
import androidx.lifecycle.LiveData;

import com.example.wannado.database.entities.Reminder;

import java.util.List;

@Dao
public interface ReminderDAO {
    @Query("SELECT * FROM Reminder")
    List<Reminder> getReminder();

    @Query("SELECT * FROM Reminder WHERE id=:id")
    Reminder getId(int id);

    @Insert
    void insertAll(Reminder... reminders);
}
