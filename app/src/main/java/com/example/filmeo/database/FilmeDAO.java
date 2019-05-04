package com.example.filmeo.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
    }

    public void delete(int id){
        String[] args = new String[1];
        args[0] = String.valueOf(id);

        connection.delete("filme_ator", "id_filme = ?", args);
        connection.delete("filme", "_id = ?", args);
    }

    public List<Filme> getAllAssistidos(Boolean order){
        list =  new ArrayList<>();
        String sql;
        if (order){
            sql = "SELECT * FROM filme WHERE assistido ORDER BY nome ASC ";
        }else {
            sql = "SELECT * FROM filme WHERE assistido ORDER BY nome DESC";
        }

        Cursor cursor = connection.rawQuery(sql,null);

        if(cursor.moveToFirst()){
            do {
                Filme filme =  new Filme();
                filme.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                filme.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                filme.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
                filme.setDiretor(getDiretor(cursor.getInt(cursor.getColumnIndex("id_diretor"))));
                filme.setAssistido(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex("assistido"))));
                list.add(filme);
            }while (cursor.moveToNext());
            cursor.close();
            return list;
        }
        cursor.close();
        return null;
    }

    public List<Filme> getAllNaoAssistidos(Boolean order){
        list =  new ArrayList<>();
        String sql;
        if (order){
            sql = " SELECT * FROM filme WHERE NOT assistido ORDER BY nome ASC";
        }else{
            sql = " SELECT * FROM filme WHERE NOT assistido ORDER BY nome DESC";
        }

        Cursor cursor = connection.rawQuery(sql,null);

        if(cursor.moveToFirst()){
            do {
                Filme filme =  new Filme();
                filme.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                filme.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                filme.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
                filme.setDiretor(getDiretor(cursor.getInt(cursor.getColumnIndex("id_diretor"))));
                filme.setAssistido(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex("assistido"))));
                list.add(filme);
            }while (cursor.moveToNext());
            cursor.close();
            return list;
        }
        cursor.close();
        return null;
    }

    public List<Filme> getByNomeAssistido(String nome, Boolean order){
        list = new ArrayList<>();

        String sql;
        if (order){
            sql = "SELECT * FROM filme WHERE  assistido AND nome LIKE '%" + nome + "%' ORDER BY nome ASC";
        }else{
            sql = "SELECT * FROM filme WHERE  assistido AND nome LIKE '%" + nome  + "%' ORDER BY nome DESC";
        }

        Cursor cursor = connection.rawQuery(sql,null);

        if(cursor.moveToFirst()){
            do {
                Filme filme =  new Filme();
                filme.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                filme.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                filme.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
                filme.setDiretor(getDiretor(cursor.getInt(cursor.getColumnIndex("id_diretor"))));
                filme.setAssistido(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex("assistido"))));
                list.add(filme);
            }while (cursor.moveToNext());
            cursor.close();
            return list;
        }
        cursor.close();
        return null;
    }

    public List<Filme> getByNomeNaoAssistido(String nome, Boolean order){
        list = new ArrayList<>();

        String sql;
        if (order){
            sql = "SELECT * FROM filme WHERE  NOT assistido AND nome LIKE '%" + nome + "%' ORDER BY nome ASC";
        }else{
            sql = "SELECT * FROM filme WHERE  NOT assistido AND nome LIKE '%" + nome  + "%' ORDER BY nome DESC";
        }

        Cursor cursor = connection.rawQuery(sql,null);

        if(cursor.moveToFirst()){
            do {
                Filme filme =  new Filme();
                filme.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                filme.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                filme.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
                filme.setDiretor(getDiretor(cursor.getInt(cursor.getColumnIndex("id_diretor"))));
                filme.setAssistido(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex("assistido"))));
                list.add(filme);
            }while (cursor.moveToNext());
            cursor.close();
            return list;
        }
        cursor.close();
        return null;
    }

    public Filme getLastFilme(){
        String sql = "SELECT * FROM filme WHERE _id = (SELECT max(_id) FROM filme)";

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