package com.example.filmeo.database;

import android.arch.persistence.room.Query;

import com.example.filmeo.model.Diretor;
import com.example.filmeo.model.Filme;

import java.util.List;

public interface DiretorDAO extends DaoGenerico<Diretor> {
    @Query("SELECT * FROM diretor WHERE id = :id")
    Diretor listaPorId(long id);

    @Query("SELECT * FROM diretor ORDER BY nome ASC")
    List<Diretor> listaTodos();

    @Query("SELECT * FROM diretor WHERE id = (SELECT MAX(id) from diretor)")
    Diretor getUltimoDiretor();
}
