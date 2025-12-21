package programacion.multimedia.todolist.activity;

import android.graphics.BitmapFactory;
import android.os.Bundle;

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

import java.util.List;

import programacion.multimedia.todolist.R;
import programacion.multimedia.todolist.db.AppDatabase;
import programacion.multimedia.todolist.db.DatabaseUtil;
import programacion.multimedia.todolist.domain.Task;
import programacion.multimedia.todolist.util.MapUtils;

public class MapActivity extends AppCompatActivity implements Style.OnStyleLoaded {

    private MapView mapView;
    private PointAnnotationManager pointAnnotationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mapView = findViewById(R.id.map);
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS, this);

        pointAnnotationManager = MapUtils.buildAnnotationManager(mapView);
    }

    @Override
    public void onStyleLoaded(@NonNull Style style) {
        final AppDatabase db = DatabaseUtil.getDb(this);
        List<Task> allTasks = db.taskDao().findAll();
        showTasksAsMarkers(allTasks);
    }

    private void showTasksAsMarkers(List<Task> tasks) {
        tasks.forEach(task -> {
            Point point = Point.fromLngLat(task.getLongitude(), task.getLatitude());
            MapUtils.addMarker(this, pointAnnotationManager, point, task.getName());
        });
    }
}