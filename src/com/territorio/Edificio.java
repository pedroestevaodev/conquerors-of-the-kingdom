package com.territorio;

import com.utils.Utils;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.System.*;

public class Edificio {
    private String nome;
    private int custo;
    private int beneficio;
    private final Scanner scanner;

    public Edificio() {
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

    public void construirEdificios(Reino reino) {
        Utils ut = new Utils(scanner);

        out.println("\n***********  CONSTRUIR EDIFÍCIOS  ************");
        out.println("1. Quartel - 50 de ouro (Expande o limite de habitantes para 20)");
        out.println("2. Mina de Ouro - 80 de ouro (Garante uma certa quantia de ouro de tempos em tempos)");
        out.println("3. Torre de Defesa - 30 de ouro (Expande o limite de habitantes para 8)");
        out.println("0. Voltar");

        int opcao = ut.gerarPerguntaInt("Escolha um edifício para construir: ");

        switch (opcao) {
            case 1:
                out.println("Construindo Quartel...");
                reino.getEdificios().add(new Edificio("Quartel", 50, 20));
                break;
            case 2:
                out.println("Construindo Mina de Ouro...");
                reino.getEdificios().add(new Edificio("Mina de Ouro", 80, 0));
                iniciarGeracaoDeOuro(reino);
                break;
            case 3:
                out.println("Construindo Torre de Defesa...");
                reino.getEdificios().add(new Edificio("Torre de Defesa", 30, 8));
                break;
            case 0:
                out.println("Voltando ao menu principal...");
                break;
            default:
                out.println("Opção inválida. Por favor, escolha uma opção válida.");
        }
    }

    private void iniciarGeracaoDeOuro(Reino reino) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                reino.setRecursos(beneficio);
                out.println("Ouro gerado para o reino " + reino.getNome() + ": " + beneficio);
            }
        }, 1000, 1000);
    }
}
