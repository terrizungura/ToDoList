package com.tererai.todolist;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todo_table")
public class ToDo {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "todo")
    private String mTodo;

    public ToDo(@NonNull String todo){
        this.mTodo=todo;
    }

   /* @NonNull
    @ColumnInfo(name = "done_status")
    private boolean mDoneStatus=false;

    @ColumnInfo(name = "todo_detail")
    private String mTodoDetail;*/

    public String getTodo(){
        return this.mTodo;
    }

   /* public boolean getDoneStatus(){
        return this.mDoneStatus;
    }

    public String getTodoDetail() {
        return mTodoDetail;
    }*/
}
