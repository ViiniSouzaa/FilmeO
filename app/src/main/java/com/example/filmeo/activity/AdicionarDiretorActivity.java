package com.example.filmeo.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.filmeo.R;
import com.example.filmeo.database.DBHelper;
import com.example.filmeo.database.DiretorDAO;
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

        diretores = recuperaDiretores();
        nomeDiretor = findViewById(R.id.editTextNomeDiretor);
    }

    public void adicionaNovoDiretor(View view){
        Diretor diretor = new Diretor();
        diretor.setNome(nomeDiretor.getText().toString());
        diretores.add(diretor);

        Intent intent = new Intent(getBaseContext(), ListaDiretoresActivity.class);
        intent.putParcelableArrayListExtra("diretores", diretores);
        startActivity(intent);
    }

    private ArrayList<Diretor> recuperaDiretores() {
        ArrayList<Diretor> tempDiretores = intent.getParcelableArrayListExtra("diretores");
        if (tempDiretores != null){
            return tempDiretores;
        }
        return new ArrayList<>();
    }
}