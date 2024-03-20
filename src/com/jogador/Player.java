package com.jogador;

public class Player {
    private String nome;
    private String genero;
    private String nomeReino;

    public Player(String nome, String genero, String nomeReino) {
        this.nome = nome;
        this.genero = genero;
        this.nomeReino = nomeReino;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNomeReino() {
        return nomeReino;
    }

    public void setNomeReino(String nomeReino) {
        this.nomeReino = nomeReino;
    }
}
