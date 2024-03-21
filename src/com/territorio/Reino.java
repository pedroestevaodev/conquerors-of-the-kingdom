package com.territorio;

import com.exercito.Tropa;
import com.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.*;

public class Reino {
    private String nome;
    private int recursos;
    private int populacao;
    private List<Edificio> edificios;
    private List<Tropa> tropas;
    private List<Reino> aliados;
    private List<Reino> dominados;
    private Scanner scanner;

    public Reino(String nome, int recursos, int populacao) {
        this.nome = nome;
        this.recursos = recursos;
        this.populacao = populacao;
        this.edificios = new ArrayList<>();
        this.tropas = new ArrayList<>();
        this.aliados = new ArrayList<>();
        this.dominados = new ArrayList<>();
        this.scanner = new Scanner(in);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getRecursos() {
        return recursos;
    }

    public void setRecursos(int recursos) {
        this.recursos = recursos;
    }

    public List<Edificio> getEdificios() {
        return edificios;
    }

    public void setEdificios(List<Edificio> edificios) {
        this.edificios = edificios;
    }

    public List<Tropa> getTropas() {
        return tropas;
    }

    public void setTropas(List<Tropa> tropas) {
        this.tropas = tropas;
    }

    public int getPopulacao() {
        return populacao;
    }

    public void setPopulacao(int populacao) {
        this.populacao = populacao;
    }

    public List<Reino> getAliados() {
        return aliados;
    }

    public void setAliados(List<Reino> aliados) {
        this.aliados = aliados;
    }

    public List<Reino> getDominados() {
        return dominados;
    }

    public void setDominados(List<Reino> dominados) {
        this.dominados = dominados;
    }

    public void interagirReino(Reino reino) {
        Utils ut = new Utils(scanner);

        out.println("Você escolheu interagir com o reino " + getNome() + ".");
        out.println("1. Batalhar");
        out.println("2. Fazer Aliança");
        out.println("3. Coletar Recursos");
        out.println("0. Voltar");

        int opcaoInteragir = ut.gerarPerguntaInt("Escolha uma ação: ");

        switch (opcaoInteragir) {
            case 1:
                batalhar(reino);
                break;
            case 2:
                fazerAlianca();
                break;
            case 3:
                coletarRecursos(reino);
                break;
            case 0:
                out.println("Voltando ao mapa...");
                break;
            default:
                out.println("Opção inválida. Por favor, escolha uma opção válida.");
        }
    }

    private void batalhar(Reino reino) {
        out.println("Iniciando batalha com o reino " + getNome() + "...");

        boolean vitoria = Math.random() < 0.5;
        if (vitoria) {
            out.println("Você venceu a batalha contra o reino " + getNome());
        } else {
            out.println("Você perdeu a batalha contra o reino " + getNome());
        }
    }

    private void fazerAlianca() {
        out.println("Fazendo aliança com o reino " + getNome() + "...");

        boolean alianca = Math.random() < 0.5;
        if (alianca) {
            out.println("O reino " + getNome() + " aceitou realizar uma aliança com seu reino!");
        } else {
            out.println("O reino " + getNome() + " não aceitou realizar uma aliança com seu reino!");
        }
    }

    private void coletarRecursos(Reino reino) {
        out.println("Coletando recursos do reino " + getNome() + "...");

        int recursos = getRecursos();
        reino.setRecursos(reino.getRecursos() + getRecursos());

        out.println("Você coletou " + getRecursos() + " de recursos deste reino!");
    }
}
