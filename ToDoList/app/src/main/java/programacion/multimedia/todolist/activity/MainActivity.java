package programacion.multimedia.todolist.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import programacion.multimedia.todolist.R;
import programacion.multimedia.todolist.adapter.TaskAdapter;
import programacion.multimedia.todolist.db.AppDatabase;
import programacion.multimedia.todolist.db.DatabaseUtil;
import programacion.multimedia.todolist.domain.Task;

public class MainActivity extends AppCompatActivity {

    List<Task> taskList;
    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskList = new ArrayList<>();

        RecyclerView todoList = findViewById(R.id.todoList);
        todoList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        todoList.setLayoutManager(linearLayoutManager);
        taskAdapter = new TaskAdapter(this, taskList);
        todoList.setAdapter(taskAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        refreshTodoList();
    }

    private void refreshTodoList() {
        final AppDatabase db = DatabaseUtil.getDb(this);
        taskList.clear();
        taskList.addAll(db.taskDao().findAll());

        taskAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.register_task_item) {
            Intent intent = new Intent(this, RegisterTaskActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}