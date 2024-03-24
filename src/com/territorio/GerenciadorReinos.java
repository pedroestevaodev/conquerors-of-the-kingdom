package com.territorio;

import com.exercito.Tropa;
import com.jogo.Mensagens;
import java.util.*;
import static java.lang.System.*;
import org.jetbrains.annotations.NotNull;

public class GerenciadorReinos {
    private static final int NUMERO_MINIMO_REINOS = 3;
    private static final int NUMERO_MAXIMO_REINOS = 10;

    public List<Reino> gerarReinos() {
        Mensagens msg = new Mensagens();
        List<Reino> reinos = new ArrayList<>();
        Random random = new Random();
        int numeroReinos = (int) (Math.random() * (NUMERO_MAXIMO_REINOS - NUMERO_MINIMO_REINOS + 1)) + NUMERO_MINIMO_REINOS;

        for (int i = 0; i < numeroReinos; i++) {
            int recursosReino = (int) (Math.random() * (5000 - 800 + 1));
            int populacaoReino = (int) (Math.random() * (200 - 10 + 1));
            Set<String> nomesReinos = msg.obterDados("/resources/reinos.properties");

            String nomeAleatorio = "";

            if (nomesReinos != null && !nomesReinos.isEmpty()) {
                nomeAleatorio = msg.aleatorizarDados(nomesReinos, random);

                while (existeNomeReino(reinos, nomeAleatorio)) {
                    nomeAleatorio = msg.aleatorizarDados(nomesReinos, random);
                }
            } else {
                out.println("\nNão foi possível carregar os nomes aleatórios para os reinos.");
            }

            Reino reino = new Reino(nomeAleatorio, recursosReino, populacaoReino);

            List<Edificio> edificios = new ArrayList<>();
            int numeroEdificios = random.nextInt(10) + 1;
            for (int j = 0; j < numeroEdificios; j ++) {
                Edificio edificioGerado = gerarEdificios();
                edificios.add(edificioGerado);
                if (edificioGerado.getNome().equalsIgnoreCase("Mina de Ouro")) {
                    iniciarGeracaoDeOuro(reino);
                }
            }
            reino.setEdificios(edificios);

            List<Tropa> tropas = new ArrayList<>();
            int numeroTropas = random.nextInt(30) + 1;
            for (int j = 0; j < numeroTropas; j ++) {
                tropas.add(gerarTropas());
            }
            reino.setTropas(tropas);
            reino.atualizaForcaDefesaReino();

            reinos.add(reino);
        }

        return reinos;
    }

    private boolean existeNomeReino(@NotNull List<Reino> reinos, @NotNull String nome) {
        for (Reino reino : reinos) {
            if (reino.getNome().equals(nome)) {
                return true;
            }
        }
        return false;
    }

    private Edificio gerarEdificios() {
        Random random = new Random();
        int tipo = random.nextInt(3);

        return switch (tipo) {
            case 0 -> new Edificio("Quartel", 500, 20);
            case 1 -> new Edificio("Mina de Ouro", 800, 0);
            case 2 -> new Edificio("Torre de Defesa", 300, 8);
            default -> null;
        };
    }

    private Tropa gerarTropas() {
        Random random = new Random();
        int tipo = random.nextInt(3);

        return switch (tipo) {
            case 0 -> new Tropa("Arqueiro", 60, 100, 20, 15);
            case 1 -> new Tropa("Cavaleiro", 100, 100, 50, 40);
            case 2 -> new Tropa("Lanceiro", 40, 100, 10, 7);
            default -> null;
        };
    }

    private void iniciarGeracaoDeOuro(@NotNull Reino reino) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                reino.setRecursos(reino.getRecursos() + 1);
            }
        }, 5000, 5000);
    }
}
