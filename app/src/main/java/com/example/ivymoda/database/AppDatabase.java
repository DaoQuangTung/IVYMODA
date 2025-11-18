package com.example.ivymoda.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Category.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    // Thay thế appDao() bằng 2 dòng này
    public abstract UserDao userDao();
    public abstract CategoryDao categoryDao();

    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "ivymoda_db")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}