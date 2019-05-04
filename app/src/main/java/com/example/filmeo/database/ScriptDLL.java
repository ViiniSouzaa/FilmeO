package com.example.filmeo.database;

import android.content.Context;

public class ScriptDLL {

    DBHelper DBHelper;
    Context context;


    public static String getCreateTableDiretor(){
        StringBuilder sql = new StringBuilder();
            sql.append("CREATE TABLE IF NOT EXISTS diretor(" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nome VARCHAR(100) NOT NULL );"
            );
        return sql.toString();
    }

    public static String getCreateTableFilme(){
        StringBuilder sql = new StringBuilder();
            sql.append("CREATE TABLE IF NOT EXISTS  filme (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nome VARCHAR(100) NOT NULL," +
                    "descricao VARCHAR(250) NOT NULL," +
                    "id_diretor INTEGER," +
                    "assistido BOOLEAN NOT NULL," +
                    "FOREIGN KEY(id_diretor) REFERENCES diretor(_id)" +
                    ");");
        return sql.toString();
    }


}
