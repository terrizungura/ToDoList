package com.tererai.todolist;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ToDoRepository {

    private ToDoDao mToDoDao;
    private LiveData<List<ToDo>> mAllToDos;

    ToDoRepository(Application application){
        ToDoRoomDatabase db = ToDoRoomDatabase.getDatabase(application);
        mToDoDao=db.toDoDao();
        mAllToDos=mToDoDao.getAllToDos();
    }

    LiveData<List<ToDo>> getAllToDos(){
        return mAllToDos;
    }

    public void insert(ToDo todo){
        new insertAsyncTask(mToDoDao).execute(todo);
    }

    private static class insertAsyncTask extends AsyncTask<ToDo, Void, Void>{
        private ToDoDao mAsyncTaskDao;

        insertAsyncTask(ToDoDao dao){
            mAsyncTaskDao=dao;
        }

        @Override
        protected Void doInBackground(final ToDo... toDos) {
            mAsyncTaskDao.insert(toDos[0]);
            return null;
        }
    }
}
