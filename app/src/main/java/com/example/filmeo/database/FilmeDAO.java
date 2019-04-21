package com.example.filmeo.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.filmeo.model.Ator;
import com.example.filmeo.model.Diretor;
import com.example.filmeo.model.Filme;

import java.util.ArrayList;
import java.util.List;

public class FilmeDAO {
    List<Filme> list;
    private SQLiteDatabase connection;

    public FilmeDAO(SQLiteDatabase connection) {
        this.connection = connection;
    }

    public void insert (Filme filme){
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", filme.getNome());
        contentValues.put("descricao", filme.getDescricao());
        contentValues.put("id_diretor", filme.getDiretor().getId());
        contentValues.put("assistido", filme.isAssistido());

        connection.insertOrThrow("filme", null, contentValues);
        for (Ator ator : filme.getAtores()){
            ContentValues contentValuesAtor = new ContentValues();
            contentValuesAtor.put("id_filme", getLastFilme().getId());
            contentValuesAtor.put("id_ator", ator.getId());

            connection.insertOrThrow("filme_atores", null, contentValuesAtor);
        }
    }

    public void update (Filme filme){
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", filme.getNome());
        contentValues.put("descricao", filme.getDescricao());
        contentValues.put("id_diretor", filme.getDiretor().getId());
        contentValues.put("assistido", filme.isAssistido());

        String[] args =  new String[1];
        args[0] = String.valueOf(filme.getId());

        connection.update("filme",contentValues,"_id = ?", args);

        for (Ator ator : filme.getAtores()){
            ContentValues contentValuesAtor = new ContentValues();
            contentValuesAtor.put("id_ator", ator.getId());

            String[] argsAtor =  new String[1];
            argsAtor[0] = String.valueOf(filme.getId());

            connection.update("filme_atores",contentValues,"id_filme = ?", argsAtor);
        }
    }

    public void delete(int id){
        String[] args = new String[1];
        args[0] = String.valueOf(id);

        connection.delete("filme_ator", "id_filme = ?", args);
        connection.delete("filme", "_id = ?", args);
    }

    public List<Filme> getAll(){
        list =  new ArrayList<>();
        String sql = " SELECT * FROM filme ";

        Cursor cursor = connection.rawQuery(sql,null);

        if(cursor.moveToFirst()){
            do {
                Filme filme =  new Filme();
                filme.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                filme.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                filme.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
                filme.setAtores(atoresFilme(filme.getId()));
                filme.setDiretor(getDiretor(cursor.getInt(cursor.getColumnIndex("id_diretor"))));
                filme.setAssistido(getAssistido(cursor.getInt(cursor.getColumnIndex("assistido"))));
            }while (cursor.moveToNext());
            cursor.close();
            return list;
        }
        cursor.close();
        return null;
    }

    private boolean getAssistido(int value) {
        if(value == 1)
            return true;
        return false;
    }

    public Filme getById(int id){
        String sql = " SELECT * FROM filme ";

        Cursor cursor = connection.rawQuery(sql,null);

        if(cursor.moveToFirst()){
                Filme filme =  new Filme();
                filme.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                filme.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                filme.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
                filme.setAtores(atoresFilme(filme.getId()));
                filme.setDiretor(getDiretor(cursor.getInt(cursor.getColumnIndex("id_diretor"))));

                cursor.close();
            return filme;
        }
        cursor.close();
        return null;
    }


    public Filme getLastFilme(){
        String sql = "SELECT * FROM filme WHERE _id = max(id)";

        Cursor cursor = connection.rawQuery(sql,null);

        Filme filme;

        if(cursor.moveToFirst()){
            filme = new Filme();
            filme.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            filme.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            filme.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
            cursor.close();
            return filme;
        }
        cursor.close();
        return null;
    }

    public List<Ator> atoresFilme(int idFilme){
        List<Ator> lista =  new ArrayList<>();
        String sql = " SELECT * FROM filme_ator join ator on(_id = id_ator)  WHERE id_filme = " + idFilme;

        Cursor cursor = connection.rawQuery(sql,null);

        if(cursor.moveToFirst()){
            do {
                Ator ator =  new Ator();
                ator.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                ator.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                lista.add(ator);
            }while (cursor.moveToNext());
            cursor.close();
            return lista;
        }
        cursor.close();
        return null;
    }

    public Diretor getDiretor(int id){
        String sql = "SELECT * FROM diretor WHERE _id = " + id;

        Cursor cursor = connection.rawQuery(sql,null);

        if(cursor.moveToFirst()){
            Diretor diretor = new Diretor();
            diretor.setId(cursor.getInt(cursor.getColumnIndex("id")));
            diretor.setNome(cursor.getString(cursor.getColumnIndex("nome")));

            cursor.close();
            return diretor;
        }
        cursor.close();
        return null;
    }
}
