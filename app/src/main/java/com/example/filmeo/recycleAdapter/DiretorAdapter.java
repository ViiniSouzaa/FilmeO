package com.example.filmeo.recycleAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.filmeo.R;
import com.example.filmeo.model.Diretor;

import java.util.List;

public class DiretorAdapter extends RecyclerView.Adapter<DiretorAdapter.MyViewHolder> {

    List<Diretor> lista;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNome;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewNome = itemView.findViewById(R.id.textViewDiretorAdapter);
        }
    }

    public DiretorAdapter(List<Diretor> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public DiretorAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemLista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.diretor_adapter, viewGroup, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Diretor diretor = lista.get(i);

        myViewHolder.textViewNome.setText(diretor.getNome());
    }


    @Override
    public int getItemCount() {
        return lista.size();
    }

}
