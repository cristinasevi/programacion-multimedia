package programacion.multimedia.todolist.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import programacion.multimedia.todolist.R;
import programacion.multimedia.todolist.db.AppDatabase;
import programacion.multimedia.todolist.db.DatabaseUtil;
import programacion.multimedia.todolist.domain.Task;

public class RegisterTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_task);
    }

    public void addTask(View view) {
        EditText etTaskName = findViewById(R.id.task_name);
        EditText etTaskDescription = findViewById(R.id.task_description);
        CheckBox cbTaskDone = findViewById(R.id.task_done);

        String name = etTaskName.getText().toString();
        String description = etTaskDescription.getText().toString();
        boolean done = cbTaskDone.isChecked();
        // ToDo Validar entrada

        Task task = new Task(name, description, done);
        final AppDatabase db = DatabaseUtil.getDb(this);
        db.taskDao().insert(task);

        Toast.makeText(this, R.string.task_added, Toast.LENGTH_SHORT).show();
        etTaskName.setText("");
        etTaskDescription.setText("");
        cbTaskDone.setChecked(false);
        etTaskName.requestFocus();
    }
}