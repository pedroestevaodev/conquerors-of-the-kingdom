import com.exercito.Batalha;
import com.jogador.Mapa;
import com.jogador.Player;
import com.territorio.Reino;

import java.util.Scanner;

public class Jogo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo ao Conquistadores do Reino!");
        System.out.println("Por favor, digite seu nome:");
        String nomePlayer = scanner.nextLine();

        System.out.println("Olá, " + nome + "! Você deseja ser um rei ou uma rainha? (Digite 'rei' ou 'rainha'):");
        String generoPlayer = scanner.nextLine();

        System.out.println("Ótimo, " + genero + " " + nome + "! Agora, qual será o nome do seu reino?");
        String reinoPlayer = scanner.nextLine();

        System.out.println("\nConfira os dados:");
        System.out.println("Nome: " + nome);
        System.out.println("Gênero: " + genero);
        System.out.println("Nome do Reino: " + nomeReino);
        System.out.println("Os dados estão corretos? (Digite 'sim' ou 'nao'):");
        String confirmacao = scanner.nextLine();

        if (confirmacao.equalsIgnoreCase("sim")) {
            // Dados confirmados, criar o Player
            Player player = new Player(nome, genero, nomeReino);
            System.out.println("\nJogador criado com sucesso!");
            System.out.println("Bem-vindo(a), " + player.getNome() + ", o(a) " + player.getGenero() + " do reino " + player.getNomeReino() + "!");
        } else if (confirmacao.equalsIgnoreCase("nao")) {
            // Dados incorretos, permitir ao jogador corrigir
            System.out.println("\nPor favor, digite 'nome', 'genero' ou 'reino' para corrigir as informações correspondentes:");
            String opcaoCorrecao = scanner.nextLine();
            corrigirInformacoes(opcaoCorrecao, scanner);
        } else {
            // Resposta inválida
            System.out.println("\nResposta inválida. Por favor, execute o programa novamente.");
        }

        scanner.close();
    }
}