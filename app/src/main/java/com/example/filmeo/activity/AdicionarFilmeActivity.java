package com.example.filmeo.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.filmeo.R;
import com.example.filmeo.database.AtorDAO;
import com.example.filmeo.database.DBHelper;
import com.example.filmeo.database.DiretorDAO;
import com.example.filmeo.database.FilmeDAO;
import com.example.filmeo.model.Ator;
import com.example.filmeo.model.Diretor;
import com.example.filmeo.model.Filme;

import java.util.ArrayList;
import java.util.List;

public class AdicionarFilmeActivity extends AppCompatActivity {

    EditText nomeFilme, descricaoFilme;
    Button inserirAtores, inserirDiretor, adicionarFilme;
    LinearLayout layoutAtores;
    CheckBox assistido;
    TextView textViewDiretor, ator1, ator2, ator3;
    FilmeDAO filmeDAO;
    SQLiteDatabase connection;
    DBHelper db;
    int[] atores_id;
    int diretor;
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_adicionar_filme);
        nomeFilme = findViewById(R.id.edittext_nome_filme);
        descricaoFilme = findViewById(R.id.editTextDescricao);
        inserirAtores = findViewById(R.id.buttonInsiraAtores);
        inserirDiretor = findViewById(R.id.buttonDiretor);
        adicionarFilme = findViewById(R.id.buttonAdicionaNovoFilme);
        layoutAtores = findViewById(R.id.LayoutAtores);
        textViewDiretor = findViewById(R.id.textViewDiretor);
        assistido = findViewById(R.id.checkBoxAssistido);
        ator1 = findViewById(R.id.textViewAtor1);
        ator2 = findViewById(R.id.textViewAtor2);
        ator3 = findViewById(R.id.textViewAtor3);
        atores_id = new int[3];
        intent = getIntent();
        populaAtores();
        populaDiretor();
    }


    public void listaAtores(View view){
        Intent intent = new Intent(getBaseContext(), ListaAtoresActivity.class);
            intent.putExtra("nome", String.valueOf(nomeFilme.getText()));
            intent.putExtra("descricao", String.valueOf(descricaoFilme.getText()));
            intent.putExtra("atores", atores_id);
            intent.putExtra("diretor", diretor);
            intent.putExtra("assistido", assistido.isSelected());
            startActivity(intent);
    }

    public void listaDiretores(View view){
        Intent intent = new Intent(getBaseContext(), ListaDiretoresActivity.class);
            intent.putExtra("nome", String.valueOf(nomeFilme.getText()));
            intent.putExtra("descricao", String.valueOf(descricaoFilme.getText()));
            intent.putExtra("atores", atores_id);
            intent.putExtra("diretor", diretor);
            intent.putExtra("assistido", assistido.isSelected());
            startActivity(intent);
    }

    public void adicionarNovoFilme(View view){
        db = new DBHelper(this);
        connection = db.getWritableDatabase();
        filmeDAO = new FilmeDAO(connection);
            Filme filme = new Filme();
            filme.setNome(String.valueOf(nomeFilme.getText()));
            filme.setDescricao(String.valueOf(descricaoFilme.getText()));
            filme.setAtores(getAtoresFilme());
            filme.setDiretor(getDiretorFilme());
            filme.setAssistido(assistido.isSelected());
            filmeDAO.insert(filme);
    }

    private List<Ator> getAtoresFilme() {
        List<Ator> lista = new ArrayList<>();
        atores_id = intent.getIntArrayExtra("atores");

        db = new DBHelper(this);
        connection = db.getReadableDatabase();
        AtorDAO atorDAO = new AtorDAO(connection);
            for(int i=0;i<3;i++) {
                if(atores_id[i] != -1)
                    lista.add(atorDAO.getById(atores_id[i]));
            }
            if (lista.size() > 0)
                return lista;
            return null;
    }

    private Diretor getDiretorFilme() {
        diretor = intent.getIntExtra("diretor", 0);
        db = new DBHelper(this);
        connection = db.getReadableDatabase();
        DiretorDAO diretorDAO = new DiretorDAO(connection);
        if(diretor != -1)
            return diretorDAO.getById(diretor);
        return null;
    }

    public void populaAtores(){
        atores_id = intent.getIntArrayExtra("atores");
        db = new DBHelper(this);
        connection = db.getReadableDatabase();
        AtorDAO atorDAO = new AtorDAO(connection);
            ator1.setText(atorDAO.getById(atores_id[0]).getNome());
            ator2.setText(atorDAO.getById(atores_id[1]).getNome());
            ator3.setText(atorDAO.getById(atores_id[2]).getNome());
    }

    public void populaDiretor(){
        diretor = intent.getIntExtra("diretor", 0);
        db = new DBHelper(this);
        connection = db.getReadableDatabase();
        DiretorDAO diretorDAO = new DiretorDAO(connection);
            textViewDiretor.setText(diretorDAO.getById(diretor).getNome());
    }
}
