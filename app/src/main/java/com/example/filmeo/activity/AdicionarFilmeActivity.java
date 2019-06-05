package com.example.filmeo.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.filmeo.R;
import com.example.filmeo.database.FilmesDatabase;
import com.example.filmeo.model.Filme;

public class AdicionarFilmeActivity extends AppCompatActivity {

    private static final String ARQUIVO = "com.example.filmeo.PREFERECIA_DARK";
    private static final String OPCAO = "TEMA";
    private boolean dark;

    EditText textViewNomeFilme, descricaoFilme;
    Button inserirDiretor, adicionarFilme;
    CheckBox assistido;
    TextView textViewDiretor;
    int diretor;
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_adicionar_filme);

        textViewNomeFilme = findViewById(R.id.edittext_nome_filme);
        descricaoFilme = findViewById(R.id.editTextDescricao);
        inserirDiretor = findViewById(R.id.buttonDiretor);
        adicionarFilme = findViewById(R.id.buttonAdicionaNovoFilme);
        textViewDiretor = findViewById(R.id.textViewDiretor);
        assistido = findViewById(R.id.checkBoxAssistido);
        intent = getIntent();
        recuperaDados();
        populaDiretor();
        lerPreferenciaTema();
    }

    private void recuperaDados(){
        String nomeFilmeRecuperado = intent.getStringExtra("nomeFilme");
        String descricaoFilmeRecuperado = intent.getStringExtra("descricaoFilme");
        textViewNomeFilme.setText(nomeFilmeRecuperado);
        descricaoFilme.setText(descricaoFilmeRecuperado);
        diretor = intent.getIntExtra("diretor", -1);
    }

    public void listaDiretores(View view){
        Intent intent = new Intent(getBaseContext(), ListaDiretoresActivity.class);
        intent.putExtra("nomeFilme", textViewNomeFilme.getText().toString());
        intent.putExtra("descricaoFilme", descricaoFilme.getText().toString());
            startActivity(intent);
    }

    public void populaDiretor(){
        if(diretor != -1){
            textViewDiretor.setText(
            FilmesDatabase.getDatabase(getApplicationContext()).diretorDAO().listaPorId(diretor).toString());
        }
    }

    public boolean validaCampos(){
        if(textViewNomeFilme.getText().length() == 0){
            Snackbar snackbar = Snackbar.make(findViewById(R.id.layoutAddFilme), R.string.erro_insere_ator, Snackbar.LENGTH_LONG);
            snackbar.show();
            return false;
        }else if (diretor == -1){
            Snackbar snackbar = Snackbar.make(findViewById(R.id.layoutAddFilme), R.string.erro_insere_diretor, Snackbar.LENGTH_LONG);
            snackbar.show();
            return false;
        }else{
            return true;
        }
    }

    public void adicionarNovoFilme(View view){
        if(validaCampos()) {
            Filme filme = new Filme();
            filme.setNome(textViewNomeFilme.getText().toString());
            filme.setDescricao(descricaoFilme.getText().toString());
            filme.setAssistido(assistido.isChecked());
            filme.setId_diretor(diretor);

            if(FilmesDatabase.getDatabase(getApplicationContext()).filmeDAO().inserir(filme) > 0)
                System.out.println("DEU PRA INSERIR ");

            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
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
