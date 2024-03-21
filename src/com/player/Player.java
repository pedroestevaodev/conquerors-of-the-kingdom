package com.player;

import com.territorio.Reino;

public class Player {
    private String nome;
    private String genero;
    private Reino reino;

    public Player(String nome, String genero, Reino reino) {
        this.nome = nome;
        this.genero = genero;
        this.reino = reino;
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

    public Reino getReino() {
        return reino;
    }

    public void setReino(Reino reino) {
        this.reino = reino;
    }
}
