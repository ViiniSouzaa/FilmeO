package com.example.filmeo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Diretor implements Parcelable {

    private int id;
    private String nome;

    public Diretor() {

    }

    public Diretor(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    protected Diretor(Parcel in) {
        id = in.readInt();
        nome = in.readString();
    }

    public static final Creator<Diretor> CREATOR = new Creator<Diretor>() {
        @Override
        public Diretor createFromParcel(Parcel in) {
            return new Diretor(in);
        }

        @Override
        public Diretor[] newArray(int size) {
            return new Diretor[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nome);
    }

    @Override
    public String toString() {
        return this.getNome();
    }
}
