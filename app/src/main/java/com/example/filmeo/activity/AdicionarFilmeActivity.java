package com.example.filmeo.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.filmeo.R;
import com.example.filmeo.database.DiretorDatabase;
import com.example.filmeo.database.FilmeDatabase;
import com.example.filmeo.model.Diretor;
import com.example.filmeo.model.Filme;

import java.util.ArrayList;

public class AdicionarFilmeActivity extends AppCompatActivity {

    EditText nomeFilme, descricaoFilme;
    Button inserirDiretor, adicionarFilme;
    CheckBox assistido;
    TextView textViewDiretor;
    int diretor;
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_adicionar_filme);

        nomeFilme = findViewById(R.id.edittext_nome_filme);
        descricaoFilme = findViewById(R.id.editTextDescricao);
        inserirDiretor = findViewById(R.id.buttonDiretor);
        adicionarFilme = findViewById(R.id.buttonAdicionaNovoFilme);
        textViewDiretor = findViewById(R.id.textViewDiretor);
        assistido = findViewById(R.id.checkBoxAssistido);
        intent = getIntent();
        recuperaDados();
        populaDiretor();
        //popularLista();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_, menu);
        return true;
    }

    private void recuperaDados(){
        diretor = intent.getIntExtra("diretor", -1);
    }

    /*
        public void adicionaGenero(View view){
            Intent intent = new Intent(getBaseContext(), GerenciaGenerosActivity.class);
                intent.putExtra("nome", String.valueOf(nomeFilme.getText()));
                intent.putExtra("descricao", String.valueOf(descricaoFilme.getText()));
                intent.putExtra("diretor", diretor.getId());
                intent.putExtra("assistido", assistido.isSelected());
        startActivity(intent);
    }*/

    public void listaDiretores(View view){
        Intent intent = new Intent(getBaseContext(), ListaDiretoresActivity.class);
            startActivity(intent);
    }

    public void populaDiretor(){
        if(diretor != -1){
            textViewDiretor.setText(
            DiretorDatabase.getDatabase(getApplicationContext()).diretorDAO().listaPorId(diretor).toString());
        }
    }

    public boolean validaCampos(){
        return !String.valueOf(nomeFilme.getText()).equals("");
    }

    public void adicionarNovoFilme(View view){
        if(validaCampos()) {
            Filme filme = new Filme();
            filme.setNome(nomeFilme.getText().toString());
            filme.setDescricao(descricaoFilme.getText().toString());
            filme.setAssistido(assistido.isSelected());
            filme.setId_diretor(diretor);

            FilmeDatabase.getDatabase(getApplicationContext()).filmeDAO().inserir(filme);

            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
        }else{
            Snackbar.make(new View(this), R.string.erro_insere_ator, Snackbar.LENGTH_LONG);
        }
    }
}
