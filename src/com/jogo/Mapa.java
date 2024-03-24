package com.jogo;

import com.player.Player;
import com.territorio.Reino;
import com.utils.Utils;
import java.util.List;
import java.util.Scanner;
import static java.lang.System.*;

public class Mapa {
    private final Player player;
    private final List<Reino> reinos;
    private final Scanner scanner;

    public Mapa(Player player, List<Reino> reinos) {
        this.player = player;
        this.reinos = reinos;
        this.scanner = new Scanner(in);
    }

    public Player getPlayer() {
        return player;
    }

    public List<Reino> getReinos() {
        return reinos;
    }

    public void abrirMapa() {
        Utils ut = new Utils(scanner);

        ut.exibirTextoPausado("\nExplorando o mapa...\n");

        while (true) {
            out.println("\n******************  REINOS  ******************");
            for (int i = 0; i < getReinos().size(); i++) {
                if (getPlayer().getReino().getAliados().contains(getReinos().get(i))) {
                    out.println((i+1) + ". " + getReinos().get(i).getNome() + " [Reino Aliado]");
                } else {
                    out.println((i+1) + ". " + getReinos().get(i).getNome());
                }
            }
            out.println("0. Voltar");

            int opcaoMapa = ut.gerarPerguntaInt("Escolha um reino para interagir: ");

            if (opcaoMapa >= 1 && opcaoMapa <= getReinos().size()) {
                getReinos().get(opcaoMapa - 1).interagirReino(getPlayer());
            } else if (opcaoMapa == 0) {
                ut.exibirTextoPausado("\nVoltando ao menu principal...\n");
                return;
            } else {
                out.println("\nOpção inválida. Por favor, escolha uma opção válida.");
            }
        }
    }
}
