package programacion.multimedia.todolist;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

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

        final AppDatabase db = DatabaseUtil.getDb(this);
        taskList = db.taskDao().findAll();

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
    }

    public void addTask(View view) {
        EditText etTaskName = findViewById(R.id.taskName);
        String taskName = etTaskName.getText().toString();
        if (taskName.isEmpty()) {
            Toast.makeText(this, "Define la tarea", Toast.LENGTH_SHORT).show();
            return;
        }

        Task task = new Task(taskName);
        final AppDatabase db = DatabaseUtil.getDb(this);
        db.taskDao().insert(task);

        etTaskName.setText("");
        etTaskName.requestFocus();

        refreshTodoList();
    }

    private void refreshTodoList() {
        final AppDatabase db = DatabaseUtil.getDb(this);
        taskList.clear();
        taskList.addAll(db.taskDao().findAll());

        taskAdapter.notifyDataSetChanged();
    }
}