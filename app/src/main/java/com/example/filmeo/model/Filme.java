package com.example.filmeo.model;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(foreignKeys = @ForeignKey(entity = Diretor.class,
                                  parentColumns = "id",
                                  childColumns = "id_diretor", onDelete = ForeignKey.CASCADE))
public class Filme {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String nome;
    private String descricao;

    @ColumnInfo(index = true)
    private int id_diretor;

    @NonNull
    private boolean assistido;

    public Filme() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId_diretor() {
        return id_diretor;
    }

    public void setId_diretor(int id_diretor) {
        this.id_diretor = id_diretor;
    }

    public boolean isAssistido() {
        return assistido;
    }

    public void setAssistido(boolean assistido) {
        this.assistido = assistido;
    }

    @Override
    public String toString() {
        return getNome();
    }
}
