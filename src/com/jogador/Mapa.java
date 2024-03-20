package com.jogador;

public class Mapa {
    private boolean[][] territorios;

    public  Mapa(int tamanho) {
        territorios = new boolean[tamanho][tamanho];
        inicializarMapa();
    }

    private void inicializarMapa() {
        for (int i =0; i < territorios.length; i++) {
            for (int j = 0; j < territorios[0].length; j++) {
                territorios[i][j] = false;
            }
        }
    }

    public void explorarTerritorio(int x, int y) {
        if (x >= 0 && x < territorios.length && y >= 0 && y < territorios[0].length) {
            if (!territorios[x][y]) {
                System.out.println("Território explorado com sucesso!");
                territorios[x][y] = true;
                // Aqui você pode adicionar lógica para ganhar recursos, descobrir inimigos, etc.
            } else {
                System.out.println("Este território já foi explorado!");
            }
        } else {
            System.out.println("Coordenadas inválidas!");
        }
    }
}
