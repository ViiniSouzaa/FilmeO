package com.example.filmeo.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.filmeo.model.Filme;

import java.util.List;

@Dao
public interface FilmeDAO extends DaoGenerico<Filme> {
    @Query("SELECT * FROM filme WHERE id = :id")
    Filme listaPorId(long id);

    @Query("SELECT * FROM filme ORDER BY nome ASC")
    List<Filme> listaTodos();

    @Query("SELECT * FROM filme WHERE assistido  ORDER BY nome ASC")
    List<Filme> listaAssistidos();

    @Query("SELECT * FROM filme WHERE assistido  ORDER BY nome DESC")
    List<Filme> listaAssistidosInvertido();

    @Query("SELECT * FROM filme WHERE NOT assistido  ORDER BY nome ASC")
    List<Filme> listaNaoAssistidos();

    @Query("SELECT * FROM filme WHERE NOT assistido  ORDER BY nome DESC")
    List<Filme> listaNaoAssistidosInvertido();

    @Query("SELECT * FROM filme WHERE assistido AND nome like :nome ORDER BY nome ASC")
    List<Filme> listaAssistidoPorNome(String nome);

    @Query("SELECT * FROM filme WHERE assistido AND nome like :nome ORDER BY nome DESC")
    List<Filme> listaAssistidoPorNomeInvertido(String nome);

    @Query("SELECT * FROM filme WHERE NOT assistido AND nome like :nome ORDER BY nome ASC")
    List<Filme> listaNaoAssistidoPorNome(String nome);

    @Query("SELECT * FROM filme WHERE NOT assistido AND nome like :nome ORDER BY nome DESC")
    List<Filme> listaNaoAssistidoPorNomeInvertido(String nome);
}
