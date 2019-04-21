package com.example.filmeo.database;

import android.content.Context;

public class ScriptDLL {

    DBHelper DBHelper;
    Context context;



    public static String getCreateTableAtor(){
        StringBuilder sql = new StringBuilder();
            sql.append("CREATE TABLE ator(" +
                    "_id SERIAL PRIMARY KEY," +
                    "nome VARCHAR(100) NOT NULL )"
            );
        return sql.toString();

    }

    public static String getCreateTableDiretor(){
        StringBuilder sql = new StringBuilder();
            sql.append("CREATE TABLE diretor(" +
                    "_id SERIAL PRIMARY KEY," +
                    "nome VARCHAR(100) NOT NULL)"
            );
        return sql.toString();
    }

    public static String getCreateTableFilme(){
        StringBuilder sql = new StringBuilder();
            sql.append("CREATE TABLE filme (" +
                    "_id SERIAL PRIMARY KEY," +
                    "nome VARCHAR(100) NOT NULL," +
                    "descricao VARCHAR(250) NOT NULL," +
                    "id_diretor INTEGER," +
                    "assistido BOOLEAN NOT NULL" +
                    "FOREIGN KEY(id_diretor) REFERENCES diretor(_id)" +
                    ")");
        return sql.toString();
    }


    public static String getCreateConectionTablesFilmeAtor(){
        StringBuilder sql = new StringBuilder();
            sql.append("CREATE TABLE filme_atores" +
                    "id_filme INTEGER," +
                    "id_ator INTEGER," +
                    "PRIMARY KEY(id_filme, id_ator)," +
                    "FOREIGN KEY (id_filme) REFERENCES filme(_id)," +
                    "FOREIGN KEY (id_ator) REFERENCES ator(_id)" +
                    ")");
        return sql.toString();
    }

}
