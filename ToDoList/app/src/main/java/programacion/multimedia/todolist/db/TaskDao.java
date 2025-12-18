package programacion.multimedia.todolist.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import programacion.multimedia.todolist.domain.Task;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM task")
    List<Task> findAll();

    @Query("SELECT * FROM task WHERE name = :name")
    List<Task> findByName(String name);

    @Insert
    void insert(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);

    @Query("DELETE FROM task WHERE name = :name")
    void deleteByName(String name);
}
