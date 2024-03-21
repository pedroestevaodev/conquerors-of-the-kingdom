package com.territorio;

import com.exercito.ForcaDefesa;
import com.exercito.Tropa;
import com.utils.Utils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.*;

public class Reino {
    private String nome;
    private int recursos;
    private int populacao;
    private ForcaDefesa forcaDefesa;
    private List<Edificio> edificios;
    private List<Tropa> tropas;
    private List<Reino> aliados;
    private List<Reino> dominados;
    private final Scanner scanner;

    public Reino(String nome, int recursos, int populacao) {
        this.nome = nome;
        this.recursos = recursos;
        this.populacao = populacao;
        this.forcaDefesa = new ForcaDefesa(0, 0);
        this.edificios = new ArrayList<>();
        this.tropas = new ArrayList<>();
        this.aliados = new ArrayList<>();
        this.dominados = new ArrayList<>();
        this.scanner = new Scanner(in);
        atualizaForcaDefesaReino(this);
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

    public ForcaDefesa getForcaDefesa() {
        return forcaDefesa;
    }

    public void setForcaDefesa(ForcaDefesa forcaDefesa) {
        this.forcaDefesa = forcaDefesa;
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

        out.println("\nVocê escolheu interagir com o reino " + getNome() + ".");
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
                out.println("\nVoltando ao mapa...");
                break;
            default:
                out.println("\nOpção inválida. Por favor, escolha uma opção válida.");
        }
    }

    private void batalhar(Reino reino) {
        out.println("\nIniciando batalha com o reino " + getNome() + "...");

        boolean vitoria = Math.random() < 0.5;
        if (vitoria) {
            out.println("\nVocê venceu a batalha contra o reino " + getNome());
        } else {
            out.println("\nVocê perdeu a batalha contra o reino " + getNome());
        }
    }

    private void fazerAlianca() {
        out.println("\nFazendo aliança com o reino " + getNome() + "...");

        boolean alianca = Math.random() < 0.5;
        if (alianca) {
            out.println("\nO reino " + getNome() + " aceitou realizar uma aliança com seu reino!");
        } else {
            out.println("\nO reino " + getNome() + " não aceitou realizar uma aliança com seu reino!");
        }
    }

    private void coletarRecursos(@NotNull Reino reino) {
        out.println("\nColetando recursos do reino " + getNome() + "...");

        int recursos = getRecursos();
        reino.setRecursos(reino.getRecursos() + getRecursos());

        out.println("\nVocê coletou " + getRecursos() + " de recursos deste reino!");
    }

    public void atualizaForcaDefesaReino(@NotNull Reino reino) {
        int forcaTotal = 0;
        int defesaTotal = 0;

        for (Tropa tropa : reino.getTropas()) {
            forcaTotal += tropa.getForca();
            defesaTotal += tropa.getDefesa();
        }

        setForcaDefesa(new ForcaDefesa(forcaTotal, defesaTotal));
    }
}
