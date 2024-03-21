package com.jogo;

import com.player.Player;
import com.territorio.Edificio;
import com.territorio.Reino;
import com.utils.Utils;
import java.util.List;
import java.util.Objects;
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
                    analizarReino();
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
                        int quarteis = 0;
                        int minasDeOuro = 0;
                        int torresDeDefesa = 0;

                        for (Edificio edificio : player.getReino().getEdificios()) {
                            if (edificio.getNome().equalsIgnoreCase("Quartel")) {
                                quarteis++;
                            } else if (edificio.getNome().equalsIgnoreCase("Mina de Ouro")) {
                                minasDeOuro++;
                            } else {
                                torresDeDefesa++;
                            }
                        }

                        ut.exibirTextoPausado("\n- " + quarteis + " " + (msg.exibirMensagem("mensagem.listagemconstrucoes.quartel." + (quarteis == 1 ? "singular" : "plural"))));
                        ut.exibirTextoPausado("\n- " + minasDeOuro + " " + (msg.exibirMensagem("mensagem.listagemconstrucoes.minadeouro." + (minasDeOuro == 1 ? "singular" : "plural"))));
                        ut.exibirTextoPausado("\n- " + torresDeDefesa + " " + (msg.exibirMensagem("mensagem.listagemconstrucoes.torrededefesa." + (torresDeDefesa == 1 ? "singular" : "plural"))));
                        out.println();
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
}
