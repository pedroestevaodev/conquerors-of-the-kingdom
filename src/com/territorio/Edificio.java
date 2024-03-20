package com.territorio;

import com.utils.Utils;

import java.util.Scanner;

import static java.lang.System.*;

public class Edificio {
    private String nome;
    private int custo;
    private final Scanner scanner;

    public Edificio(String nome, int custo) {
        this.nome = nome;
        this.custo = custo;
        this.scanner = new Scanner(in);
    }

    public String getNome() {
        return nome;
    }

    public int getCusto() {
        return custo;
    }

    private void construirEdificios() {
        Utils ut = new Utils(scanner);

        out.println("Edifícios disponíveis para construção:");
        out.println("1. Quartel");
        out.println("2. Casa");
        out.println("3. Mina de Ouro");
        out.println("4. Torre de Defesa");
        out.println("0. Voltar");

        int opcao = ut.gerarPerguntaInt("Escolha um edifício para construir: ");

        switch (opcao) {
            case 1:
                construirQuartel();
                break;
            case 2:
                construirCasa();
                break;
            case 3:
                construirMinaDeOuro();
                break;
            case 4:
                construirTorreDeDefesa();
                break;
            case 0:
                out.println("Voltando ao menu principal...");
                break;
            default:
                out.println("Opção inválida. Por favor, escolha uma opção válida.");
        }
    }

    private void construirQuartel() {
        out.println("Construindo Quartel...");
    }

    private void construirCasa() {
        out.println("Construindo Casa...");
    }

    private void construirMinaDeOuro() {
        out.println("Construindo Mina de Ouro...");
    }

    private void construirTorreDeDefesa() {
        out.println("Construindo Torre de Defesa...");
    }
}
