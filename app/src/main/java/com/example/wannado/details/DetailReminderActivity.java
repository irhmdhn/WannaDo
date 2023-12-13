package com.example.wannado.details;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

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
    RelativeLayout pickDate, pickTime;

    public String checkDigit(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_reminder);
        btnBack = findViewById(R.id.btnBack);
        etReminderTitleDetail = findViewById(R.id.etReminderTitleDetail);
        tvReminderDateDetail = findViewById(R.id.tvReminderDateDetail);
        tvReminderTimeDetail = findViewById(R.id.tvReminderTimeDetail);
        pickDate = findViewById(R.id.pickDate);
        pickTime = findViewById(R.id.pickTime);

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


        ReminderModel element = (ReminderModel) getIntent().getSerializableExtra("ReminderModel");
        if(element != null ){
            etReminderTitleDetail.setText(element.getTitle());
            tvReminderDateDetail.setText(element.getDate());
            tvReminderTimeDetail.setText(element.getTime());
        }
    }
}