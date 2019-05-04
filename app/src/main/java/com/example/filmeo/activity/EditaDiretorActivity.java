package com.example.filmeo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.filmeo.R;
import com.example.filmeo.model.Diretor;

import java.util.ArrayList;

public class EditaDiretorActivity extends AppCompatActivity {
    Intent intent;
    ArrayList<Diretor> diretores;
    EditText nomeDiretor;
    int diretor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edita_diretor);

        intent = getIntent();

        diretores = recuperaDiretores();
        diretor = intent.getIntExtra("diretor", -1);
        nomeDiretor = findViewById(R.id.editTextNomeDiretor2);
        nomeDiretor.setText(diretores.get(diretor).getNome());
    }

    public void EditaDiretor(View view){
        diretores.get(diretor).setNome(nomeDiretor.getText().toString());

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
