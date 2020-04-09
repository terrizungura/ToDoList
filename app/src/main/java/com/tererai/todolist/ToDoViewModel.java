package com.tererai.todolist;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ToDoViewModel extends AndroidViewModel {

    private ToDoRepository mToDoRepository;
    private LiveData<List<ToDo>> mAllToDos;

    public ToDoViewModel (Application application){
        super(application);
        mToDoRepository = new ToDoRepository(application);
        mAllToDos = mToDoRepository.getAllToDos();
    }

    public LiveData<List<ToDo>> getAllToDos() {
        return mAllToDos;
    }

    public void insert(ToDo toDo){
        mToDoRepository.insert(toDo);
    }

    public void deleteTaskById(ToDo toDo){
        mToDoRepository.deleteTaskById(toDo);
    }

    public void updateStatus(ToDo toDo){
        mToDoRepository.updateStatus(toDo);
    }
}
