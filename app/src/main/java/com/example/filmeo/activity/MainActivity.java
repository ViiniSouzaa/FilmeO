package com.example.filmeo.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.filmeo.R;

public class MainActivity extends AppCompatActivity {

    private static final String ARQUIVO = "com.example.filmeo.PREFERECIA_DARK";
    private static final String OPCAO = "TEMA";

    private boolean dark;

    TabLayout tabLayout;
    private ViewPager viewPager;
    private AdapterListaFragment adapterListaFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lerPreferenciaTema();
        adapterListaFragment = new AdapterListaFragment(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.viewPagerFilmes);
        configuraAdapter(viewPager);
        tabLayout = findViewById(R.id.tabLayoutFilmes);
        tabLayout.setupWithViewPager(viewPager);

    }

    public void configuraAdapter(ViewPager viewPager){
        AdapterListaFragment adapter = new AdapterListaFragment(getSupportFragmentManager());
        adapter.addFragment(new ListaFilmesAssistidosActivity(), getString(R.string.filme_assistido));
        adapter.addFragment(new ListaFilmesNaoAssistidosActivity(), getString(R.string.filme_nao_assistido));
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.claro) {
            salvaPreferencia(false);
            return true;
        }else if(id == R.id.escuro){
            salvaPreferencia(true);
            return true;
        }
        return false;
    }

    public void adicionarFilme(View view) {
        startActivity(new Intent(getBaseContext(), AdicionarFilmeActivity.class));
    }

    public void sobre(View view){
        startActivity(new Intent(getBaseContext(), SobreActivity.class));
    }

    public void salvaPreferencia(boolean opcao){
        SharedPreferences s = getSharedPreferences(ARQUIVO, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = s.edit();

        editor.putBoolean(OPCAO, opcao);

        editor.commit();

        dark = opcao;

        mudaTema();
    }

    public void lerPreferenciaTema(){
        SharedPreferences s = getSharedPreferences(ARQUIVO, Context.MODE_PRIVATE);

        dark = s.getBoolean(OPCAO, false);

        mudaTema();
    }

    private void mudaTema() {
        if(dark){
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else if(!dark){
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
