package com.example.filmeo.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.filmeo.model.Diretor;
import com.example.filmeo.model.Filme;

import javax.xml.validation.Schema;


@Database(entities = {Filme.class, Diretor.class}, version = 1, exportSchema = false)
public abstract class FilmesDatabase extends RoomDatabase {
    public abstract FilmeDAO filmeDAO();
    public abstract DiretorDAO diretorDAO();

    private static FilmesDatabase instance;

    public static FilmesDatabase getDatabase(final Context context) {

        if (instance == null) {

            synchronized (FilmesDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context,
                            FilmesDatabase.class,
                            "filmes.db").allowMainThreadQueries().build();
                }
            }
        }
        return instance;
    }
}
