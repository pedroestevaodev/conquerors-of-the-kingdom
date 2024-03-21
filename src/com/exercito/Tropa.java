package com.exercito;

public class Tropa {
    private String nome;
    private int custoTreinamento;
    private int forca;
    private int defesa;

    public Tropa(String nome, int custoTreinamento) {
        this.nome = nome;
        this.custoTreinamento = custoTreinamento;
    }

    public String getNome() {
        return nome;
    }

    public int getCustoTreinamento() {
        return custoTreinamento;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }
}
