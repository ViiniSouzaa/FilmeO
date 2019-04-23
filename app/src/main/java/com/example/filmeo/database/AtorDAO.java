package com.example.filmeo.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.filmeo.model.Ator;
import com.example.filmeo.model.Filme;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class AtorDAO {

    List<Ator> atores;

    private SQLiteDatabase connection;

    public AtorDAO(SQLiteDatabase sqLiteDatabase) {
        this.connection = sqLiteDatabase;
    }

    public void insert(Ator ator){
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", ator.getNome());

        connection.insertOrThrow("ator",null,contentValues);
    }

    public void update(Ator ator){
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", ator.getNome());

        String[] args = new String[1];
        args[0] = String.valueOf(ator.getId());

        connection.update("ator",contentValues,"id = ?", args);
    }

    public void delete(int id){
        String[] args = new String[1];
        args[0] = String.valueOf(id);

        connection.delete("ator", "_id = ?", args);
    }

    public List<Ator> getAll(){
        atores =  new ArrayList<>();
        String sql = " SELECT * FROM ator ";

        Cursor cursor = connection.rawQuery(sql,null);

        if(cursor.moveToFirst()){
            do {
                Ator ator = new Ator();
                ator.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                ator.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                atores.add(ator);
            }while (cursor.moveToNext());
            cursor.close();
            return atores;
        }
        cursor.close();
        return null;
    }

    public Ator getById(int id){
        String sql = "SELECT * FROM ator WHERE _id = " + id;

        Cursor cursor = connection.rawQuery(sql,null);

        if(cursor.moveToFirst()){
                Ator ator = new Ator();
                ator.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                ator.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            cursor.close();
            return ator;
        }
        cursor.close();
        return null;
    }

    public Ator getLastAtor(){
        String sql = "SELECT * FROM ator WHERE _id = (SELECT max(_id) FROM filme)";

        Cursor cursor = connection.rawQuery(sql,null);

        Ator ator;

        if(cursor.moveToFirst()){
            ator = new Ator();
            ator.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            ator.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            cursor.close();
            return ator;
        }
        cursor.close();
        return null;
    }
}
