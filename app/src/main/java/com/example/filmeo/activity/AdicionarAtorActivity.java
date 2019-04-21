package com.example.filmeo.activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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
import com.example.filmeo.model.Ator;

public class AdicionarAtorActivity extends Fragment{

    AtorDAO atorDAO;
    DBHelper db;
    EditText nomeAtor;
    SQLiteDatabase connection;

    Button novoAtor;

    public AdicionarAtorActivity() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_adiciona_novo_ator, container, false);

        db = new DBHelper(view.getContext());
        connection = db.getWritableDatabase();

        atorDAO = new AtorDAO(connection);

        nomeAtor = view.findViewById(R.id.editTextNomeAtor);
        novoAtor = (Button) view.findViewById(R.id.buttonNovoAtor);
        novoAtor.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Ator ator = new Ator();
                ator.setNome(String.valueOf(nomeAtor.getText()));
                atorDAO.insert(ator);
                getActivity().finish();
            }
        });
        return view;
    }


}