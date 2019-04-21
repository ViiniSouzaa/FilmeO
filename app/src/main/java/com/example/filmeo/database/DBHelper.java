package com.example.filmeo.database;

import android.bluetooth.le.ScanRecord;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String nome_banco = "filmeo.db";
    public static final int versao_banco = 1;


    public DBHelper(Context context) {
        super(context, "nome_banco", null, versao_banco);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ScriptDLL.getCreateTableAtor());
        db.execSQL(ScriptDLL.getCreateTableDiretor());
        db.execSQL(ScriptDLL.getCreateTableFilme());
        db.execSQL(ScriptDLL.getCreateConectionTablesFilmeAtor());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
