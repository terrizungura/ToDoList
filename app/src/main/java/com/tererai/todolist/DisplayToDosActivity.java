package com.tererai.todolist;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DisplayToDosActivity extends AppCompatActivity {

    private ToDoViewModel mToDoViewModel;

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    @BindView(R.id.fab) FloatingActionButton fab;
    @BindView(R.id.image) ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_to_dos);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        mToDoViewModel = ViewModelProviders.of(this).get(ToDoViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ToDoListAdapter adapter = new ToDoListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mToDoViewModel.getAllToDos().observe(this, new Observer<List<ToDo>>() {
            @Override
            public void onChanged(List<ToDo> toDos) {
                // Update the cached copy of the ToDos in the adapter.
                adapter.setToDos(toDos);
            }
        });
    }

    @OnClick(R.id.fab)
        public void addNewToDo() {
            Intent intent = new Intent(DisplayToDosActivity.this, NewToDoActivity.class);
            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);

        }

        @OnClick(R.id.image)
        public void deteteToDo(){
        
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            ToDo toDo = new ToDo(data.getStringExtra(NewToDoActivity.EXTRA_REPLY),
                    data.getStringExtra(NewToDoActivity.EXTRA_DETAIL),
                    data.getStringExtra(NewToDoActivity.EXTRA_DATE),
                    false);
            mToDoViewModel.insert(toDo);
        } else {
            Toast.makeText(getApplicationContext(), R.string.empty_not_saved, Toast.LENGTH_LONG).show();
        }
    }


}
