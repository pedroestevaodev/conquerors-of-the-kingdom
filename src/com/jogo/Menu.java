package com.jogo;

import com.player.Player;
import com.territorio.Reino;
import com.utils.Utils;
import java.util.List;
import java.util.Scanner;
import static java.lang.System.*;

public class Menu {
    private Player player;
    private List<Reino> reinos;
    private Scanner scanner;

    public Menu(Player player, List<Reino> reinos) {
        this.player = player;
        this.reinos = reinos;
        this.scanner = new Scanner(in);
        exibirMenu();
    }

    public void exibirMenu() {
        Utils ut = new Utils(scanner);

        while (true) {
            out.println("\n*******************  MENU  *******************");
            out.println("1. Abrir Mapa");
            out.println("2. Verificar Recursos");
            out.println("3. Construir Edifícios");
            out.println("4. Gerenciar Tropas");
            out.println("5. Sair");

            int menuOpcao = ut.gerarPerguntaInt("Escolha uma opção: ");

            switch (menuOpcao) {
                case 1:
                    Mapa mapa = new Mapa(player, reinos);
                    mapa.abrirMapa(player.getReino());
                    break;
                case 2:
                    out.println("Recursos disponíveis:");
                    out.println("Ouro: " + player.getReino().getRecursos());
                    break;
                case 3:

            }
        }
    }
}
