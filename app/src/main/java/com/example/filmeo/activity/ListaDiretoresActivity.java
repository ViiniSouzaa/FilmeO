package com.example.filmeo.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.filmeo.R;
import com.example.filmeo.database.DiretorDatabase;
import com.example.filmeo.model.Diretor;

import java.util.ArrayList;

public class ListaDiretoresActivity extends AppCompatActivity {

    ListView listViewDiretores;
    Intent intent;
    int diretor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diretores);

        intent = getIntent();
        listViewDiretores = findViewById(R.id.listViewDiretores);
        populaLista();
        recuperaDiretor();
        clickListView();
    }

    private void recuperaDiretor() {
        diretor = intent.getIntExtra("diretor", -1);
    }

    public void adicionaNovoDiretor(View view){
        Intent intent = new Intent(getBaseContext(), AdicionarDiretorActivity.class);
        startActivity(intent);
    }

    private void populaLista() {
        ArrayAdapter<Diretor> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                DiretorDatabase.getDatabase(getApplicationContext()).diretorDAO().listaTodos());
        listViewDiretores.setAdapter(adapter);
    }

    public void clickListView() {
        listViewDiretores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Diretor diretor = (Diretor) parent.getAdapter().getItem(position);

                Intent intent = new Intent(getBaseContext(), AdicionarFilmeActivity.class);
                intent.putExtra("diretor", diretor.getId());
                startActivity(intent);
            }
        });

        listViewDiretores.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View arg1, int position, long id) {
                final Diretor diretor = (Diretor) parent.getAdapter().getItem(position);

                final CharSequence[] acoes = {getString(R.string.editar), getString(R.string.deletar), getString(R.string.cancelar)};

                final AlertDialog.Builder listaAcoes = new AlertDialog.Builder(ListaDiretoresActivity.this);
                listaAcoes.setItems(acoes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String acao = (String) acoes[which];

                        if(acao.equals(getString(R.string.editar))){
                            Intent intent = new Intent(getBaseContext(), EditaDiretorActivity.class);
                            intent.putExtra("diretor", diretor.getId());
                            startActivity(intent);
                        }
                        else if(acao.equals(getString(R.string.deletar))){
                            confirmaDeletar(diretor);
                        }
                        else if(acao.equals(getString(R.string.cancelar))){
                            dialog.cancel();
                        }
                    }
                });
                listaAcoes.show();
                return true;
            }
        });
    }

    private void confirmaDeletar(final Diretor diretor) {
        AlertDialog.Builder confirma = new AlertDialog.Builder(this);
        confirma.setMessage(R.string.deseja_excluir);
        confirma.setPositiveButton(getString(R.string.sim), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DiretorDatabase.getDatabase(getApplicationContext()).diretorDAO().deletar(diretor);
                populaLista();
            }
        });
        confirma.setNegativeButton(getString(R.string.nao), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        confirma.show();
    }
}
