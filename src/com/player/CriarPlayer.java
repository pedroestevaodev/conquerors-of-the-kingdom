package com.player;

import com.jogo.Mensagens;
import com.territorio.Reino;
import com.utils.Utils;
import java.util.Scanner;
import static java.lang.System.*;

public class CriarPlayer {
    private final Scanner scanner;

    public CriarPlayer(Scanner scanner) {
        this.scanner = scanner;
    }

    public Player criarJogador() {
        Utils ut = new Utils(scanner);
        Mensagens msg = new Mensagens();

        out.println("\n***********************************************");
        out.println("*           CONQUISTADORES DO REINO           *");
        out.println("***********************************************");

        out.println("\nBem-vindo(a) ao Conquistadores do Reino!\n");
        String nomePlayer = ut.validarInfo("Por favor, digite seu nome:", "O nome não pode conter números, caracteres especiais ou espaço. Por favor, tente novamente.", valor -> valor.matches("[a-zA-Z]+"));
        out.println();
        String generoPlayer = ut.validarInfo("Olá, " + nomePlayer + "! Você deseja ser um rei ou uma rainha? (Digite 'rei' ou 'rainha'):", "Por favor, digite 'rei' ou 'rainha'.", valor -> valor.equalsIgnoreCase("rei") || valor.equalsIgnoreCase("rainha"));
        out.println();
        String reinoPlayer = ut.gerarPergunta("Ótimo, " + generoPlayer + " " + nomePlayer + "! Agora, qual será o nome do seu reino?");

        out.println("\nConfira os dados:");
        out.println(msg.exibirMensagem("mensagem.criacaoplayer."+generoPlayer)+" "+nomePlayer);
        out.println("Gênero: " + generoPlayer);
        out.println("Reino: " + reinoPlayer);
        String confirmacao = ut.gerarPergunta("Os dados estão corretos? (Digite 'sim' ou 'nao'):");

        while (!confirmacao.equalsIgnoreCase("sim")) {
            String opcaoEdicao = ut.gerarPergunta("\nQual dado você gostaria de editar? (nome, gênero ou reino)").toLowerCase();

            switch (opcaoEdicao) {
                case "nome":
                    nomePlayer = ut.gerarPergunta("Por favor, digite seu nome:");
                    break;
                case "gênero":
                    generoPlayer = ut.gerarPergunta("Você deseja ser um rei ou uma rainha? (Digite 'rei' ou 'rainha'):");
                    break;
                case "reino":
                    reinoPlayer = ut.gerarPergunta("Qual nome você deseja dar para o seu reino?");
                    break;
                default:
                    out.println("Opção inválida!");
            }

            out.println("\nResumo dos dados atualizados:");
            out.println(msg.exibirMensagem("mensagem.criacaoplayer."+generoPlayer)+" "+nomePlayer);
            out.println("Gênero: " + generoPlayer);
            out.println("Nome do reino: " + reinoPlayer);

            out.println("\nOs dados estão corretos? (Digite 'sim' para confirmar ou 'editar' para fazer alterações):");
            confirmacao = scanner.nextLine().toLowerCase();
        }

        out.println("\n"+msg.exibirMensagem("mensagem.criacaoplayer.sucesso."+generoPlayer)+"\n");

        Reino reino = new Reino(reinoPlayer, 100, 10);

        return new Player(nomePlayer, generoPlayer, reino);
    }
}
