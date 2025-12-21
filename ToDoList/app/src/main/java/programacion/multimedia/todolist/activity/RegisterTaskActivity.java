package programacion.multimedia.todolist.activity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.mapbox.geojson.Point;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;
import com.mapbox.maps.plugin.gestures.GesturesPlugin;
import com.mapbox.maps.plugin.gestures.GesturesUtils;
import com.mapbox.maps.plugin.gestures.OnMapClickListener;

import programacion.multimedia.todolist.R;
import programacion.multimedia.todolist.db.AppDatabase;
import programacion.multimedia.todolist.db.DatabaseUtil;
import programacion.multimedia.todolist.domain.Task;
import programacion.multimedia.todolist.util.MapUtils;

public class RegisterTaskActivity extends AppCompatActivity implements OnMapClickListener {

    private MapView mapView;
    private GesturesPlugin gesturesPlugin;
    private PointAnnotationManager pointAnnotationManager;
    private Point currentPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_task);

        mapView = findViewById(R.id.mapView);
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS);

        initializeGesturesPlugin();
        pointAnnotationManager = MapUtils.buildAnnotationManager(mapView);
    }

    @Override
    public boolean onMapClick(@NonNull Point point) {
        pointAnnotationManager.deleteAll();
        currentPoint = point;
        MapUtils.addMarker(this, pointAnnotationManager, point);
        return false;
    }

    public void addTask(View view) {
        EditText etTaskName = findViewById(R.id.task_name);
        EditText etTaskDescription = findViewById(R.id.task_description);
        CheckBox cbTaskDone = findViewById(R.id.task_done);

        String name = etTaskName.getText().toString();
        String description = etTaskDescription.getText().toString();
        boolean done = cbTaskDone.isChecked();
        // ToDo Validar entrada

        Task task = new Task(name, description, done, currentPoint.latitude(), currentPoint.longitude());
        final AppDatabase db = DatabaseUtil.getDb(this);
        db.taskDao().insert(task);

        Toast.makeText(this, R.string.task_added, Toast.LENGTH_SHORT).show();
        etTaskName.setText("");
        etTaskDescription.setText("");
        cbTaskDone.setChecked(false);
        pointAnnotationManager.deleteAll();
        etTaskName.requestFocus();
    }

    private void initializeGesturesPlugin() {
        gesturesPlugin = GesturesUtils.getGestures(mapView);
        gesturesPlugin.addOnMapClickListener(this);
    }
}