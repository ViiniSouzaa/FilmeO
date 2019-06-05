package com.example.filmeo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.filmeo.R;
import com.example.filmeo.database.FilmesDatabase;
import com.example.filmeo.model.Diretor;

public class EditaDiretorActivity extends AppCompatActivity {
    Intent intent;
    EditText nomeDiretor;
    long diretor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edita_diretor);

        intent = getIntent();

        diretor = intent.getIntExtra("diretor", -1);
        nomeDiretor = findViewById(R.id.editTextNomeDiretor2);
        nomeDiretor.setText(FilmesDatabase.getDatabase(getApplicationContext()).diretorDAO().listaPorId(diretor).toString());
    }

    public void EditaDiretor(View view){
        long in = diretor;
        Diretor diretor = FilmesDatabase.getDatabase(getApplicationContext()).diretorDAO().listaPorId(in);
        diretor.setNome(nomeDiretor.getText().toString());
        FilmesDatabase.getDatabase(getApplicationContext()).diretorDAO().alterar(diretor);
        Intent intent = new Intent(getBaseContext(), ListaDiretoresActivity.class);
        startActivity(intent);
        finish();
    }
}
