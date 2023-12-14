package com.example.wannado.details;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wannado.R;
import com.example.wannado.database.AppDatabase;
import com.example.wannado.database.entities.Todolist;
import com.example.wannado.model.TodoModel;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;

import org.w3c.dom.Text;

public class DetailTodoActivity extends AppCompatActivity {

    EditText etTodoTitle;
    AppDatabase database;
    Button btnBack;
    int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_todo);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        database = AppDatabase.getInstance(getApplicationContext());

        if (id > 0 ){
            Todolist todolist = database.todolistDAO().getId(id);
            etTodoTitle = findViewById(R.id.etTodoTitle);
            etTodoTitle.setText(todolist.title);

        }

//        Todolist element = (Todolist) getIntent().getSerializableExtra("TodoModel");
//        Log.i("TES","id nya = "+element);
//        if(element != null ){
//        }


    }
}