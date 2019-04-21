package com.example.filmeo.model;

import java.util.List;

public class Filme {

    private int id;
    private String nome;
    private String descricao;
    private List<Ator> atores;
    private Diretor diretor;
    private boolean assistido;

    public Filme() {

    }

    public boolean adcionaAtores(List<Ator> atores){
        if(atores.size() <= 3)
            return true;
         return false;
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

    public List<Ator> getAtores() {
        return atores;
    }

    public void setAtores(List<Ator> atores) {
        this.atores = atores;
    }

    public Diretor getDiretor() {
        return diretor;
    }

    public void setDiretor(Diretor diretor) {
        this.diretor = diretor;
    }

    public boolean isAssistido() {
        return assistido;
    }

    public void setAssistido(boolean assistido) {
        this.assistido = assistido;
    }
}
