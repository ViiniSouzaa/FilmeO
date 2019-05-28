package com.example.filmeo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.filmeo.R;
import com.example.filmeo.model.Diretor;

import java.util.ArrayList;

public class AdicionarDiretorActivity extends AppCompatActivity {

    Intent intent;
    ArrayList<Diretor> diretores;
    EditText nomeDiretor;
    Button novoDiretor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adiciona_novo_diretor);

        intent = getIntent();

        nomeDiretor = findViewById(R.id.editTextNomeDiretor);
    }

    public void adicionaNovoDiretor(View view){
        Diretor diretor = new Diretor(nomeDiretor.getText().toString());
        Intent intent = new Intent(getBaseContext(), ListaDiretoresActivity.class);
        intent.putExtra("diretor", diretor.getId());
        startActivity(intent);
    }


}