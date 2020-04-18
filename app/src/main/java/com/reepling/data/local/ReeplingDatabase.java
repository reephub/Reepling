package com.reepling.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.reepling.data.local.dao.UserDao;
import com.reepling.data.local.model.User;


@Database(entities = {User.class}, version = 1)
public abstract class ReeplingDatabase extends RoomDatabase {

    private static ReeplingDatabase instance;

    public abstract UserDao userDao();

    // Only one instance can access the database at the time
    public static synchronized ReeplingDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ReeplingDatabase.class, "reepling_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }
}
