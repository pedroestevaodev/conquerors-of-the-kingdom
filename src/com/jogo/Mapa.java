package com.jogo;

import com.player.Player;
import com.territorio.Reino;
import com.utils.Utils;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.*;

public class Mapa {
    private Player player;
    private List<Reino> reinos;
    private Scanner scanner;

    public Mapa(Player player, List<Reino> reinos) {
        this.player = player;
        this.reinos = reinos;
        this.scanner = new Scanner(in);
    }

    public void abrirMapa(Reino reino) {
        Utils ut = new Utils(scanner);

        ut.exibirTextoPausado("\nExplorando o mapa...\n");

        out.println("\n******************  REINOS  ******************");
        for (int i = 0; i < reinos.size(); i++) {
            out.println((i+1) + ". " + reinos.get(i).getNome());
        }
        out.println("0. Voltar");

        int opcaoMapa = ut.gerarPerguntaInt("Escolha um reino para interagir: ");

        if (opcaoMapa >= 1 && opcaoMapa <= reinos.size()) {
            reinos.get(opcaoMapa - 1).interagirReino(reino);
        } else if (opcaoMapa == 0) {
            ut.exibirTextoPausado("\nVoltando ao menu principal...\n");
        } else {
            out.println("\nOpção inválida. Por favor, escolha uma opção válida.");
        }
    }
}
