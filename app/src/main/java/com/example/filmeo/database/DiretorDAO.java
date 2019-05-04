package com.example.filmeo.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.filmeo.model.Diretor;

import java.util.ArrayList;
import java.util.List;

public class DiretorDAO {
    List<Diretor> diretores;

    private SQLiteDatabase connection;

    public DiretorDAO(SQLiteDatabase sqLiteDatabase) {
        this.connection = sqLiteDatabase;
    }

    public void insert(Diretor diretor){
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", diretor.getNome());

        connection.insertOrThrow("diretor",null,contentValues);
    }

    public void update(Diretor diretor){
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", diretor.getNome());

        String[] args = new String[1];
        args[0] = String.valueOf(diretor.getId());

        connection.update("diretor",contentValues,"id = ?", args);
    }

    public void delete(int id){
        String[] args = new String[1];
        args[0] = String.valueOf(id);

        connection.delete("diretor", "_id = ?", args);
    }

    public List<Diretor> getAll(){
        diretores =  new ArrayList<>();
        String sql = " SELECT * FROM diretor ";

        Cursor cursor = connection.rawQuery(sql,null);

        if(cursor.moveToFirst()){
            do {
                Diretor diretor = new Diretor();
                diretor.setId(cursor.getInt(cursor.getColumnIndex("id")));
                diretor.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                diretores.add(diretor);
            }while (cursor.moveToNext());
            cursor.close();
            return diretores;
        }
        cursor.close();
        return null;
    }

    public Diretor getById(int id){
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
