package com.example.filmeo.database;

import android.arch.persistence.room.Query;

import com.example.filmeo.model.Filme;

import java.util.List;

public interface FilmeDAO extends DaoGenerico<Filme> {
    @Query("SELECT * FROM filme WHERE id = :id")
    Filme listaPorId(long id);

    @Query("SELECT * FROM filme ORDER BY nome ASC")
    List<Filme> listaTodos();

}
