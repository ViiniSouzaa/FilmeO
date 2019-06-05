package com.example.filmeo.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;

import com.example.filmeo.R;
import com.example.filmeo.database.FilmesDatabase;
import com.example.filmeo.model.Filme;
import com.example.filmeo.recycleAdapter.FilmeAdapter;
import com.example.filmeo.utils.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListaFilmesNaoAssistidosActivity extends Fragment {

    Spinner spinnerOrderBy;
    RecyclerView.LayoutManager layoutManager;
    int order;
    List<Filme> filmes;
    View view;
    ListView listView;
    SearchView searchViewfilmes;

    private ActionMode actionMode;
    private int posicaoSelecionado = -1;
    private View viewSelecionada;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_filmes, container, false);

        spinnerOrderBy = view.findViewById(R.id.spinnerOrderBy);
        listView = view.findViewById(R.id.listViewFilmes);
        layoutManager = new LinearLayoutManager(view.getContext());
        order = 1;

        searchViewfilmes = view.findViewById(R.id.searchViewFilme);

        spinnerOrderBy.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String ordem = parent.getItemAtPosition(position).toString();
                        if(ordem.equals(getString(R.string.az))){
                            order = 0;
                            verificaOrdemLista();
                        }else
                        if(ordem.equals(getString(R.string.za))){
                            order = 1;
                            verificaOrdemLista();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );

        filmes = FilmesDatabase.getDatabase(getActivity().getBaseContext()).filmeDAO().listaNaoAssistidos();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                posicaoSelecionado = position;
                alterarSelecionado();
            }
        });

        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (actionMode != null) {
                    return false;
                }
                posicaoSelecionado = position;
                view.setBackgroundColor(Color.LTGRAY);
                viewSelecionada = view;
                listView.setEnabled(true);
                actionMode = ((AppCompatActivity) getActivity()).startSupportActionMode(mActionModeCallback);
                return true;
            }
        });
        verificaOrdemLista();
        acaoSearchView();
        return view;
    }

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.menu_acoes, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menuItemAlterar:
                    alterarSelecionado();
                    mode.finish();
                    return true;

                case R.id.menuItemExcluir:
                    confirmaExcluir();
                    mode.finish();
                    return true;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            if (viewSelecionada != null) {
                viewSelecionada.setBackgroundColor(Color.TRANSPARENT);
            }
            actionMode = null;
            viewSelecionada = null;

            listView.setEnabled(true);
        }
    };

    private void confirmaExcluir() {
        AlertDialog.Builder confirma = new AlertDialog.Builder(getActivity());
        confirma.setMessage(getString(R.string.deseja_excluir));
        confirma.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Filme filme = FilmesDatabase.getDatabase(getContext()).filmeDAO().listaPorId(filmes.get(posicaoSelecionado).getId());
                FilmesDatabase.getDatabase(getContext()).filmeDAO().deletar(filme);
                verificaOrdemLista();
            }
        });
        confirma.setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        confirma.show();
    }

    private void alterarSelecionado() {
        Intent intent = new Intent(getContext(), EditaFilmeActivity.class);
        intent.putExtra("idFilme", filmes.get(posicaoSelecionado).getId());
        startActivity(intent);
    }


    private void listar() {
        ArrayAdapter<Filme> filmeArrayAdapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_list_item_1, filmes);
        listView.setAdapter(filmeArrayAdapter);
    }

    private void verificaOrdemLista() {
        switch (order){
            case 0 :
                filmes = FilmesDatabase.getDatabase(getContext()).filmeDAO().listaNaoAssistidos();
                listar();
                break;
            case 1 :
                filmes = FilmesDatabase.getDatabase(getContext()).filmeDAO().listaNaoAssistidosInvertido();
                listar();
                break;
        }
    }

    private void acaoSearchView() {
        searchViewfilmes.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                consultaFilme(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                consultaFilme(newText);
                return false;
            }
        });
    }
    private void consultaFilme(String texto) {
        if(texto == null){
            verificaOrdemLista();
        }else {
            String temp = "%" + texto + "%";
            switch (order){
                case 0 :
                    filmes = FilmesDatabase.getDatabase(getContext()).filmeDAO().listaNaoAssistidoPorNome(temp);
                    listar();
                    break;
                case 1 :
                    filmes = FilmesDatabase.getDatabase(getContext()).filmeDAO().listaNaoAssistidoPorNomeInvertido(temp);
                    listar();
                    break;
            }
        }
    }
}
