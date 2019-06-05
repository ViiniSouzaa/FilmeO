package com.example.filmeo.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.filmeo.R;
import com.example.filmeo.database.FilmesDatabase;
import com.example.filmeo.model.Diretor;

import java.util.ArrayList;

public class AdicionarDiretorActivity extends AppCompatActivity {

    private static final String ARQUIVO = "com.example.filmeo.PREFERECIA_DARK";
    private static final String OPCAO = "TEMA";
    private boolean dark;

    Intent intent;
    EditText nomeDiretor;
    String nomeFilme, descricaoFilme;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adiciona_novo_diretor);

        intent = getIntent();
        nomeFilme = intent.getStringExtra("nomeFilme");
        descricaoFilme = intent.getStringExtra("descricaoFilme");

        nomeDiretor = findViewById(R.id.editTextNomeDiretor);
        lerPreferenciaTema();
    }

    public void adicionaNovoDiretor(View view){
        Diretor diretor = new Diretor(nomeDiretor.getText().toString());
        FilmesDatabase.getDatabase(getApplicationContext()).diretorDAO().inserir(diretor);
        Intent intent = new Intent(getBaseContext(), AdicionarFilmeActivity.class);
        intent.putExtra("diretor", FilmesDatabase.getDatabase(getApplicationContext()).diretorDAO().getUltimoDiretor().getId());
        intent.putExtra("nomeFilme", nomeFilme);
        intent.putExtra("descricaoFilme", descricaoFilme);
        startActivity(intent);
        finish();
    }

    public void lerPreferenciaTema(){
        SharedPreferences s = getSharedPreferences(ARQUIVO, Context.MODE_PRIVATE);

        dark = s.getBoolean(OPCAO, false);

        mudaTema();
    }

    private void mudaTema() {
        if(dark){
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else if(!dark){
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }


}