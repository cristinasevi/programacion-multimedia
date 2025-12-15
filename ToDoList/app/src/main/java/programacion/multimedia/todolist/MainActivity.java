package programacion.multimedia.todolist;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskList = new ArrayList<>();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void addTask(View view) {
        EditText taskName = findViewById(R.id.taskName);
        String name = taskName.getText().toString();
        if (name.isEmpty()) {
            Toast.makeText(this, "Define la tarea", Toast.LENGTH_SHORT).show();
            return;
        }

        taskList.add(name);
        refreshTaskList();
    }

    public void refreshTaskList() {

    }
}