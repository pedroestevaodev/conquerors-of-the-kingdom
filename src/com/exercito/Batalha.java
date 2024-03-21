package com.exercito;

import com.territorio.Reino;

import java.util.Random;

public class Batalha {
    private Reino reinoPlayer;
    private Reino reinoInimigo;

    public Batalha(Reino reinoPlayer, Reino reinoInimigo) {
        this.reinoPlayer = reinoPlayer;
        this.reinoInimigo = reinoInimigo;
    }

    public Reino getReinoPlayer() {
        return reinoPlayer;
    }

    public Reino getReinoInimigo() {
        return reinoInimigo;
    }

    public void iniciarBatalha() {
        Random random = new Random();
        int forcaJogador = getReinoPlayer().getForcaDefesa().getForca();
        int forcaInimigo = getReinoInimigo().getForcaDefesa().getForca();
        int defesaJogador = getReinoPlayer().getForcaDefesa().getDefesa();
        int defesaInimigo = getReinoInimigo().getForcaDefesa().getDefesa();

        if (forcaJogador > forcaInimigo) {
            System.out.println("Você venceu a batalha!");
        } else if (forcaJogador < forcaInimigo) {
            System.out.println("Você perdeu a batalha!");
        } else {
            System.out.println("Empate!");
        }
    }
}
