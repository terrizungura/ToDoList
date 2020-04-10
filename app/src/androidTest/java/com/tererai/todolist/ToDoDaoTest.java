package com.tererai.todolist;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

//@RunWith(AndroidJUnit4.class)
public class ToDoDaoTest {

    private ToDoRoomDatabase db;
    ToDo toDo;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception {
        this.db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                ToDoRoomDatabase.class)
                .allowMainThreadQueries()
                .build();

        // DATA SET FOR TEST
        toDo = new ToDo("1tt3e", "Wash", "Wash Clothes", "22/04/2020", false);

        // BEFORE : Adding a new todo
        this.db.toDoDao().insert(toDo);
    }

    @After
    public void closeDb() throws Exception {
        toDo = null;
        db.close();
    }


    @Test
    public void test_get_todo() throws InterruptedException {
        // TEST
        List<ToDo> toDo = LiveDataTestUtil.getValue(this.db.toDoDao().getAllToDos());

        ToDo t = toDo.get(toDo.size() - 1);
        assertEquals("Wash", t.getTodo());
        assertEquals("Wash Clothes", t.getTodoDetail());
        assertFalse(t.getDoneStatus());
    }

    @Test
    public void test_update_status() throws InterruptedException {
        List<ToDo> toDoList = LiveDataTestUtil.getValue(this.db.toDoDao().getAllToDos());
        ToDo t = toDoList.get(toDoList.size() - 1);
        t.setDoneStatus(true);

        this.db.toDoDao().updateStatus(t);
        toDoList = LiveDataTestUtil.getValue(this.db.toDoDao().getAllToDos());
        t = toDoList.get(toDoList.size() - 1);
        assertTrue(t.getDoneStatus());
    }
}
