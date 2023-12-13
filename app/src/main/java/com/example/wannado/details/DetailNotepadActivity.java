package com.example.wannado.details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wannado.R;
import com.example.wannado.model.NotepadModel;
import com.example.wannado.model.TodoModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetailNotepadActivity extends AppCompatActivity {

    Button btnBack;
    EditText etNotepadTitle,etNotepadDesc;
    TextView tvDate, tvCharCounter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_notepad);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        String currentDate = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(new Date());

        etNotepadTitle = findViewById(R.id.etNotepadTitle);
        etNotepadDesc = findViewById(R.id.etNotepadDesc);
        tvDate = findViewById(R.id.tvDate);

//      Date
        tvDate.setText(currentDate);
//      Char counter
        etNotepadDesc.addTextChangedListener(mTextEditorWatcher);

        NotepadModel element = (NotepadModel) getIntent().getSerializableExtra("NotepadModel");
        if(element != null ){
            etNotepadTitle.setText(element.getTitle());
            etNotepadDesc.setText(element.getDesc());
            tvDate.setText(element.getDate());
        }
    }

    public final TextWatcher mTextEditorWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {

            //This sets a textview to the current length
            tvCharCounter = findViewById(R.id.tvCharCounter);
            tvCharCounter.setText(String.valueOf(s.length()));
        }

        public void afterTextChanged(Editable s) {
        }
    };

}