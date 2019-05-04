package com.example.filmeo.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.filmeo.R;

import java.util.ArrayList;
import java.util.List;

public class ListaGenerosFragment extends Fragment {

    List<String> generos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_genero, container, false);
        generos = new ArrayList<>();
        return view;
    }


}
