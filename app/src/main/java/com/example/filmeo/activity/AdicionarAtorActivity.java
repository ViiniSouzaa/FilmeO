package com.example.filmeo.activity;

import android.annotation.SuppressLint;
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

@SuppressLint("ValidFragment")
public class AdicionarAtorActivity extends Fragment{

    AtorDAO atorDAO;
    DBHelper db;
    EditText nomeAtor;
    SQLiteDatabase connection;
    Button novoAtor;
    Bundle bundle;
    int[] atores_id;


    @SuppressLint("ValidFragment")
    public AdicionarAtorActivity(Bundle bundle) {
        this.bundle = bundle;
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
                addAtorBundle(atorDAO.getLastAtor().getId());
                getActivity().finish();
            }
        });
        return view;
    }

    public void addAtorBundle(int id){
        iniciaAtores();
        trocaVetor(bundle.getIntArray("atores"));
        for(int i = 0; i < 3; i++){
            if(atores_id[i] == -1){
                atores_id[i] = id;
                break;
            }
        }
    }

    public void trocaVetor(int[] atores_recuperados){
        if(atores_recuperados != null) {
            for (int i = 0; i < 3; i++) {
                if (atores_recuperados[i] != -1) {
                    atores_id[i] = atores_recuperados[i];
                }
            }
        }
    }

    public void iniciaAtores(){
        atores_id = new int[3];
        for (int i = 0; i < 3; i++){
            atores_id[i] = -1;
        }
    }

}