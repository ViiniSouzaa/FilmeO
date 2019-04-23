package com.example.filmeo.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.filmeo.R;
import com.example.filmeo.activity.AdicionarAtorActivity;
import com.example.filmeo.database.AtorDAO;
import com.example.filmeo.database.DBHelper;
import com.example.filmeo.model.Ator;
import com.example.filmeo.recycleAdapter.AtorAdapter;
import com.example.filmeo.utils.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class ListaAtoresActivity extends AppCompatActivity {

    private Button adicionarNovoAtor;
    private RecyclerView recyclerViewAtores;
    private RecyclerView.LayoutManager layoutManager;
    private AtorAdapter atorAdapter;
    private List<Ator> atores;
    private SQLiteDatabase connection;
    private AtorDAO atorDAO;
    private DBHelper db;
    private String nomeFilme, descricaoFilme;
    private int diretor;
    private int[] atores_id;
    private boolean assistido;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_atores);

        db = new DBHelper(this);
        connection = db.getWritableDatabase();
        atorDAO = new AtorDAO(connection);
        intent = getIntent();
        iniciaAtores();
        recuperaDados();

        recyclerViewAtores = findViewById(R.id.recycleViewAtores);

        layoutManager = new LinearLayoutManager(this);

        recyclerViewAtores.setLayoutManager(layoutManager);
        recyclerViewAtores.setHasFixedSize(true);
        recyclerViewAtores.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        atores = atorDAO.getAll();
        atorAdapter = new AtorAdapter(atores);

        recyclerViewAtores.setAdapter(atorAdapter);
        recyclerViewAtores.addOnItemTouchListener(
            new RecyclerItemClickListener(getApplicationContext(),recyclerViewAtores, new RecyclerItemClickListener.OnItemClickListener(){

                @Override
                public void onItemClick(View view, int position) {
                    Ator ator = atores.get(position);
                }

                @Override
                public void onLongItemClick(View view, int position) {
                    Ator ator = atores.get(position);
                }
            })
        );

        adicionarNovoAtor = findViewById(R.id.buttonAdicionaAtor);

    }

    public void AdicionaAtor(View view){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.FrameLayoutAdicionarAtores, new AdicionarAtorActivity(createSaved())).commit();
    }

    private void recuperaDados(){
        nomeFilme = intent.getStringExtra("nome");
        descricaoFilme = intent.getStringExtra("descricao");
        trocaVetor(intent.getIntArrayExtra("atores"));
        diretor = intent.getIntExtra("diretor", -1);
        assistido = intent.getBooleanExtra("assistido", false);
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

    public Bundle createSaved(){
        Bundle bundle = new Bundle();
        bundle.putString("nomeFilme", nomeFilme);
        bundle.putString("descricaoFilme", descricaoFilme);
        bundle.putIntArray("atores", atores_id);
        bundle.putInt("diretor", diretor);
        bundle.putBoolean("assistido", assistido);

        return bundle;
    }



}
