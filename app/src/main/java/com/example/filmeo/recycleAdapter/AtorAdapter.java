package com.example.filmeo.recycleAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.filmeo.R;
import com.example.filmeo.model.Ator;
import com.example.filmeo.utils.RecyclerItemClickListener;

import java.util.List;

public class AtorAdapter extends RecyclerView.Adapter<AtorAdapter.MyViewHolder>{

    List<Ator> lista;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textViewnome;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewnome = itemView.findViewById(R.id.textViewAtorAdapter);
        }
    }

    public AtorAdapter(List<Ator> lista){
        this.lista = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemLista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ator_adapter, viewGroup, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Ator ator = lista.get(i);

        myViewHolder.textViewnome.setText(ator.getNome());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
