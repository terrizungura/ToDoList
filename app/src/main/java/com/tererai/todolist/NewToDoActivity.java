package com.tererai.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import java.util.Date;

public class NewToDoActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.todolist.REPLY";
    public static final String EXTRA_DETAIL = "com.example.android.todolist.REPLY";
    public static final String EXTRA_DATE = "com.example.android.todolist.REPLY";

    private EditText mEditToDoView;
    private EditText mEditToDoDetail;
    private EditText mEditToDoDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_to_do);

        mEditToDoView = findViewById(R.id.edit_todo);
        mEditToDoDetail = findViewById(R.id.edit_todo_detail);
        mEditToDoDate = findViewById(R.id.edit_todo_date);
        final Button button = findViewById(R.id.button_save);

        mEditToDoDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                if(TextUtils.isEmpty(mEditToDoView.getText())){
                    setResult(RESULT_CANCELED, replyIntent);
                }else{
                    String toDo = mEditToDoView.getText().toString();
                    String toDoDetail = mEditToDoDetail.getText().toString();
                    String toDoDate =  mEditToDoDate.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, toDo);
                    replyIntent.putExtra(EXTRA_DETAIL, toDoDetail);
                    replyIntent.putExtra(EXTRA_DATE, toDoDate);
                    setResult(RESULT_OK, replyIntent);

                }
                finish();
            }
        });
    }
}
