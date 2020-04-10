package com.tererai.todolist.data.dataBase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.tererai.todolist.data.model.ToDo;
import com.tererai.todolist.data.dao.ToDoDao;

@Database(entities = {ToDo.class}, version = 1, exportSchema = false)
public abstract class ToDoRoomDatabase extends RoomDatabase {

    public abstract ToDoDao toDoDao();

    private static ToDoRoomDatabase INSTANCE;

    public static ToDoRoomDatabase getDatabase(final Context context){
        if(INSTANCE ==null){
            synchronized (ToDoRoomDatabase.class){
                if(INSTANCE==null){

                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                            ToDoRoomDatabase.class, "todo_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                }
            };
}
