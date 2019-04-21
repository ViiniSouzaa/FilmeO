package com.example.filmeo.activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.filmeo.R;
import com.example.filmeo.activity.AdicionarDiretorActivity;
import com.example.filmeo.database.DBHelper;
import com.example.filmeo.database.DiretorDAO;
import com.example.filmeo.model.Diretor;
import com.example.filmeo.recycleAdapter.DiretorAdapter;
import com.example.filmeo.utils.RecyclerItemClickListener;

import java.util.List;

public class ListaDiretoresActivity extends AppCompatActivity {

    private Button adicionarNovoDiretor;
    private RecyclerView recyclerViewDiretor;
    private RecyclerView.LayoutManager layoutManager;
    private DiretorAdapter diretorAdapter;
    private List<Diretor> diretores;
    private SQLiteDatabase connection;
    private DiretorDAO diretorDAO;
    private DBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_diretor);

        db =  new DBHelper(this);
        connection = db.getWritableDatabase();
        diretorDAO =  new DiretorDAO(connection);

        recyclerViewDiretor = findViewById(R.id.recycleViewDiretores);

        layoutManager = new LinearLayoutManager(this);

        recyclerViewDiretor.setLayoutManager(layoutManager);
        recyclerViewDiretor.setHasFixedSize(true);
        recyclerViewDiretor.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        popularLista();

        recyclerViewDiretor.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerViewDiretor, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Diretor diretor = diretores.get(position);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        Diretor diretor = diretores.get(position);
                    }
                })
        );

        adicionarNovoDiretor = findViewById(R.id.buttonAdicionaDiretor);
    }

    private void popularLista() {
        diretores = diretorDAO.getAll();
        diretorAdapter = new DiretorAdapter(diretores);
        recyclerViewDiretor.setAdapter(diretorAdapter);
    }

    public void AdicionaDiretor(View view){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.FrameLayoutAdicionaDiretor, new AdicionarDiretorActivity()).commit();
    }

}
