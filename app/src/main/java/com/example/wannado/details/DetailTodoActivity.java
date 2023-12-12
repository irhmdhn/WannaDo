package com.example.wannado.details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.wannado.R;
import com.example.wannado.model.TodoModel;

import org.w3c.dom.Text;

public class DetailTodoActivity extends AppCompatActivity {

    TextView tvTodoTitleDetail,tvTodoDescDetail,tvTodoDateDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_todo);

        TodoModel element = (TodoModel) getIntent().getSerializableExtra("TodoModel");
        if(element != null ){
            tvTodoTitleDetail = findViewById(R.id.tvTodoTitleDetail);
            tvTodoDescDetail = findViewById(R.id.tvTodoDescDetail);
            tvTodoDateDetail = findViewById(R.id.tvTodoDateDetail);

            tvTodoTitleDetail.setText(element.getTitle());
            tvTodoDescDetail.setText(element.getDesc());
            tvTodoDateDetail.setText(element.getDate());
        }

    }
}