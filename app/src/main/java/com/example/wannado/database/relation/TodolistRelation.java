package com.example.wannado.database.relation;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.wannado.database.entities.Todolist;
import com.example.wannado.database.entities.Todolist_item;

import java.util.List;


public class TodolistRelation {
    @Embedded public Todolist todolist;
    @Relation(
            parentColumn = "id",
            entityColumn = "todo_id"
    )
    public List<TodolistRelation> todolistRelations;
}
