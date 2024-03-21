package com.territorio;

import com.exercito.Tropa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GerenciadorReinos {
    private static final int NUMERO_MINIMO_REINOS = 3;
    private static final int NUMERO_MAXIMO_REINOS = 10;

    public List<Reino> gerarReinos() {
        List<Reino> reinos = new ArrayList<>();
        Random random = new Random();
        int numeroReinos = (int) (Math.random() * (NUMERO_MAXIMO_REINOS - NUMERO_MINIMO_REINOS + 1)) + NUMERO_MINIMO_REINOS;

        for (int i = 0; i < numeroReinos; i++) {
            int recursosReino = (int) (Math.random() * (500 - 80 + 1));
            int populacaoReino = (int) (Math.random() * (200 - 10 + 1));

            Reino reino = new Reino("Reino " + (i + 1), recursosReino, populacaoReino);

            List<Edificio> edificios = new ArrayList<>();
            int numeroEdificios = random.nextInt(10) + 1;
            for (int j = 0; j < numeroEdificios; j ++) {
                edificios.add(gerarEdificios());
            }
            reino.setEdificios(edificios);

            List<Tropa> tropas = new ArrayList<>();
            int numeroTropas = random.nextInt(30) + 1;
            for (int j = 0; j < numeroTropas; j ++) {
                tropas.add(gerarTropas());
            }
            reino.setTropas(tropas);

            reinos.add(reino);
        }

        return reinos;
    }

    private Edificio gerarEdificios() {
        Random random = new Random();
        int tipo = random.nextInt(3);

        return switch (tipo) {
            case 0 -> new Edificio("Quartel", 50, 20);
            case 1 -> new Edificio("Mina de Ouro", 80, 0);
            case 2 -> new Edificio("Torre de Defesa", 30, 8);
            default -> null;
        };
    }

    private Tropa gerarTropas() {
        Random random = new Random();
        int tipo = random.nextInt(3);

        return switch (tipo) {
            case 0 -> new Tropa("Arqueiro", 6);
            case 1 -> new Tropa("Cavaleiro", 10);
            case 2 -> new Tropa("Lanceiro", 4);
            default -> null;
        };
    }
}
