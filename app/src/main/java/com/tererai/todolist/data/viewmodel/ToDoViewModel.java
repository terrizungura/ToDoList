package com.tererai.todolist.data.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.tererai.todolist.data.model.ToDo;
import com.tererai.todolist.data.repository.ToDoRepository;

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
