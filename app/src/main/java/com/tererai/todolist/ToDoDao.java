package com.tererai.todolist;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ToDoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ToDo todo);

    @Query("DELETE FROM todo_table")
    void deleteAll();

    @Query("SELECT * from todo_table ORDER BY todo ASC")
    LiveData<List<ToDo>> getAllToDos();

    @Delete
    void deleteTaskById(ToDo toDo);

    @Query("UPDATE todo_table SET done_status = :done_status WHERE todoID = :todoID")
    void updateStatus(boolean done_status, String todoID);
   /* @Update
    void update(ToDo... todo);*/

}
