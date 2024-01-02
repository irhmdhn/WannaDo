package com.example.wannado.details;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wannado.R;
import com.example.wannado.adapter.NotepadAdapter;
import com.example.wannado.adapter.TodoAdapter;
import com.example.wannado.database.AppDatabase;
import com.example.wannado.database.entities.Notepad;
import com.example.wannado.database.entities.Reminder;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DetailNotepadActivity extends AppCompatActivity {

    Button btnBack;
    FloatingActionButton btnSave;
    EditText etNotepadTitle,etNotepadDesc;
    TextView tvDate, tvCharCounter, tvTime;
    AppDatabase database;
    Notepad notepad;
    List<Notepad> notepads;
    NotepadAdapter notepadAdapter;
    int id = 0;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_notepad);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        database = AppDatabase.getInstance(getApplicationContext());
        btnBack = findViewById(R.id.btnBack);
        btnSave = findViewById(R.id.btnSave);
        etNotepadTitle = findViewById(R.id.etNotepadTitle);
        etNotepadDesc = findViewById(R.id.etNotepadDesc);
        tvDate = findViewById(R.id.tvDate);
        tvTime = findViewById(R.id.tvTime);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        String currentDate = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(new Date());
        String currentTime = new SimpleDateFormat("hh:mm", Locale.getDefault()).format(new Date());

//      Date & Time
        tvDate.setText(currentDate);
        tvTime.setText(currentTime);

//      Char counter
        etNotepadDesc.addTextChangedListener(mTextEditorWatcher);
        etNotepadTitle.addTextChangedListener(mTextEditorWatcher);

        notepads = new ArrayList<>();
        notepads.clear();
        notepads.addAll(database.notepadDAO().getNotepad());
        notepadAdapter = new NotepadAdapter(notepads, getApplicationContext(),null);

        btnSave.setEnabled(false);
        btnSave.setVisibility(View.INVISIBLE);

        if(id > 0 ){
            notepad = database.notepadDAO().getById(id);
            etNotepadTitle.setText(notepad.title);
            etNotepadDesc.setText(notepad.desc);
            tvDate.setText(notepad.date);
            tvTime.setText(notepad.time);

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    database.notepadDAO().update(
                            id,
                            etNotepadTitle.getText().toString(),
                            etNotepadDesc.getText().toString(),
                            currentDate,
                            currentTime
                    );
                    onStart();
                    Toast.makeText(DetailNotepadActivity.this, "Catatan di diubah", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
            });
        }else {
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Notepad notepad = new Notepad();
                    notepad.title = etNotepadTitle.getText().toString();
                    notepad.desc = etNotepadDesc.getText().toString();
                    notepad.date = currentDate;
                    notepad.time = currentTime;
                    database.notepadDAO().insertAll(notepad);
                    onStart();
                    Toast.makeText(DetailNotepadActivity.this, "Catatan di Tambahkan", Toast.LENGTH_SHORT).show();
                    onBackPressed();

                }
            });
        }
    }

    public final TextWatcher mTextEditorWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (etNotepadDesc != null){
                tvCharCounter = findViewById(R.id.tvCharCounter);
                tvCharCounter.setText(String.valueOf(s.length()));
            }
//            !s.toString().trim().isEmpty() &&
//            if(s.length() > 0 ){
//                btnSave.setVisibility(View.VISIBLE);
//            }
            if (s.length() > 0) {
                btnSave.setEnabled(true);
                btnSave.setVisibility(View.VISIBLE);
            } else {
                btnSave.setEnabled(false);
                btnSave.setVisibility(View.INVISIBLE);
            }
        }

        public void afterTextChanged(Editable s) {
        }
    };
    @Override
    public void onStart() {
        super.onStart();
        notepads.clear();
        notepads.addAll(database.notepadDAO().getNotepad());
        notepadAdapter.notifyDataSetChanged();
    }
}