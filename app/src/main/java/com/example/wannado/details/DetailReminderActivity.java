package com.example.wannado.details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wannado.R;
import com.example.wannado.model.NotepadModel;
import com.example.wannado.model.ReminderModel;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DetailReminderActivity extends AppCompatActivity {
    Button btnBack;
    EditText etReminderTitleDetail;
    TextView tvReminderDateDetail, tvReminderTimeDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_reminder);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

//        Date
        String currentDate = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(new Date());
//        String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
        tvReminderDateDetail.setText(currentDate);
//        tvReminderTimeDetail.setText(currentTime);

////        Time
//        LocalDateTime currentTime = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
//        String formattedTime = currentTime.format(formatter);
//        tvReminderTimeDetail.setText(formattedTime);

        etReminderTitleDetail = findViewById(R.id.etReminderTitleDetail);
        tvReminderDateDetail = findViewById(R.id.tvReminderDateDetail);
        tvReminderTimeDetail = findViewById(R.id.tvReminderTimeDetail);

        ReminderModel element = (ReminderModel) getIntent().getSerializableExtra("ReminderModel");
        if(element != null ){
            etReminderTitleDetail.setText(element.getTitle());
            tvReminderDateDetail.setText(element.getDate());
            tvReminderTimeDetail.setText(element.getTime());
        }
    }
}