package com.territorio;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorReinos {
    private static final int NUMERO_MINIMO_REINOS = 3;
    private static final int NUMERO_MAXIMO_REINOS = 8;

    public List<Reino> gerarReinos() {
        List<Reino> reinos = new ArrayList<>();
        int numeroReinos = (int) (Math.random() * (NUMERO_MAXIMO_REINOS - NUMERO_MINIMO_REINOS + 1)) + NUMERO_MINIMO_REINOS;

        for (int i = 0; i < numeroReinos; i++) {
            int recursosReino = (int) (Math.random() * (500 - 80 + 1));
            reinos.add(new Reino("Reino " + (i + 1), recursosReino));
        }

        return reinos;
    }
}
