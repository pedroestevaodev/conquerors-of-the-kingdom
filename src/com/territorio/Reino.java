package com.territorio;

import com.exercito.ForcaDefesa;
import com.exercito.Tropa;
import com.jogo.Mensagens;
import com.player.Player;
import com.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import static java.lang.System.*;
import org.jetbrains.annotations.NotNull;

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

    public void interagirReino(@NotNull Player player) {
        Utils ut = new Utils(scanner);

        while (true) {
            if (player.getReino().getAliados().contains(this)) {
                out.println("\n************  " + getNome() + " [ALIADO]  ************");
            } else {
                out.println("\n****************  " + getNome() + "  *****************");
            }
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
                    fazerAlianca(player);
                    break;
                case 3:
                    coletarRecursos(player.getReino(), this);
                    break;
                case 0:
                    out.println("\nVoltando ao mapa...");
                    return;
                default:
                    out.println("\nOpção inválida. Por favor, escolha uma opção válida.");
            }
        }
    }

    private void batalhar(@NotNull Player player) {
        Random random = new Random();
        Utils ut = new Utils(scanner);
        Mensagens msg = new Mensagens();
        Tropa tropas = new Tropa();

        if (player.getReino().getDominados().contains(this)) {
            ut.exibirTextoPausado("\nVocê já derrotou este reino. Não se esqueça que a sede por destruição pode ser sua ruina...\n");
            return;
        }

        if (player.getReino().getAliados().contains(this)) {
            out.println();
            String perguntaGuerraAliado = ut.validarInfo("Este reino atualmente é seu aliado, tem certeza que deseja traí-lo? (sim ou nao)", "Por favor, digite 'sim' ou 'nao'.", valor -> valor.equalsIgnoreCase("sim") || valor.equalsIgnoreCase("nao"));

            if (perguntaGuerraAliado.equalsIgnoreCase("nao")) {
                return;
            } else {
                player.getReino().getAliados().remove(this);
            }
        }

        ut.limparPrompt();
        ut.exibirTextoPausado("Foi declarada guerra e não tem como voltar atrás...\n");

        if (player.getReino().getForcaDefesa().forca() == 0 && player.getReino().getForcaDefesa().defesa() == 0) {
            ut.exibirTextoPausado(msg.parametrosMensagem(msg.exibirMensagem("mensagem.batalha.none."+player.getGenero().toLowerCase()), player.getNome(), getNome()).replace("[BREAK]", "\n") + "\n");
            return;
        }

        ut.exibirTextoPausado(msg.parametrosMensagem(msg.exibirMensagem("mensagem.batalha."+player.getGenero().toLowerCase()), player.getNome(), player.getReino().getNome(), getNome()));
        out.println("\n");

        ut.exibirTextoPausado("[Reino " + player.getReino().getNome() + "]\n");
        ut.exibirTextoPausado("- Força: " + player.getReino().getForcaDefesa().forca() + "\n");
        ut.exibirTextoPausado("- Defesa: " + player.getReino().getForcaDefesa().defesa() + "\n");
        ut.exibirTextoPausado("- Recursos: " + player.getReino().getRecursos() + " de ouro\n");
        ut.exibirTextoPausado("= [Tropas]");
        tropas.listarTropas(player.getReino());
        for (Reino reino : player.getReino().getAliados()) {
            ut.exibirTextoPausado("= [Tropas aliadas do Reino " + reino.getNome() + "]");
            tropas.listarTropas(reino);
        }
        out.println("\n");

        ut.exibirTextoPausado("[Reino " + getNome() + "]\n");
        ut.exibirTextoPausado("- Força: " + getForcaDefesa().forca() + "\n");
        ut.exibirTextoPausado("- Defesa: " + getForcaDefesa().defesa() + "\n");
        ut.exibirTextoPausado("- Recursos: " + getRecursos() + " de ouro\n");
        ut.exibirTextoPausado("= [Tropas]");
        for (Reino reino : getAliados()) {
            ut.exibirTextoPausado("= [Tropas aliadas do Reino " + reino.getNome() + "]");
            tropas.listarTropas(reino);
        }
        tropas.listarTropas(this);

        ut.exibirTextoPausado("\nO campo de batalha foi preparado. A tensão reina soberana...");
        ut.exibirTextoPausado("\nQuem iniciará o combate? A sorte decidirá...");

        boolean iniciador = Math.random() < 0.5;
        if (iniciador) {
            ut.exibirTextoPausado("\nSeu exército decidiu tomar a iniciativa. A guerra começou...");
        } else {
            ut.exibirTextoPausado("\nO exército inimigo decidiu tomar a iniciativa. A guerra começou...");
        }

        int forcaJogador = player.getReino().getForcaDefesa().forca();
        int forcaInimigo = player.getReino().getForcaDefesa().forca();
        int defesaJogador = getForcaDefesa().defesa();
        int defesaInimigo = getForcaDefesa().defesa();

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

        int forcaAtaquePlayer = player.getReino().getForcaDefesa().forca() - getForcaDefesa().defesa();
        int forcaAtaqueInimigo = getForcaDefesa().forca() - player.getReino().getForcaDefesa().defesa();

        if (forcaAtaquePlayer < 0) forcaAtaquePlayer = 0;
        if (forcaAtaqueInimigo < 0) forcaAtaqueInimigo = 0;

        int resultadoBatalha = random.nextInt(forcaAtaquePlayer + forcaAtaqueInimigo);
        if (resultadoBatalha < forcaAtaquePlayer) {
            ut.exibirTextoPausado("\nSeu reino foi vitorioso!");
            player.getReino().getDominados().add(this);
            coletarRecursos(player.getReino(), this);
            ut.exibirTextoPausado("\nO reino " + getNome() + " agora está na lista de reinos derrotados. Agora você poderá coletar recursos dele quando quiser...\n");
        } else if (resultadoBatalha < forcaAtaquePlayer + forcaAtaqueInimigo) {
            ut.exibirTextoPausado("\nSeu reino sucumbiu ao inimigo!");
            getDominados().add(player.getReino());
            coletarRecursos(this, player.getReino());
        } else {
            ut.exibirTextoPausado("\nA batalha terminou em empate! Ambos os reinos possuem a mesma força.");
        }
    }

    private void fazerAlianca(@NotNull Player player) {
        Utils ut = new Utils(scanner);
        Mensagens msg = new Mensagens();

        if (player.getReino().getDominados().contains(this)) {
            ut.exibirTextoPausado("\nO reino " + getNome() + " foi derrotado por você. Ao realizar uma aliança com ele, você não poderá mais coletar seus recursos, mas poderá contar com a força de seu exército ao seu lado.");
            String perguntaAliancaDominado = ut.validarInfo("\nDeseja formar uma aliança com este reino? (sim ou nao)", "Por favor, digite 'sim' ou 'nao'.", valor -> valor.equalsIgnoreCase("sim") || valor.equalsIgnoreCase("nao"));

            if (perguntaAliancaDominado.equalsIgnoreCase("sim")) {
                player.getReino().getDominados().remove(this);
                player.getReino().getAliados().add(this);
                player.getReino().atualizaForcaDefesaReino();
                getAliados().add(player.getReino());
                atualizaForcaDefesaReino();
            } else {
                ut.exibirTextoPausado("\nSeu reino desistiu de fazer uma aliança com o reino " + getNome() + "! Mesmo sabendo que isso poderia enfim trazer paz para ele e seus órfãos...\n");
            }

            return;
        }

        ut.exibirTextoPausado("\nSeu reino enviou uma proposta de aliança para o reino de " + getNome() + "...\n");

        if (player.getReino().getTropas().isEmpty() && player.getReino().getEdificios().isEmpty()) {
            ut.exibirTextoPausado(msg.parametrosMensagem(msg.exibirMensagem("mensagem.alianca.none."+player.getGenero()), this.getNome()) + "\n");
            return;
        }

        boolean alianca = Math.random() < 0.5;
        if (alianca) {
            ut.exibirTextoPausado("O reino " + getNome() + " aceitou realizar uma aliança com seu reino!\n");
            player.getReino().getDominados().remove(this);
            player.getReino().getAliados().add(this);
            player.getReino().atualizaForcaDefesaReino();
            getAliados().add(player.getReino());
            atualizaForcaDefesaReino();
        } else {
            ut.exibirTextoPausado("O reino " + getNome() + " recusou a proposta de aliança com seu reino! Muitas vezes é melhor continuar sozinho...\n");
        }
    }

    private void coletarRecursos(@NotNull Reino reinoColeta, @NotNull Reino reinoPerde) {
        Utils ut = new Utils(scanner);

        if (!reinoColeta.getDominados().contains(reinoPerde)) {
            ut.exibirTextoPausado("\nVocê não pode coletar recursos de um reino que não tenha sido dominado por você...\n");
            return;
        }

        if (reinoColeta.getAliados().contains(reinoPerde)) {
            ut.exibirTextoPausado("\nEste reino é seu aliado, você não pode coletar seus recursos! Nunca se esqueça que a ganância é a ruina do homem...\n");
            return;
        }

        ut.exibirTextoPausado("\nAos vencedores os espólios...");

        if (reinoPerde.getRecursos() == 0) {
            ut.exibirTextoPausado("\nMas infelizmente o reino de " + reinoPerde.getNome() + " não possui recursos para serem coletados...");

            int possuiMinasDeOuro = 0;
            for (Edificio edificio : reinoPerde.getEdificios()) {
                if (edificio.getNome().equalsIgnoreCase("mina de ouro")) {
                    possuiMinasDeOuro++;
                }
            }

            if (possuiMinasDeOuro == 0) {
                ut.exibirTextoPausado("\nEles não possuem minas de ouro para poderem gerar riqueza. Como ato de misericórdia você pode formar uma aliança com eles para que suas tropas lutem ao seu lado...\n");
            } else {
                ut.exibirTextoPausado("\nMas eles possuem minas de ouro gerando ouro, volte depois para poder coletar mais recursos...\n");
            }

            return;
        }

        ut.exibirTextoPausado("\nO reino " + reinoColeta.getNome() + " coletou os recursos do reino de " + reinoPerde.getNome() + "...");
        int recursosColetados = reinoPerde.getRecursos();
        reinoColeta.setRecursos(reinoColeta.getRecursos() + recursosColetados);
        reinoPerde.setRecursos(0);
        ut.exibirTextoPausado("\n" + reinoColeta.getNome() + " recebeu " + recursosColetados + " de ouro...\n");
    }

    public void atualizaForcaDefesaReino() {
        int forcaTotal = 0;
        int defesaTotal = 0;

        for (Tropa tropa : getTropas()) {
            forcaTotal += tropa.getForca();
            defesaTotal += tropa.getDefesa();
        }

        for (Reino reino : getAliados()) {
            for (Tropa tropa : reino.getTropas()) {
                forcaTotal += tropa.getForca();
                defesaTotal += tropa.getDefesa();
            }
        }

        setForcaDefesa(new ForcaDefesa(forcaTotal, defesaTotal));
    }

    public void visualizadorInformacoes() {
        out.print("  Ouro: " + getRecursos());
        out.print(" | Tropas: " + getTropas().size() + "/" + getPopulacao());
        out.print(" | Edifícios: " + getEdificios().size() + "\n");
        out.println("**********************************************");
    }
}
