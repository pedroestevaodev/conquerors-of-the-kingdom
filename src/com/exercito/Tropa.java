package com.exercito;

import com.jogo.Mensagens;
import com.territorio.Reino;
import com.utils.Utils;
import java.util.Scanner;
import static java.lang.System.*;

public class Tropa {
    private final String nome;
    private final int custo;
    private final int custoTreinamento;
    private int forca;
    private int defesa;
    private final Scanner scanner;

    public Tropa() {
        this.nome = "Sem nome";
        this.custo = 0;
        this.custoTreinamento = 0;
        this.forca = 0;
        this.defesa = 0;
        this.scanner = new Scanner(in);
    }

    public Tropa(String nome, int custo, int custoTreinamento, int forca, int defesa) {
        this.nome = nome;
        this.custo = custo;
        this.custoTreinamento = custoTreinamento;
        this.forca = forca;
        this.defesa = defesa;
        this.scanner = new Scanner(in);
    }

    public String getNome() {
        return nome;
    }

    public int getCusto() {
        return custo;
    }

    public int getCustoTreinamento() {
        return custoTreinamento;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public void contratar(Reino reino) {
        Utils ut = new Utils(scanner);
        Mensagens msg = new Mensagens();

        while (true) {
            out.println("\n*************  EXPANDIR TROPAS  **************");
            reino.visualizadorInformacoes();
            out.println("1. Arqueiro - 60 de ouro (Força: 20 - Defesa: 15)");
            out.println("2. Cavaleiro - 100 de ouro (Força: 50 - Defesa: 40)");
            out.println("3. Lanceiro - 40 de ouro (Força: 10 - Defesa: 7)");
            out.println("0. Voltar");

            int opcao = ut.gerarPerguntaInt("Escolha uma classe para contratar: ");

            switch (opcao) {
                case 1:
                    Tropa arqueiro = new Tropa("Arqueiro", 60, 100, 20, 15);

                    int perguntaQtdArqueiros = ut.validarInfoInt("\nDeseja contratar quantos arqueiros?", "Por favor, informe apenas valores inteiros positivos!", valor -> Integer.parseInt(String.valueOf(valor)) >= 0);

                    if (perguntaQtdArqueiros == 0) {
                        ut.exibirTextoPausado("\nVocê escolheu não contratar nenhum arqueiro...\n");
                        break;
                    }

                    if (!ut.validarCompra(reino, perguntaQtdArqueiros, arqueiro)) {
                        ut.exibirTextoPausado("\nInfelizmente você não tem recursos para contratar essa quantidade de arqueiros...\n");
                        break;
                    }

                    if (reino.getPopulacao() == reino.getTropas().size()) {
                        ut.exibirTextoPausado("\nSeu reino atingiu o limite máximo de habitantes. Que tal construir alguns edifícios?\n");
                        break;
                    }

                    if ((perguntaQtdArqueiros + reino.getTropas().size()) > reino.getPopulacao()) {
                        ut.exibirTextoPausado("\nInfelizmente você não pode contratar essa quantidade de arqueiros...\n");
                        break;
                    }

                    ut.exibirTextoPausado("\n" + (msg.exibirMensagem("mensagem.contratar.arqueiro." + (perguntaQtdArqueiros == 1 ? "singular" : "plural"))));

                    for (int i = 0; i < perguntaQtdArqueiros; i++) {
                        if (reino.getRecursos() >= arqueiro.getCusto()) {
                            reino.getTropas().add(arqueiro);
                            reino.setRecursos(reino.getRecursos() - arqueiro.getCusto());
                            reino.atualizaForcaDefesaReino();
                        } else {
                            ut.exibirTextoPausado("\nInfelizmente você não tem recursos para esta contratação...\n");
                            break;
                        }
                    }

                    ut.exibirTextoPausado("\n" + (msg.exibirMensagem("mensagem.contratar.arqueiro.sucesso." + (perguntaQtdArqueiros == 1 ? "singular" : "plural"))) + "\n");

                    break;
                case 2:
                    Tropa cavaleiro = new Tropa("Cavaleiro", 100, 350, 50, 40);

                    int perguntaQtdCavaleiros = ut.validarInfoInt("\nDeseja contratar quantos cavaleiros?", "Por favor, informe apenas valores inteiros positivos!", valor -> Integer.parseInt(String.valueOf(valor)) >= 0);

                    if (perguntaQtdCavaleiros == 0) {
                        ut.exibirTextoPausado("\nVocê escolheu não contratar nenhum cavaleiro...\n");
                        break;
                    }

                    if (!ut.validarCompra(reino, perguntaQtdCavaleiros, cavaleiro)) {
                        ut.exibirTextoPausado("\nInfelizmente você não tem recursos para contratar essa quantidade de cavaleiros...\n");
                        break;
                    }

                    if (reino.getPopulacao() == reino.getTropas().size()) {
                        ut.exibirTextoPausado("\nSeu reino atingiu o limite máximo de habitantes. Que tal construir alguns edifícios?\n");
                        break;
                    }

                    if ((perguntaQtdCavaleiros + reino.getTropas().size()) > reino.getPopulacao()) {
                        ut.exibirTextoPausado("\nInfelizmente você não pode contratar essa quantidade de cavaleiros...\n");
                        break;
                    }

                    ut.exibirTextoPausado("\n"+msg.exibirMensagem("mensagem.contratar.cavaleiro." + (perguntaQtdCavaleiros == 1 ? "singular" : "plural")));

                    for (int i = 0; i < perguntaQtdCavaleiros; i++) {
                        if (reino.getRecursos() >= cavaleiro.getCusto()) {
                            reino.getTropas().add(cavaleiro);
                            reino.setRecursos(reino.getRecursos() - cavaleiro.getCusto());
                            reino.atualizaForcaDefesaReino();
                        } else {
                            ut.exibirTextoPausado("\nInfelizmente você não tem recursos para esta contratação...\n");
                            break;
                        }
                    }

                    ut.exibirTextoPausado("\n" + msg.exibirMensagem("mensagem.contratar.cavaleiro.sucesso." + (perguntaQtdCavaleiros == 1 ? "singular" : "plural")) + "\n");

                    break;
                case 3:
                    Tropa lanceiro = new Tropa("Lanceiro", 40, 200, 10, 7);

                    int perguntaQtdLanceiros = ut.validarInfoInt("\nDeseja contratar quantos lanceiros?", "Por favor, informe apenas valores inteiros positivos!", valor -> Integer.parseInt(String.valueOf(valor)) >= 0);

                    if (perguntaQtdLanceiros == 0) {
                        ut.exibirTextoPausado("\nVocê escolheu não contratar nenhum lanceiro...\n");
                        break;
                    }

                    if (!ut.validarCompra(reino, perguntaQtdLanceiros, lanceiro)) {
                        ut.exibirTextoPausado("\nInfelizmente você não tem recursos para contratar essa quantidade de lanceiros...\n");
                        break;
                    }

                    if (reino.getPopulacao() == reino.getTropas().size()) {
                        ut.exibirTextoPausado("\nSeu reino atingiu o limite máximo de habitantes. Que tal construir alguns edifícios?\n");
                        break;
                    }

                    if ((perguntaQtdLanceiros + reino.getTropas().size()) > reino.getPopulacao()) {
                        ut.exibirTextoPausado("\nInfelizmente você não pode contratar essa quantidade de lanceiros...\n");
                        break;
                    }

                    ut.exibirTextoPausado("\n"+msg.exibirMensagem("mensagem.contratar.lanceiro." + (perguntaQtdLanceiros == 1 ? "singular" : "plural")));

                    for (int i = 0; i < perguntaQtdLanceiros; i++) {
                        if (reino.getRecursos() >= lanceiro.getCusto()) {
                            reino.getTropas().add(lanceiro);
                            reino.setRecursos(reino.getRecursos() - lanceiro.getCusto());
                            reino.atualizaForcaDefesaReino();
                        } else {
                            ut.exibirTextoPausado("\nInfelizmente você não tem recursos para esta contratação...\n");
                            break;
                        }
                    }

                    ut.exibirTextoPausado("\n" + msg.exibirMensagem("mensagem.contratar.lanceiro.sucesso." + (perguntaQtdLanceiros == 1 ? "singular" : "plural")) + "\n");

                    break;
                case 0:
                    ut.exibirTextoPausado("\nVoltando ao menu...\n");
                    return;
                default:
                    out.println("\nOpção inválida. Por favor, escolha uma opção válida.");
            }
        }
    }

    public void treinar(Reino reino) {
        Utils ut = new Utils(scanner);

        if (reino.getTropas().isEmpty()) {
            ut.exibirTextoPausado("\nSeu reino não possui tropas para poder treinar. Que tal contratar alguns guerreiros?\n");
            return;
        }

        int arqueiros = 0;
        int cavaleiros = 0;
        int lanceiros = 0;

        for (Tropa tropa : reino.getTropas()) {
            if (tropa.getNome().equalsIgnoreCase("Arqueiro")) {
                arqueiros++;
            } else if (tropa.getNome().equalsIgnoreCase("Cavaleiro")) {
                cavaleiros++;
            } else {
                lanceiros++;
            }
        }

        while (true) {
            out.println("\n**************  TREINAR TROPAS  **************");
            reino.visualizadorInformacoes();
            out.println("[O treinamento aumentará a força em +32.8% e a defesa em +75%]");
            out.println("1. Arqueiros - 100 de ouro");
            out.println("2. Cavaleiros - 350 de ouro");
            out.println("3. Lanceiros - 200 de ouro");
            out.println("4. Treinar todas as tropas - 800 de ouro");
            out.println("0. Voltar");

            int opcao = ut.gerarPerguntaInt("Escolha uma classe para treinar: ");

            switch (opcao) {
                case 1:
                    Tropa arqueiro = new Tropa("Arqueiro", 60, 100, 20, 15);

                    if (arqueiros == 0) {
                        ut.exibirTextoPausado("\nSeu reino não possui arqueiros para poder treinar. Que tal contratar alguns?\n");
                        return;
                    }

                    if (reino.getRecursos() >= arqueiro.getCustoTreinamento()) {
                        ut.exibirTextoPausado("\nIniciando treinamento dos Arqueiros...");
                        for (Tropa tropa : reino.getTropas()) {
                            if (tropa.getNome().equalsIgnoreCase("Arqueiro")) {
                                tropa.setForca((int) (tropa.getForca() * 1.328));
                                tropa.setDefesa((int) (tropa.getDefesa() * 1.50));
                            }
                        }
                        reino.setRecursos(reino.getRecursos() - arqueiro.getCustoTreinamento());
                        reino.atualizaForcaDefesaReino();
                        ut.exibirTextoPausado("\nArqueiros treinaram com sucesso!\n");
                    } else {
                        ut.exibirTextoPausado("\nInfelizmente você não tem recursos para treinar essa classe...\n");
                    }
                    break;
                case 2:
                    Tropa cavaleiro = new Tropa("Cavaleiro", 100, 350, 50, 40);

                    if (cavaleiros == 0) {
                        ut.exibirTextoPausado("\nSeu reino não possui cavaleiros para poder treinar. Que tal contratar alguns?\n");
                        return;
                    }

                    if (reino.getRecursos() >= cavaleiro.getCustoTreinamento()) {
                        ut.exibirTextoPausado("\nIniciando treinamento dos Cavaleiros...");
                        for (Tropa tropa : reino.getTropas()) {
                            if (tropa.getNome().equalsIgnoreCase("Cavaleiro")) {
                                tropa.setForca((int) (tropa.getForca() * 1.328));
                                tropa.setDefesa((int) (tropa.getDefesa() * 1.50));
                            }
                        }
                        reino.setRecursos(reino.getRecursos() - cavaleiro.getCustoTreinamento());
                        reino.atualizaForcaDefesaReino();
                        ut.exibirTextoPausado("\nCavaleiros treinaram com sucesso!\n");
                    } else {
                        ut.exibirTextoPausado("\nInfelizmente você não tem recursos para treinar essa classe...\n");
                    }
                    break;
                case 3:
                    Tropa lanceiro = new Tropa("Lanceiro", 40, 200, 10, 7);

                    if (lanceiros == 0) {
                        ut.exibirTextoPausado("\nSeu reino não possui lanceiros para poder treinar. Que tal contratar alguns?\n");
                        return;
                    }

                    if (reino.getRecursos() >= lanceiro.getCustoTreinamento()) {
                        ut.exibirTextoPausado("\nIniciando treinamento dos Lanceiros...");
                        for (Tropa tropa : reino.getTropas()) {
                            if (tropa.getNome().equalsIgnoreCase("Lanceiro")) {
                                tropa.setForca((int) (tropa.getForca() * 1.328));
                                tropa.setDefesa((int) (tropa.getDefesa() * 1.50));
                            }
                        }
                        reino.setRecursos(reino.getRecursos() - lanceiro.getCustoTreinamento());
                        reino.atualizaForcaDefesaReino();
                        ut.exibirTextoPausado("\nLanceiros treinaram com sucesso!\n");
                    } else {
                        ut.exibirTextoPausado("\nInfelizmente você não tem recursos para treinar essa classe...\n");
                    }
                    break;
                case 4:
                    if (reino.getRecursos() >= 800) {
                        ut.exibirTextoPausado("\nIniciando treinamento das Tropas...");
                        for (Tropa tropa : reino.getTropas()) {
                            tropa.setForca((int) (tropa.getForca() * 1.328));
                            tropa.setDefesa((int) (tropa.getDefesa() * 1.50));
                        }
                        reino.setRecursos(reino.getRecursos() - 800);
                        reino.atualizaForcaDefesaReino();
                        ut.exibirTextoPausado("\nAs Tropas treinaram com sucesso!\n");
                    } else {
                        ut.exibirTextoPausado("\nInfelizmente você não tem recursos para treinar suas tropas...\n");
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

    public void listarTropas(Reino reino) {
        Utils ut = new Utils(scanner);
        Mensagens msg = new Mensagens();

        int arqueiros = 0;
        int cavaleiros = 0;
        int lanceiros = 0;

        for (Tropa tropa : reino.getTropas()) {
            if (tropa.getNome().equalsIgnoreCase("Arqueiro")) {
                arqueiros++;
            } else if (tropa.getNome().equalsIgnoreCase("Cavaleiro")) {
                cavaleiros++;
            } else {
                lanceiros++;
            }
        }

        ut.exibirTextoPausado("\n- " + arqueiros + " " + (msg.exibirMensagem("mensagem.listagemtropas.arqueiro." + (arqueiros == 1 ? "singular" : "plural"))));
        ut.exibirTextoPausado("\n- " + cavaleiros + " " + (msg.exibirMensagem("mensagem.listagemtropas.cavaleiro." + (cavaleiros == 1 ? "singular" : "plural"))));
        ut.exibirTextoPausado("\n- " + lanceiros + " " + (msg.exibirMensagem("mensagem.listagemtropas.lanceiro." + (lanceiros == 1 ? "singular" : "plural"))));
        out.println();
    }
}
