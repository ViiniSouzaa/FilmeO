package com.example.filmeo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.filmeo.R;
import com.example.filmeo.database.FilmesDatabase;
import com.example.filmeo.model.Filme;

public class EditaFilmeActivity extends AppCompatActivity {

    EditText textViewNomeFilme, textViewDescricaoFilme;
    CheckBox assistido;
    TextView textViewDiretor;
    int idFilme;
    Intent intent;
    Filme filme;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edita_filme);

        textViewNomeFilme = findViewById(R.id.edittext_nome_filme2);
        textViewDescricaoFilme = findViewById(R.id.editTextDescricao2);
        textViewDiretor = findViewById(R.id.textViewDiretor2);
        assistido = findViewById(R.id.checkBoxAssistido2);
        intent = getIntent();
        recuperaDados();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_, menu);
        return true;
    }

    private void recuperaDados(){
        idFilme = intent.getIntExtra("idFilme", -1);
        filme = FilmesDatabase.getDatabase(getApplicationContext()).filmeDAO().listaPorId(idFilme);
        textViewNomeFilme.setText(filme.getNome());
        textViewDiretor.setText(FilmesDatabase.getDatabase(getApplicationContext()).diretorDAO().listaPorId(filme.getId_diretor()).getNome());
        textViewDescricaoFilme.setText(filme.getDescricao());
        assistido.setChecked(filme.isAssistido());
    }

    public boolean validaCampos(){
        return textViewNomeFilme.getText().length() != 0;
    }

    public void editaFilme(View view){
        if(validaCampos()) {
            filme.setNome(textViewNomeFilme.getText().toString());
            filme.setDescricao(textViewDescricaoFilme.getText().toString());
            filme.setAssistido(assistido.isChecked());
            FilmesDatabase.getDatabase(getApplicationContext()).filmeDAO().alterar(filme);

            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            Snackbar.make(findViewById(R.id.editarFilme), R.string.erro_insere_ator, Snackbar.LENGTH_LONG).show();
        }
    }
}
