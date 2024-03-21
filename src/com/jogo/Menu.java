package com.jogo;

import com.player.Player;
import com.territorio.Edificio;
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
            out.println("2. Analisar Reino");
            out.println("3. Construir Edifícios");
            out.println("4. Gerenciar Tropas");
            out.println("0. Sair do Jogo");

            int menuOpcao = ut.gerarPerguntaInt("Escolha uma opção: ");

            switch (menuOpcao) {
                case 1:
                    Mapa mapa = new Mapa(player, reinos);
                    mapa.abrirMapa(player.getReino());
                    break;
                case 2:
                    int opcaoReino = 0;

                    while (opcaoReino != 0) {
                        out.println("\n**************  ANALISAR REINO  **************");
                        out.println("1. Verificar Recursos");
                        out.println("2. Verificar População");
                        out.println("3. Varificar Edifícios");
                        out.println("0. Voltar");

                        opcaoReino = ut.gerarPerguntaInt("Escolha uma opção: ");

                        switch (opcaoReino) {
                            case 1:
                                out.println("\nRecursos disponíveis:");
                                out.println("Ouro: " + player.getReino().getRecursos());
                                break;
                            case 2:
                                out.println("\nPopulação atual:");
                                out.println(player.getReino().getTropas() + "/" + player.getReino().getPopulacao());
                                break;
                            case 3:
                                out.println("\nEdifícios:");
                                for (Edificio edificio : player.getReino().getEdificios()) {
                                    out.println(edificio);
                                }
                                break;
                            case 0:
                                out.println("\nVoltando ao menu...");
                                break;
                            default:
                                out.println("\nOpção inválida. Por favor, escolha uma opção válida.");
                        }
                    }
                    break;
                case 3:
                    Edificio edificio = new Edificio();
                    edificio.construirEdificios(player.getReino());
                    break;
                case 4:
                    break;
                case 0:
                    exit(0);
                    break;
                default:
                    out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        }
    }
}
