package com.jogo;

import com.exercito.Tropa;
import com.player.Player;
import com.territorio.Edificio;
import com.territorio.Reino;
import com.utils.Utils;
import java.util.List;
import java.util.Scanner;
import static java.lang.System.*;

public class Menu {
    private final Player player;
    private final List<Reino> reinos;
    private final Scanner scanner;

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
                    mapa.abrirMapa();
                    break;
                case 2:
                    analizarReino();
                    break;
                case 3:
                    Edificio edificio = new Edificio();
                    edificio.construirEdificios(player.getReino());
                    break;
                case 4:
                    gerenciarTropas();
                    break;
                case 0:
                    exit(0);
                    break;
                default:
                    out.println("\nOpção inválida. Por favor, escolha uma opção válida.");
            }
        }
    }

    private void analizarReino() {
        Utils ut = new Utils(scanner);
        Mensagens msg = new Mensagens();

        while (true) {
            out.println("\n**************  ANALISAR REINO  **************");
            out.println("1. Verificar Recursos");
            out.println("2. Verificar População");
            out.println("3. Verificar Edifícios");
            out.println("0. Voltar");

            int opcaoReino = ut.gerarPerguntaInt("Escolha uma opção: ");

            switch (opcaoReino) {
                case 1:
                    ut.exibirTextoPausado("\nRecursos disponíveis:");
                    ut.exibirTextoPausado("\n- Ouro: " + player.getReino().getRecursos() + "\n");
                    break;
                case 2:
                    ut.exibirTextoPausado("\nPopulação atual:");
                    int populacao = player.getReino().getTropas().toArray().length;
                    ut.exibirTextoPausado("\n- " + populacao + "/" + player.getReino().getPopulacao() + "\n");
                    break;
                case 3:
                    ut.exibirTextoPausado("\nEdifícios:");
                    if (player.getReino().getEdificios().toArray().length == 0) {
                        ut.exibirTextoPausado("\n- Seu reino ainda não possui edifícios!\n");
                    } else {
                        Edificio edificios = new Edificio();
                        edificios.listarEdificios(player.getReino());
                    }
                    break;
                case 0:
                    ut.exibirTextoPausado("\nVoltando ao menu...\n");
                    return;
                default:
                    out.println("\nOpção inválida. Por favor, escolha uma opção válida.");
            }
        }
    }

    private void gerenciarTropas() {
        Utils ut = new Utils(scanner);
        Mensagens msg = new Mensagens();

        while (true) {
            out.println("\n*************  GERENCIAR TROPAS  *************");
            out.println("1. Verificar Força/Defesa do Reino");
            out.println("2. Verificar Tropas");
            out.println("3. Expandir Tropas");
            out.println("4. Treinar Tropas");
            out.println("0. Voltar");

            int opcaoReino = ut.gerarPerguntaInt("Escolha uma opção: ");

            switch (opcaoReino) {
                case 1:
                    ut.exibirTextoPausado("\nForça/Defesa do Reino:");
                    ut.exibirTextoPausado("\n- Força: " + player.getReino().getForcaDefesa().forca());
                    ut.exibirTextoPausado("\n- Defesa: " + player.getReino().getForcaDefesa().defesa());
                    out.println();
                    break;
                case 2:
                    ut.exibirTextoPausado("\nTropas em Serviço ["+ player.getReino().getTropas().toArray().length + "/" + player.getReino().getPopulacao() + "]:");
                    if (player.getReino().getTropas().toArray().length == 0) {
                        ut.exibirTextoPausado("\n- Seu reino ainda não possui tropas em serviço! Que tal contratar?\n");
                    } else {
                        Tropa tropas = new Tropa();
                        tropas.listarTropas(player.getReino());
                    }
                    break;
                case 3:
                    Tropa tropaContratar = new Tropa();
                    tropaContratar.contratar(player.getReino());
                    break;
                case 4:
                    Tropa tropaTreinar = new Tropa();
                    tropaTreinar.treinar(player.getReino());
                    break;
                case 0:
                    ut.exibirTextoPausado("\nVoltando ao menu...\n");
                    return;
                default:
                    out.println("\nOpção inválida. Por favor, escolha uma opção válida.");
            }
        }
    }
}
