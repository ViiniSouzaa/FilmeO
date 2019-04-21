package com.example.filmeo.activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.filmeo.R;
import com.example.filmeo.database.DBHelper;
import com.example.filmeo.database.FilmeDAO;
import com.example.filmeo.model.Filme;
import com.example.filmeo.recycleAdapter.FilmeAdapter;
import com.example.filmeo.utils.RecyclerItemClickListener;

import java.util.List;

public class FilmesAssistidosActivity extends Fragment {

    DBHelper db;
    SQLiteDatabase connection;
    FilmeDAO filmeDAO;
    EditText editTextNomeFilme;
    ImageButton buttonPesquisa;
    Spinner spinnerOrderBy;
    RecyclerView recyclerViewFilmes;
    RecyclerView.LayoutManager layoutManager;
    Boolean order;
    List<Filme> filmes;
    FilmeAdapter filmeAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_filmes, container, false);

        db = new DBHelper(view.getContext());
        connection = db.getReadableDatabase();
        filmeDAO = new FilmeDAO(connection);
        editTextNomeFilme = view.findViewById(R.id.editTextSearchFilme);
        buttonPesquisa = view.findViewById(R.id.imageButtonSearchFIlme);
        spinnerOrderBy = view.findViewById(R.id.spinnerOrderBy);
        recyclerViewFilmes = view.findViewById(R.id.recycleViewFilmes);
        layoutManager = new LinearLayoutManager(view.getContext());
        order = true;

        recyclerViewFilmes.setLayoutManager(layoutManager);
        recyclerViewFilmes.setHasFixedSize(true);
        recyclerViewFilmes.addItemDecoration(new DividerItemDecoration(view.getContext(), LinearLayout.VERTICAL));

        filmes = filmeDAO.getAllAssistidos(order);
        filmeAdapter = new FilmeAdapter(filmes);

        recyclerViewFilmes.setAdapter(filmeAdapter);
        recyclerViewFilmes.addOnItemTouchListener(
                new RecyclerItemClickListener(view.getContext(), recyclerViewFilmes, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                })
        );

        buttonPesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filmes = filmeDAO.getByNomeAssistido(String.valueOf(editTextNomeFilme.getText()), order);
                filmeAdapter = new FilmeAdapter(filmes);
                recyclerViewFilmes.setAdapter(filmeAdapter);
            }
        });

        return view;
    }
}
