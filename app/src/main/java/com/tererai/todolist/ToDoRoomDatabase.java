package com.tererai.todolist;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

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
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    //Populate the database in the background.

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>{

        private final ToDoDao mDao;
        String[] todos = {"The first thing"};

        PopulateDbAsync(ToDoRoomDatabase db){
            mDao = db.toDoDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
            mDao.deleteAll();

            for(int i=0; i<=todos.length-1; i++){
                ToDo toDo = new ToDo(todos[i]);
                mDao.insert((toDo));
            }
            return null;
        }
    }
}
