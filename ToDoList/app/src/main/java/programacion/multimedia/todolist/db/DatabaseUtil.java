package programacion.multimedia.todolist.db;

import static programacion.multimedia.todolist.util.Constants.DATABASE_NAME;

import android.content.Context;

import androidx.room.Room;

public class DatabaseUtil {
    public static AppDatabase getDb(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
    }
}
