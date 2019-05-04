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
import com.example.filmeo.database.DBHelper;
import com.example.filmeo.database.DiretorDAO;
import com.example.filmeo.database.FilmeDAO;
import com.example.filmeo.model.Diretor;
import com.example.filmeo.model.Filme;

import java.util.ArrayList;

public class AdicionarFilmeActivity extends AppCompatActivity {

    EditText nomeFilme, descricaoFilme;
    Button inserirDiretor, adicionarFilme;
    CheckBox assistido;
    TextView textViewDiretor;
    FilmeDAO filmeDAO;
    SQLiteDatabase connection;
    DBHelper db;
    Diretor diretor;
    DiretorDAO diretorDAO;
    ListView listViewGeneros;
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
        listViewGeneros = findViewById(R.id.listViewGeneros);
        intent = getIntent();
        recuperaDados();
        populaDiretor();
        popularLista();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_, menu);
        return true;
    }

    private void recuperaDados(){
        db = new DBHelper(this);
        connection = db.getReadableDatabase();
        diretorDAO = new DiretorDAO(connection);
        nomeFilme.setText(intent.getStringExtra("nome"));
        descricaoFilme.setText(intent.getStringExtra("descricao"));
        diretor = diretorDAO.getById(intent.getIntExtra("diretor", -1));
        assistido.setSelected(intent.getBooleanExtra("assistido", false));
    }

    public void adicionaGenero(View view){
        Intent intent = new Intent(getBaseContext(), GerenciaGenerosActivity.class);
            /*intent.putExtra("nome", String.valueOf(nomeFilme.getText()));
            intent.putExtra("descricao", String.valueOf(descricaoFilme.getText()));
            intent.putExtra("diretor", diretor.getId());
            intent.putExtra("assistido", assistido.isSelected());*/
        startActivity(intent);
    }

    public void listaDiretores(View view){
        Intent intent = new Intent(getBaseContext(), ListaDiretoresActivity.class);
            /*intent.putExtra("nome", String.valueOf(nomeFilme.getText()));
            intent.putExtra("descricao", String.valueOf(descricaoFilme.getText()));
            intent.putExtra("diretor", diretor.getId());
            intent.putExtra("assistido", assistido.isSelected());*/
            startActivity(intent);
    }

    public void populaDiretor(){
            if(diretor != null)
                textViewDiretor.setText(diretor.getNome());
    }

    public boolean validaCampos(){
        return !String.valueOf(nomeFilme.getText()).equals("");
    }

    public void adicionarNovoFilme(View view){
        if(validaCampos()) {
            db = new DBHelper(this);
            connection = db.getWritableDatabase();
            filmeDAO = new FilmeDAO(connection);
            Filme filme = new Filme();
            filme.setNome(String.valueOf(nomeFilme.getText()));
            filme.setDescricao(String.valueOf(descricaoFilme.getText()));
            filme.setDiretor(diretor);
            filme.setAssistido(assistido.isSelected());
            filmeDAO.insert(filme);
            finish();
        }else{
            Snackbar.make(new View(this), R.string.erro_insere_ator, Snackbar.LENGTH_LONG);
        }
    }

   private void popularLista(){
        ArrayList<String> nomes = new ArrayList<>();

        for (int i = 0; i < 10; i++)
            nomes.add("teste" + (i+1));

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, nomes);

        listViewGeneros.setAdapter(adapter);
    }
}
