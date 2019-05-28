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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.filmeo.R;
import com.example.filmeo.database.FilmeDatabase;
import com.example.filmeo.model.Filme;
import com.example.filmeo.recycleAdapter.FilmeAdapter;
import com.example.filmeo.utils.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListaFilmesAssistidosActivity extends Fragment {

    EditText editTextNomeFilme;
    ImageButton buttonPesquisa;
    Spinner spinnerOrderBy;
    RecyclerView recyclerViewFilmes;
    RecyclerView.LayoutManager layoutManager;
    Boolean order;
    List<Filme> filmes;
    FilmeAdapter filmeAdapter;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_filmes, container, false);

        spinnerOrderBy = view.findViewById(R.id.spinnerOrderBy);
        recyclerViewFilmes = view.findViewById(R.id.recycleViewFilmes);
        layoutManager = new LinearLayoutManager(view.getContext());
        order = true;
        filmes = new ArrayList<>();

        spinnerOrderBy.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String ordem = parent.getItemAtPosition(position).toString();
                        if(ordem.equals(getString(R.string.az))){
                            order = true;
                            verificaOrdemLista();
                        }else
                        if(ordem.equals(getString(R.string.az))){
                            order = false;
                            verificaOrdemLista();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );

        recyclerViewFilmes.setLayoutManager(layoutManager);
        recyclerViewFilmes.setHasFixedSize(true);
        recyclerViewFilmes.addItemDecoration(new DividerItemDecoration(view.getContext(), LinearLayout.VERTICAL));

        filmes = FilmeDatabase.getDatabase(getContext()).filmeDAO().listaAssistidos();

        if(filmes == null) {
            filmes = new ArrayList<>();
        }
        verificaOrdemLista();
        return view;
    }

    private void listar() {
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
    }

    private void verificaOrdemLista() {
        if(!order){
            Collections.reverse(filmes);
            listar();
        }else if(order){
            filmes = FilmeDatabase.getDatabase(getContext()).filmeDAO().listaAssistidos();
            listar();
        }
    }
}
