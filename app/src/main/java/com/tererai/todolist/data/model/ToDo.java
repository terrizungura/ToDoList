package com.tererai.todolist.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todo_table")
public class ToDo {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "todoID")
    private String mTodoID;

    @NonNull
    @ColumnInfo(name = "todo")
    private String mTodo;

    @NonNull
    @ColumnInfo(name = "todo_detail")
    private String mTodoDetail;

    @NonNull
    @ColumnInfo(name = "date")
    private String mDate;

    @NonNull
    @ColumnInfo(name = "done_status")
    private boolean mDoneStatus;

    public ToDo(@NonNull String todoID, @NonNull String todo, @NonNull String todoDetail, @NonNull String date, @NonNull boolean doneStatus) {
        this.mTodoID = todoID;
        this.mTodo = todo;
        this.mTodoDetail = todoDetail;
        this.mDate = date;
        this.mDoneStatus = doneStatus;
    }

    public void setTodoID(@NonNull String mTodoID) {
        this.mTodoID = mTodoID;
    }

    public void setTodo(@NonNull String mTodo) {
        this.mTodo = mTodo;
    }

    public void setTodoDetail(String todo_detail) {
        this.mTodoDetail = todo_detail;
    }

    public void setDate(String date) {
        this.mDate = date;
    }

    public void setDoneStatus(boolean done_status) {
        this.mDoneStatus = done_status;
    }

    public String getTodoID() {
        return this.mTodoID;
    }

    public String getTodo() {
        return this.mTodo;
    }

    public String getTodoDetail() {
        return mTodoDetail;
    }

    public String getDate() {
        return mDate;
    }

    public boolean getDoneStatus() {
        return this.mDoneStatus;
    }
}
