package programacion.multimedia.todolist.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import programacion.multimedia.todolist.domain.Task;

@Database(entities = {Task.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}

// Borrar la base de datos del móvil
// Device Explorer -> carpeta data -> data -> programacion.multimedia.todolist -> databases -> eliminar tasks