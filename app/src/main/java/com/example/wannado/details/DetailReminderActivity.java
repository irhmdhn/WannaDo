package com.example.wannado.details;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.wannado.R;
//import com.example.wannado.model.NotepadModel;
//import com.example.wannado.model.ReminderModel;
import com.example.wannado.adapter.ReminderAdapter;
import com.example.wannado.database.AppDatabase;
import com.example.wannado.database.entities.Notepad;
import com.example.wannado.database.entities.Reminder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DetailReminderActivity extends AppCompatActivity {
    Button btnBack;
    FloatingActionButton btnSave;
    EditText etReminderTitleDetail;
    TextView tvReminderDateDetail, tvReminderTimeDetail;
    RelativeLayout pickDate, pickTime;
    AppDatabase database;
    ReminderAdapter reminderAdapter;
    int id = 0;

    public String checkDigit(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_reminder);
        btnBack = findViewById(R.id.btnBack);
        btnSave = findViewById(R.id.btnSave);
        etReminderTitleDetail = findViewById(R.id.etReminderTitleDetail);
        tvReminderDateDetail = findViewById(R.id.tvReminderDateDetail);
        tvReminderTimeDetail = findViewById(R.id.tvReminderTimeDetail);
        pickDate = findViewById(R.id.pickDate);
        pickTime = findViewById(R.id.pickTime);
        database = AppDatabase.getInstance(getApplicationContext());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //Date
        String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        tvReminderDateDetail.setText(currentDate);

        //Time
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattedTime = currentTime.format(formatter);
        tvReminderTimeDetail.setText(formattedTime);

        //DatePicker
        pickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        DetailReminderActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                tvReminderDateDetail.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        },
                        year, month, day);
                datePickerDialog.show();
            }
        });

        //TimePicker
        pickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(DetailReminderActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                tvReminderTimeDetail.setText(checkDigit(hourOfDay) + ":" + checkDigit(minute));

                            }
                        }, hour, minute, false);
                timePickerDialog.show();
            }
        });

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        database = AppDatabase.getInstance(getApplicationContext());

        if(id > 0 ){
            Reminder reminder = database.reminderDAO().getId(id);
            etReminderTitleDetail.setText(reminder.title);
            tvReminderDateDetail.setText(reminder.date);
            tvReminderTimeDetail.setText(reminder.time);
//        }
//
//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Reminder reminder = new Reminder();
//                reminder.title = etReminderTitleDetail.getText().toString();
//                reminder.date = tvReminderDateDetail.getText().toString();
//                reminder.time = tvReminderTimeDetail.getText().toString();
////                reminder.repeat = tvReminderRepeatDetail.getText().toString();
//                database.reminderDAO().insertAll(reminder);
//                finish();
//            }
//        });

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    database.reminderDAO().update(
                            id,
                            etReminderTitleDetail.getText().toString(),
                            tvReminderDateDetail.getText().toString(),
                            tvReminderTimeDetail.getText().toString()
                    );
                    onStart();
                    Toast.makeText(DetailReminderActivity.this, "Pengingat di diubah", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
            });
        }else {
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Reminder reminder = new Reminder();
                    reminder.title = etReminderTitleDetail.getText().toString();
                    reminder.date = tvReminderDateDetail.getText().toString();
                    reminder.time = tvReminderTimeDetail.getText().toString();
                    database.reminderDAO().insertAll(reminder);
                    onStart();
                    Toast.makeText(DetailReminderActivity.this, "Pengingat di Tambahkan", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
            });
        }
    }
}