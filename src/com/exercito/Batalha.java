package com.exercito;

import java.util.Random;

public class Batalha {
    public void iniciarBatalha(Tropa tropaJogador, Tropa tropaInimigo) {
        Random random = new Random();
        int forcaJogador = tropaJogador.getForca();
        int forcaInimigo = tropaInimigo.getForca();

        if (forcaJogador > forcaInimigo) {
            System.out.println("Você venceu a batalha!");
        } else if (forcaJogador < forcaInimigo) {
            System.out.println("Você perdeu a batalha!");
        } else {
            System.out.println("Empate!");
        }
    }
}
