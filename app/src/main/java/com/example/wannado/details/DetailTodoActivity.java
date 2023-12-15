package com.example.wannado.details;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wannado.MainActivity;
import com.example.wannado.R;
import com.example.wannado.adapter.TodoAdapter;
import com.example.wannado.adapter.TodoItemsAdapter;
import com.example.wannado.database.AppDatabase;
import com.example.wannado.database.entities.Todolist;
import com.example.wannado.database.entities.Todolist_item;
import com.example.wannado.model.TodoModel;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class DetailTodoActivity extends AppCompatActivity {

    TextView etTodoTitle;
    AppDatabase database;
    TodoAdapter todoAdapter;
    TodoItemsAdapter todoItemsAdapter;
    Button btnBack;
//    FloatingActionButton btnAddItemTodo;
    Todolist todolist;
    List<Todolist> todolists;
    List<Todolist_item> todolistItems;
    MaterialAlertDialogBuilder dialog;
    FloatingActionButton btnEditTitle;
    ExtendedFloatingActionButton btnAddItemTodo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_todo);

        btnBack = findViewById(R.id.btnBack);
        btnAddItemTodo = findViewById(R.id.btnAddItemTodo);
        btnEditTitle = findViewById(R.id.btnEditTitle);


        Intent intent = getIntent();
        Long id = intent.getLongExtra("id",0);
        database = AppDatabase.getInstance(getApplicationContext());
        todolist = database.todolistDAO().getId(id);

//        SET TITLE
        etTodoTitle = findViewById(R.id.etTodoTitle);
        etTodoTitle.setText(todolist.title);

//        TODOLISTS
        todolists = new ArrayList<>();
        todolists.clear();
        todolists.addAll(database.todolistDAO().getTodolist());
        todoAdapter = new TodoAdapter(todolists,getApplicationContext(),null);

//        TODOLIST ITEM
        todolistItems = new ArrayList<>();
        todolistItems.clear();
        todolistItems.addAll(database.todolistItemDAO().getTodolistItems(id));
        todoItemsAdapter = new TodoItemsAdapter(todolistItems, getApplicationContext(),null);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnAddItemTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabAddClick(id);
            }
        });

        btnEditTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabEditTitle(id);
            }
        });

        todoItemsAdapter.setDialog(new TodoItemsAdapter.MaterialAlertDialogBuilder() {
            @Override
            public void onClick(int position) {
                Todolist_item todolistItem = todolistItems.get(position);
                dialog = new MaterialAlertDialogBuilder(DetailTodoActivity.this);
                dialog.setTitle("Ubah item");

                final TextInputEditText input = new TextInputEditText(DetailTodoActivity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                input.setPadding(48,48,48,48);
                input.setHint("Aktivitas");
                input.setText(todolistItem.item);

                dialog.setView(input);

                dialog.setPositiveButton("Ubah", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        database.todolistItemDAO().updateItem(todolistItem.id, input.getText().toString());
                        onStart();
                    }
                });
                dialog.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = dialog.show();
                Button pBtn = alert.getButton(DialogInterface.BUTTON_POSITIVE);
                pBtn.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.blue));
                pBtn.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
            }
            @Override
            public void onHold(int position) {
                dialog = new MaterialAlertDialogBuilder(DetailTodoActivity.this);
                dialog.setTitle("Hapus item");
                dialog.setMessage("Yakin untuk menghapus item ini?");
                dialog.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Todolist_item todolistItem = todolistItems.get(position);
                        database.todolistItemDAO().delete(todolistItem);
                        onStart();
                    }
                });
                dialog.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = dialog.show();
                Button pBtn = alert.getButton(DialogInterface.BUTTON_POSITIVE);
                pBtn.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                pBtn.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
            }
        });

//        todoItemsAdapter.setIsCheck(new TodoItemsAdapter.MaterialCheckBox() {
//            @Override
//            public void onChange(int position) {
//                if(isChecked){
//                    tvTodoItem.setPaintFlags(tvTodoItem.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//                    database.todolistItemDAO().updateIsCheck();
//
//                }
//            }
//        });

        RecyclerView recyclerView = findViewById(R.id.rvTodoItem);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(todoItemsAdapter);
    }



    public Dialog fabEditTitle(Long id) {
        MaterialAlertDialogBuilder dialogBuilder = (new MaterialAlertDialogBuilder(DetailTodoActivity.this));
        dialogBuilder.setTitle("Ubah judul");

        final TextInputEditText input = new TextInputEditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setPadding(48,48,48,48);
        input.setText(todolist.title);
        input.setHint("Judul kegiatan");
        dialogBuilder.setView(input);

        dialogBuilder.setPositiveButton("Ubah", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                database.todolistDAO().update(id, input.getText().toString());
                dialog.dismiss();
                todolist = database.todolistDAO().getId(id);
                etTodoTitle.setText(todolist.title);
            }
        });
        dialogBuilder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        return dialogBuilder.show();
    }

    public Dialog fabAddClick(Long id) {

        MaterialAlertDialogBuilder dialogBuilder = (new MaterialAlertDialogBuilder(DetailTodoActivity.this));
        dialogBuilder.setTitle("Mau apa nih?");

        final TextInputEditText input = new TextInputEditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setPadding(48,48,48,48);
        input.setHint("Aktivitas kamu");
        dialogBuilder.setView(input);

        dialogBuilder.setPositiveButton("Tambahkan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Todolist_item todolistItem = new Todolist_item();
                todolistItem.item = input.getText().toString();
                todolistItem.todo_id = id;
                database.todolistItemDAO().insertAll(todolistItem);
                onStart();
                dialog.dismiss();
            }
        });
        dialogBuilder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        return dialogBuilder.show();
    }


    @Override
    protected void onStart() {
        super.onStart();
        todolistItems.clear();
        todolistItems.addAll(database.todolistItemDAO().getAllTodolistItem());
        todoItemsAdapter.notifyDataSetChanged();
    }
}