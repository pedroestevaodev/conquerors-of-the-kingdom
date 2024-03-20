package com.territorio;

public class Edificio {
    private String nome;
    private int custo;

    public Edificio(String nome, int custo) {
        this.nome = nome;
        this.custo = custo;
    }

    public String getNome() {
        return nome;
    }

    public int getCusto() {
        return custo;
    }
}
