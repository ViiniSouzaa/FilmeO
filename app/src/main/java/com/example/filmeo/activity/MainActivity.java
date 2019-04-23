package com.example.filmeo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.example.filmeo.R;

public class MainActivity extends AppCompatActivity {

    FrameLayout frameLayoutFilmes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayoutFilmes = findViewById(R.id.FrameLayoutFilmes);
    }

    public void listaFilmesAssistidos(View view){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.FrameLayoutFilmes, new ListaFilmesAssistidosActivity()).commit();
    }

    public void listaFilmesNaoAssistidos(View view){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.FrameLayoutFilmes, new ListaFilmesNaoAssistidosActivity()).commit();
    }

    public void adicionarFilme(View view) {
        startActivity(new Intent(getBaseContext(), AdicionarFilmeActivity.class));
    }
}
