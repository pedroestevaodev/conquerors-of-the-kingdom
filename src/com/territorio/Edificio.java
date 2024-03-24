package com.territorio;

import com.jogo.Mensagens;
import com.utils.Utils;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import static java.lang.System.*;
import org.jetbrains.annotations.NotNull;

public class Edificio {
    private final String nome;
    private final int custo;
    private final int beneficio;
    private final Scanner scanner;

    public Edificio() {
        this.nome = "Sem nome";
        this.custo = 0;
        this.beneficio = 0;
        this.scanner = new Scanner(in);
    }

    public Edificio(String nome, int custo, int beneficio) {
        this.nome = nome;
        this.custo = custo;
        this.beneficio = beneficio;
        this.scanner = new Scanner(in);
    }

    public String getNome() {
        return nome;
    }

    public int getCusto() {
        return custo;
    }

    public int getBeneficio() {
        return beneficio;
    }

    public void construirEdificios(@NotNull Reino reino) {
        Utils ut = new Utils(scanner);
        Mensagens msg = new Mensagens();

        while (true) {
            out.println("\n***********  CONSTRUIR EDIFÍCIOS  ************");
            reino.visualizadorInformacoes();
            out.println("1. Quartel - 500 de ouro (Expande o limite de habitantes para +20)");
            out.println("2. Mina de Ouro - 800 de ouro (Garante uma certa quantia de ouro de tempos em tempos)");
            out.println("3. Torre de Defesa - 300 de ouro (Expande o limite de habitantes para +12)");
            out.println("0. Voltar");

            int opcao = ut.gerarPerguntaInt("Escolha um edifício para construir: ");

            switch (opcao) {
                case 1:
                    Edificio quartel = new Edificio("Quartel", 500, 20);

                    int perguntaQtdQuartel = Integer.parseInt(ut.validarInfo("\nDeseja construir quantos quartéis?", "Por favor, informe apenas valores inteiros positivos!", valor -> {
                        try {
                            int quantidade = Integer.parseInt(valor);
                            return quantidade >= 0;
                        } catch (NumberFormatException e) {
                            return false;
                        }
                    }));

                    if (perguntaQtdQuartel == 0) {
                        ut.exibirTextoPausado("\nVocê escolheu não construir nenhum quartel...\n");
                        break;
                    }

                    if (!ut.validarCompra(reino, perguntaQtdQuartel, quartel)) {
                        ut.exibirTextoPausado("\nInfelizmente você não tem recursos para construir essa quantidade de quartéis...\n");
                        break;
                    }

                    ut.exibirTextoPausado("\n" + (msg.exibirMensagem("mensagem.construcao.quartel." + (perguntaQtdQuartel == 1 ? "singular" : "plural"))));

                    for (int i = 0; i < perguntaQtdQuartel; i++) {
                        if (reino.getRecursos() >= quartel.getCusto()) {
                            reino.getEdificios().add(quartel);
                            reino.setRecursos(reino.getRecursos() - quartel.getCusto());
                            reino.setPopulacao(reino.getPopulacao() + quartel.getBeneficio());
                        } else {
                            ut.exibirTextoPausado("\nInfelizmente você não tem recursos para esta construção...\n");
                            break;
                        }
                    }

                    ut.exibirTextoPausado("\n" + (msg.exibirMensagem("mensagem.construcao.quartel.sucesso." + (perguntaQtdQuartel == 1 ? "singular" : "plural"))) + "\n");

                    break;
                case 2:
                    Edificio minaDeOuro = new Edificio("Mina de Ouro", 800, 0);

                    int perguntaQtdMinaDeOuro = Integer.parseInt(ut.validarInfo("\nDeseja construir quantas minas de ouro?", "Por favor, informe apenas valores inteiros positivos!", valor -> {
                        try {
                            int quantidade = Integer.parseInt(valor);
                            return quantidade >= 0;
                        } catch (NumberFormatException e) {
                            return false;
                        }
                    }));

                    if (perguntaQtdMinaDeOuro == 0) {
                        ut.exibirTextoPausado("\nVocê escolheu não construir nenhuma mina de ouro...\n");
                        break;
                    }

                    if (!ut.validarCompra(reino, perguntaQtdMinaDeOuro, minaDeOuro)) {
                        ut.exibirTextoPausado("\nInfelizmente você não tem recursos para construir essa quantidade de minas de ouro...\n");
                        break;
                    }

                    ut.exibirTextoPausado("\n" + (msg.exibirMensagem("mensagem.construcao.minadeouro." + (perguntaQtdMinaDeOuro == 1 ? "singular" : "plural"))));

                    for (int i = 0; i < perguntaQtdMinaDeOuro; i++) {
                        if (reino.getRecursos() >= minaDeOuro.getCusto()) {
                            reino.getEdificios().add(minaDeOuro);
                            reino.setRecursos(reino.getRecursos() - minaDeOuro.getCusto());
                            reino.setPopulacao(reino.getPopulacao() + minaDeOuro.getBeneficio());
                            iniciarGeracaoDeOuro(reino);
                        } else {
                            ut.exibirTextoPausado("\nInfelizmente você não tem recursos para esta construção...\n");
                        }
                    }

                    ut.exibirTextoPausado("\n" + (msg.exibirMensagem("mensagem.construcao.minadeouro.sucesso." + (perguntaQtdMinaDeOuro == 1 ? "singular" : "plural"))) + "\n");

                    break;
                case 3:
                    Edificio torreDeDefesa = new Edificio("Torre de Defesa", 300, 12);

                    int perguntaQtdTorresDeDefesa = Integer.parseInt(ut.validarInfo("\nDeseja construir quantas torres de defesa?", "Por favor, informe apenas valores inteiros positivos!", valor -> {
                        try {
                            int quantidade = Integer.parseInt(valor);
                            return quantidade >= 0;
                        } catch (NumberFormatException e) {
                            return false;
                        }
                    }));

                    if (perguntaQtdTorresDeDefesa == 0) {
                        ut.exibirTextoPausado("\nVocê escolheu não construir nenhuma torre de defesa...\n");
                        break;
                    }

                    if (!ut.validarCompra(reino, perguntaQtdTorresDeDefesa, torreDeDefesa)) {
                        ut.exibirTextoPausado("\nInfelizmente você não tem recursos para construir essa quantidade de torres de defesa...\n");
                        break;
                    }

                    ut.exibirTextoPausado("\n" + (msg.exibirMensagem("mensagem.construcao.torrededefesa." + (perguntaQtdTorresDeDefesa == 1 ? "singular" : "plural"))));

                    for (int i = 0; i < perguntaQtdTorresDeDefesa; i++) {
                        if (reino.getRecursos() >= torreDeDefesa.getCusto()) {
                            ut.exibirTextoPausado("\nConstruindo Torre de Defesa...");
                            reino.getEdificios().add(torreDeDefesa);
                            reino.setRecursos(reino.getRecursos() - torreDeDefesa.getCusto());
                            reino.setPopulacao(reino.getPopulacao() + torreDeDefesa.getBeneficio());
                            ut.exibirTextoPausado("\nTorre de Defesa construída com sucesso!\n");
                        } else {
                            ut.exibirTextoPausado("\nInfelizmente você não tem recursos para esta construção...\n");
                        }
                    }

                    ut.exibirTextoPausado("\n" + (msg.exibirMensagem("mensagem.construcao.torrededefesa.sucesso." + (perguntaQtdTorresDeDefesa == 1 ? "singular" : "plural"))) + "\n");

                    break;
                case 0:
                    ut.exibirTextoPausado("\nVoltando ao menu...\n");
                    return;
                default:
                    out.println("\nOpção inválida. Por favor, escolha uma opção válida.");
            }
        }
    }

    private void iniciarGeracaoDeOuro(@NotNull Reino reino) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                reino.setRecursos(reino.getRecursos() + 1);
            }
        }, 3500, 3500);
    }

    public void listarEdificios(@NotNull Reino reino) {
        Utils ut = new Utils(scanner);
        Mensagens msg = new Mensagens();

        int quarteis = 0;
        int minasDeOuro = 0;
        int torresDeDefesa = 0;

        for (Edificio edificio : reino.getEdificios()) {
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
}
