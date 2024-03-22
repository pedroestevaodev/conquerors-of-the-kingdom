package com.territorio;

import com.exercito.ForcaDefesa;
import com.exercito.Tropa;
import com.jogo.Mensagens;
import com.player.Player;
import com.utils.Utils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
        atualizaForcaDefesaReino();
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

    public void interagirReino(Player player) {
        Utils ut = new Utils(scanner);

        out.println("\n****************  " + getNome() + "  *****************");
        out.println("1. Batalhar");
        out.println("2. Fazer Aliança");
        out.println("3. Coletar Recursos");
        out.println("0. Voltar");

        int opcaoInteragir = ut.gerarPerguntaInt("Escolha uma ação: ");

        switch (opcaoInteragir) {
            case 1:
                batalhar(player);
                break;
            case 2:
                fazerAlianca();
                break;
            case 3:
                coletarRecursos(player);
                break;
            case 0:
                out.println("\nVoltando ao mapa...");
                break;
            default:
                out.println("\nOpção inválida. Por favor, escolha uma opção válida.");
        }
    }

    private void batalhar(Player player) {
        Random random = new Random();
        Utils ut = new Utils(scanner);
        Mensagens msg = new Mensagens();
        Tropa tropas = new Tropa();

        ut.limparPrompt();
        ut.exibirTextoPausado("Foi declarada guerra e não tem como voltar atrás...\n");

        if (player.getReino().getForcaDefesa().getForca() == 0 && player.getReino().getForcaDefesa().getDefesa() == 0) {
            ut.exibirTextoPausado(msg.parametrosMensagem(msg.exibirMensagem("mensagem.batalha.none."+player.getGenero().toLowerCase()), player.getNome(), getNome()).replace("[BREAK]", "\n") + "\n");
            return;
        }

        ut.exibirTextoPausado(msg.parametrosMensagem(msg.exibirMensagem("mensagem.batalha."+player.getGenero().toLowerCase()), player.getNome(), player.getReino().getNome(), getNome()));
        out.println("\n");

        ut.exibirTextoPausado("[Reino " + player.getReino().getNome() + "]\n");
        ut.exibirTextoPausado("- Força: " + player.getReino().getForcaDefesa().getForca() + "\n");
        ut.exibirTextoPausado("- Defesa: " + player.getReino().getForcaDefesa().getDefesa() + "\n");
        ut.exibirTextoPausado("- Recursos: " + player.getReino().getRecursos() + " de ouro\n");
        ut.exibirTextoPausado("= [Tropas]");
        tropas.listarTropas(player.getReino());
        out.println("\n");

        ut.exibirTextoPausado("[Reino " + getNome() + "]\n");
        ut.exibirTextoPausado("- Força: " + getForcaDefesa().getForca() + "\n");
        ut.exibirTextoPausado("- Defesa: " + getForcaDefesa().getDefesa() + "\n");
        ut.exibirTextoPausado("- Recursos: " + getRecursos() + " de ouro\n");
        ut.exibirTextoPausado("= [Tropas]");
        tropas.listarTropas(this);

        ut.exibirTextoPausado("\nO campo de batalha foi preparado. A tensão reina soberana...");
        ut.exibirTextoPausado("\nQuem iniciará o combate? A sorte decidirá...");

        boolean iniciador = Math.random() < 0.5;
        if (iniciador) {
            ut.exibirTextoPausado("\nSeu exército decidiu tomar a iniciativa. A guerra começou...");
        } else {
            ut.exibirTextoPausado("\nO exército inimigo decidiu tomar a iniciativa. A guerra começou...");
        }

        int forcaJogador = player.getReino().getForcaDefesa().getForca();
        int forcaInimigo = player.getReino().getForcaDefesa().getForca();
        int defesaJogador = getForcaDefesa().getDefesa();
        int defesaInimigo = getForcaDefesa().getDefesa();

        if (iniciador) {
            if (forcaJogador > defesaInimigo) {
                ut.exibirTextoPausado("\nSeu exército avançou fervoroso e conseguiu quebrar a defesa inimiga!");
            } else {
                ut.exibirTextoPausado("\nSeu exército avançou fervoroso, mas a defesa inimiga era mais poderosa!");
            }
        } else {
            if (forcaInimigo > defesaJogador) {
                ut.exibirTextoPausado("\nO exército inimigo avançou fervoroso e conseguiu quebrar sua defesa!");
            } else {
                ut.exibirTextoPausado("\nO exército inimigo avançou fervoroso, mas sua defesa era mais poderosa!");
            }
        }

        ut.exibirTextoPausado("\nA batalha é ferrenha, mas o destino já estava escrito...");

        int forcaAtaquePlayer = player.getReino().getForcaDefesa().getForca() - getForcaDefesa().getDefesa();
        int forcaAtaqueInimigo = getForcaDefesa().getForca() - player.getReino().getForcaDefesa().getDefesa();

        if (forcaAtaquePlayer < 0) forcaAtaquePlayer = 0;
        if (forcaAtaqueInimigo < 0) forcaAtaqueInimigo = 0;

        int resultadoBatalha = random.nextInt(forcaAtaquePlayer + forcaAtaqueInimigo);
        if (resultadoBatalha < forcaAtaquePlayer) {
            ut.exibirTextoPausado("\nSeu reino foi vitorioso!");
            ut.exibirTextoPausado("\nAos vencedores os espólios...");
            ut.exibirTextoPausado("\nSeu reino coletou os recursos do reino de " + getNome() + "...");
            ut.exibirTextoPausado("\nVocê recebeu " + getRecursos() + " de ouro...");
            player.getReino().getDominados().add(this);
            ut.exibirTextoPausado("\nO reino " + getNome() + " agora está na lista de reinos derrotados. Agora você poderá coletar recursos dele quando quiser...");
        } else if (resultadoBatalha < forcaAtaquePlayer + forcaAtaqueInimigo) {
            ut.exibirTextoPausado("\nSeu reino sucumbiu ao inimigo!");
            ut.exibirTextoPausado("\nAos vencedores os espólios...");
            int recursosPerdidos = (int) (player.getReino().getRecursos() * 0.75);
            player.getReino().setRecursos(player.getReino().getRecursos() - recursosPerdidos);
            ut.exibirTextoPausado("\nSeu reino perdeu " + recursosPerdidos + " de ouro para o reino de " + getNome() + "...");
        } else {
            ut.exibirTextoPausado("\nA batalha terminou em empate! Ambos os reinos possuem a mesma força.");
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

    private void coletarRecursos(@NotNull Player player) {
        out.println("\nColetando recursos do reino " + getNome() + "...");

        int recursos = getRecursos();
        player.getReino().setRecursos(player.getReino().getRecursos() + getRecursos());

        out.println("\nVocê coletou " + getRecursos() + " de recursos deste reino!");
    }

    public void atualizaForcaDefesaReino() {
        int forcaTotal = 0;
        int defesaTotal = 0;

        for (Tropa tropa : getTropas()) {
            forcaTotal += tropa.getForca();
            defesaTotal += tropa.getDefesa();
        }

        setForcaDefesa(new ForcaDefesa(forcaTotal, defesaTotal));
    }
}
