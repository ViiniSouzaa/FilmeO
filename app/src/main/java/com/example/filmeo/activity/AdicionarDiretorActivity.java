package com.example.filmeo.activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.filmeo.R;
import com.example.filmeo.database.AtorDAO;
import com.example.filmeo.database.DBHelper;
import com.example.filmeo.database.DiretorDAO;
import com.example.filmeo.model.Diretor;

public class AdicionarDiretorActivity extends Fragment {

    DBHelper db;
    SQLiteDatabase connection;
    DiretorDAO diretorDAO;
    EditText nomeDiretor;
    Button novoDiretor;

    public AdicionarDiretorActivity(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_adiciona_novo_diretor, container, false);

        db = new DBHelper(view.getContext());
        connection = db.getWritableDatabase();

        diretorDAO =  new DiretorDAO(connection);

        nomeDiretor = view.findViewById(R.id.editTextNomeDiretor);
        novoDiretor = view.findViewById(R.id.buttonNovoDiretor);
        novoDiretor.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Diretor diretor = new Diretor();
                diretor.setNome(String.valueOf(nomeDiretor.getText()));
                getActivity().finish();
            }
        });
        return view;
    }
}