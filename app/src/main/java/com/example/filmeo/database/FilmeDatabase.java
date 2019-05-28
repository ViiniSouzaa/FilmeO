package com.example.filmeo.database;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

public abstract class FilmeDatabase extends RoomDatabase {
    public abstract FilmeDAO filmeDAO();

    private static FilmeDatabase instance;

    public static FilmeDatabase getDatabase(final Context context) {

        if (instance == null) {

            synchronized (FilmeDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context,
                            FilmeDatabase.class,
                            "filme.db").allowMainThreadQueries().build();
                }
            }
        }
        return instance;
    }
}
