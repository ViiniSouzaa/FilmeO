package com.example.filmeo.activity;

import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.filmeo.R;

public class GerenciaGenerosActivity extends AppCompatActivity {

    private AdapterListaFragment adapterListaFragment;

    private ViewPager viewPager;

    TabLayout tabLayout;
    TabItem tabItemLista;
    TabItem tabItemAdicionar;
    FrameLayout frameLayout;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generos);

        adapterListaFragment = new AdapterListaFragment(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.containerGeneros);
        configuraAdapter(viewPager);
        tabLayout = findViewById(R.id.tabLayoutOpcaoGenero);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void configuraAdapter(ViewPager viewPager){
        AdapterListaFragment adapter = new AdapterListaFragment(getSupportFragmentManager());
        adapter.addFragment(new ListaGenerosFragment(), "LISTA GENEROS");
        adapter.addFragment(new AdicionaGeneroFragment(), "ADICIONA GENERO");
        viewPager.setAdapter(adapter);
    }
}
