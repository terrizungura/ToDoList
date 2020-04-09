package com.tererai.todolist;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "todo_table")
public class ToDo {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "todo")
    private String mTodo;

    @NonNull
    @ColumnInfo(name = "done_status")
    private boolean mDoneStatus=false;

    @ColumnInfo(name = "todo_detail")
    private String mTodoDetail;

    @ColumnInfo(name="date")
    private String mDate;

    public ToDo(@NonNull String todo){
        this.mTodo=todo;
    }

    public void setDoneStatus(boolean done_status) {
        this.mDoneStatus = done_status;
    }

    public void setTodoDetail(String todo_detail) {
        this.mTodoDetail = todo_detail;
    }

    public void setDate(String date) {
        this.mDate = date;
    }

    public String getTodo(){
        return this.mTodo;
    }

    public boolean getDoneStatus(){
        return this.mDoneStatus;
    }

    public String getTodoDetail() {
        return mTodoDetail;
    }

    public String getDate() {
        return mDate;
    }
}
