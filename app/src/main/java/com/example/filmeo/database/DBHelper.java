package com.example.filmeo.database;

import android.bluetooth.le.ScanRecord;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String nome_banco = "filmeo.db";
    public static final int versao_banco = 1;


    public DBHelper(Context context) {
        super(context, nome_banco, null, versao_banco);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(ScriptDLL.getCreateTableAtor());
        }catch (SQLException ex){
            System.out.println("Erro aqui ator");
        }
        try {
            db.execSQL(ScriptDLL.getCreateTableDiretor());
        }catch (SQLException ex){
            System.out.println("Erro aqui diretor");
        }
        try {
            db.execSQL(ScriptDLL.getCreateTableFilme());
        }catch (SQLException ex){
            System.out.println("Erro aqui filme");
        }
        try {
            db.execSQL(ScriptDLL.getCreateConectionTablesFilmeAtor());
        }catch (SQLException ex){
            System.out.println("Erro aqui filme_ator");
        }
        db.execSQL(ScriptDLL.getCreateConectionTablesFilmeAtor());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
