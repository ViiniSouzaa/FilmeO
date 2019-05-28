package com.example.filmeo.database;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

public abstract class DiretorDatabase  extends RoomDatabase {

    public abstract DiretorDAO diretorDAO();

    private static DiretorDatabase instance;

    public static DiretorDatabase getDatabase(final Context context) {

        if (instance == null) {

            synchronized (DiretorDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context,
                            DiretorDatabase.class,
                            "diretor.db").allowMainThreadQueries().build();
                }
            }
        }
        return instance;
    }
}