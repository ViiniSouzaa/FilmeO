package com.example.filmeo.recycleAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.filmeo.R;
import com.example.filmeo.model.Filme;

import java.util.List;

public class FilmeAdapter extends RecyclerView.Adapter<FilmeAdapter.MyViewHolder> {

    List<Filme> lista;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textViewnome;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewnome = itemView.findViewById(R.id.textViewNomeFIlmeAdapter);
        }
    }

    public FilmeAdapter(List<Filme> lista) {
            System.out.println("LISTA NULA");
            this.lista = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemLista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.filme_adapter, viewGroup, false);
        return new FilmeAdapter.MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Filme filme = lista.get(i);

        myViewHolder.textViewnome.setText(filme.getNome());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


}
