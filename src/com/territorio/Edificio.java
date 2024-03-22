package com.territorio;

import com.jogo.Mensagens;
import com.utils.Utils;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.System.in;
import static java.lang.System.out;

public class Edificio {
    private String nome;
    private int custo;
    private int beneficio;
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

    public void construirEdificios(Reino reino) {
        Utils ut = new Utils(scanner);

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

                    if (reino.getRecursos() >= quartel.getCusto()) {
                        ut.exibirTextoPausado("\nConstruindo Quartel...");
                        reino.getEdificios().add(quartel);
                        reino.setRecursos(reino.getRecursos() - quartel.getCusto());
                        reino.setPopulacao(reino.getPopulacao() + quartel.getBeneficio());
                        ut.exibirTextoPausado("\nQuartel construído com sucesso!\n");
                    } else {
                        ut.exibirTextoPausado("\nInfelizmente você não tem recursos para esta construção...\n");
                    }
                    break;
                case 2:
                    Edificio minaDeOuro = new Edificio("Mina de Ouro", 800, 0);

                    if (reino.getRecursos() >= minaDeOuro.getCusto()) {
                        ut.exibirTextoPausado("\nConstruindo Mina de Ouro...");
                        reino.getEdificios().add(minaDeOuro);
                        reino.setRecursos(reino.getRecursos() - minaDeOuro.getCusto());
                        reino.setPopulacao(reino.getPopulacao() + minaDeOuro.getBeneficio());
                        iniciarGeracaoDeOuro(reino);
                        ut.exibirTextoPausado("\nMina de Ouro construída com sucesso!\n");
                    } else {
                        ut.exibirTextoPausado("\nInfelizmente você não tem recursos para esta construção...\n");
                    }
                    break;
                case 3:
                    Edificio torreDeDefesa = new Edificio("Torre de Defesa", 300, 12);

                    if (reino.getRecursos() >= torreDeDefesa.getCusto()) {
                        ut.exibirTextoPausado("\nConstruindo Torre de Defesa...");
                        reino.getEdificios().add(torreDeDefesa);
                        reino.setRecursos(reino.getRecursos() - torreDeDefesa.getCusto());
                        reino.setPopulacao(reino.getPopulacao() + torreDeDefesa.getBeneficio());
                        ut.exibirTextoPausado("\nTorre de Defesa construída com sucesso!\n");
                    } else {
                        ut.exibirTextoPausado("\nInfelizmente você não tem recursos para esta construção...\n");
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

    private void iniciarGeracaoDeOuro(Reino reino) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                reino.setRecursos(reino.getRecursos() + 1);
            }
        }, 3500, 3500);
    }

    public void listarEdificios(Reino reino) {
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
