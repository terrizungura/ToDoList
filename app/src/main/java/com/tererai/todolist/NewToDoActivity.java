package com.tererai.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewToDoActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.todolist.REPLY";
    public static final String EXTRA_DETAIL = "com.example.android.todolist.DETAIL";
    public static final String EXTRA_DATE = "com.example.android.todolist.DATE";
    public static final String EXTRA_ID = "com.example.android.todolist.ID";
    public static final String EXTRA_STATUS = "com.example.android.todolist.STATUS";

    @BindView(R.id.edit_todo) EditText mEditToDoView;
    @BindView(R.id.edit_todo_detail) EditText mEditToDoDetail;
    @BindView(R.id.edit_todo_date) EditText mEditToDoDate;
    @BindView(R.id.button_save) Button button;

    DatePickerDialog picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_to_do);

        ButterKnife.bind(this);

    }

        @OnClick(R.id.edit_todo_date)
        public void editDate () {
            final Calendar cldr = Calendar.getInstance();
            int day = cldr.get(Calendar.DAY_OF_MONTH);
            int month = cldr.get(Calendar.MONTH);
            int year = cldr.get(Calendar.YEAR);
            // date picker dialog
            picker = new DatePickerDialog(NewToDoActivity.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            mEditToDoDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        }
                    }, year, month, day);
            picker.show();
        }

        @OnClick(R.id.button_save)
        public void saveToDo(View v) {
            Intent replyIntent = new Intent();
            if(TextUtils.isEmpty(mEditToDoView.getText())){
                setResult(RESULT_CANCELED, replyIntent);
            }else{
                String toDo = mEditToDoView.getText().toString();
                String toDoDetail = mEditToDoDetail.getText().toString();
                String toDoDate =  mEditToDoDate.getText().toString();
                String toDoID =  UUID.randomUUID().toString();

                replyIntent.putExtra(EXTRA_REPLY, toDo);
                replyIntent.putExtra(EXTRA_DETAIL, toDoDetail);
                replyIntent.putExtra(EXTRA_DATE, toDoDate);
                replyIntent.putExtra(EXTRA_ID, toDoID);
                replyIntent.putExtra(EXTRA_STATUS, false);
                setResult(RESULT_OK, replyIntent);

            }
            finish();
        }
}
