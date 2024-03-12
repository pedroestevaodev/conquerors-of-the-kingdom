import java.util.Random;
import java.util.Scanner;

public class Main{

public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Quantos guerreiros você quer?");
        int quantidadeGuerreiros = scanner.nextInt();

        System.out.println("Quantos arqueiros você quer?");
        int quantidadeArqueiros = scanner.nextInt();

        System.out.println("Quantos lanceiros você quer?");
        int quantidadeLanceiros = scanner.nextInt();

        // Verificando se a quantidade de personagens é menor do que 5 (padrão)
        if (quantidadeGuerreiros < 5 || quantidadeArqueiros < 5 || quantidadeLanceiros < 5) {
            System.out.println("Atenção: A chance de vitória é de apenas 10% devido à quantidade insuficiente de inimigos.");
        }

        // Criando inimigos
        Inimigos inimigos = new Inimigos(quantidadeGuerreiros, quantidadeArqueiros, quantidadeLanceiros);

        // Criando vetores para armazenar os personagens do jogador
        Guerreiro[] guerreirosJogador = new Guerreiro[quantidadeGuerreiros];
        Arqueiro[] arqueirosJogador = new Arqueiro[quantidadeArqueiros];
        Lanceiro[] lanceirosJogador = new Lanceiro[quantidadeLanceiros];

        // Adicionando Guerreiros do jogador ao vetor
        for (int i = 0; i < quantidadeGuerreiros; i++) {
            guerreirosJogador[i] = new Guerreiro("G" + (i + 1), 100);
        }

        // Adicionando Arqueiros do jogador ao vetor
        for (int i = 0; i < quantidadeArqueiros; i++) {
            arqueirosJogador[i] = new Arqueiro("A" + (i + 1), 100);
        }

        // Adicionando Lanceiros do jogador ao vetor
        for (int i = 0; i < quantidadeLanceiros; i++) {
            lanceirosJogador[i] = new Lanceiro("L" + (i + 1), 100);
        }

        // Simulando ataques
        Random random = new Random();

        // Loop principal para as interações do usuário
        while (true) {
            // Inimigos atacam
            inimigosAtacam(inimigos, guerreirosJogador, arqueirosJogador, lanceirosJogador, random);

            System.out.println("Escolha um personagem:");
            System.out.println("1. Guerreiro");
            System.out.println("2. Arqueiro");
            System.out.println("3. Lanceiro");
            System.out.println("0. Sair");

            int escolhaClasse = scanner.nextInt();

            if (escolhaClasse == 0) {
                System.out.println("Jogo encerrado. Obrigado por jogar!");
                break;
            }

            // Seleção do vetor de personagens do jogador
            Personagem[] vetorPersonagensJogador = null;
            switch (escolhaClasse) {
                case 1:
                    vetorPersonagensJogador = guerreirosJogador;
                    break;
                case 2:
                    vetorPersonagensJogador = arqueirosJogador;
                    break;
                case 3:
                    vetorPersonagensJogador = lanceirosJogador;
                    break;
                default:
                    System.out.println("Escolha inválida. Tente novamente.");
                    continue;
            }

            // Exibição dos personagens disponíveis do jogador
            System.out.println("Personagens disponíveis do jogador:");
            for (int i = 0; i < vetorPersonagensJogador.length; i++) {
                if (vetorPersonagensJogador[i] != null && vetorPersonagensJogador[i].estaVivo()) {
                    System.out.println(i + 1 + ". " + vetorPersonagensJogador[i].getNome() + " - Vida: " + vetorPersonagensJogador[i].getVida());
                }
            }

            System.out.println("Com qual posição do personagem você deseja atacar? (1 - " + vetorPersonagensJogador.length + ")");
            int escolhaIndice = scanner.nextInt();

            // Verificação se a escolha do índice está dentro dos limites
            if (escolhaIndice < 1 || escolhaIndice > vetorPersonagensJogador.length || vetorPersonagensJogador[escolhaIndice - 1] == null || !vetorPersonagensJogador[escolhaIndice - 1].estaVivo()) {
                System.out.println("Escolha inválida. Tente novamente.");
                continue;
            }

            // Realizando o ataque de acordo com a escolha do usuário
            realizarAtaque(vetorPersonagensJogador[escolhaIndice - 1], inimigos, escolhaClasse, random);

            // Verificando se algum inimigo foi derrotado
            verificarInimigosDerrotados(inimigos);

            // Se todos os inimigos foram derrotados, encerra o jogo
            if (todosInimigosDerrotados(inimigos)) {
                System.out.println("Parabéns! Todos os inimigos foram derrotados. Você venceu!");
                break;
            }

            // Inimigos atacam novamente após o movimento do jogador
            inimigosAtacam(inimigos, guerreirosJogador, arqueirosJogador, lanceirosJogador, random);

            // Verificando se algum personagem do jogador foi derrotado
            verificarPersonagensDerrotados(vetorPersonagensJogador);

            // Se todos os personagens do jogador foram derrotados, encerra o jogo
            if (todosPersonagensDerrotados(vetorPersonagensJogador)) {
                System.out.println("Todos os seus personagens foram derrotados. Você perdeu!");
                break;
            }
        }
        scanner.close();
    }

    private static void inimigosAtacam(Inimigos inimigos, Guerreiro[] guerreirosJogador, Arqueiro[] arqueirosJogador, Lanceiro[] lanceirosJogador, Random random) {
        // Inimigos atacam
        for (int i = 0; i < inimigos.getGuerreiros().length; i++) {
            atacarInimigo(inimigos.getInimigo(1, i), guerreirosJogador, random);
        }

        for (int i = 0; i < inimigos.getArqueiros().length; i++) {
            atacarInimigo(inimigos.getInimigo(2, i), arqueirosJogador, random);
        }

        for (int i = 0; i < inimigos.getLanceiros().length; i++) {
            atacarInimigo(inimigos.getInimigo(3, i), lanceirosJogador, random);
        }
    }

    private static void atacarInimigo(Personagem inimigo, Personagem[] personagensJogador, Random random) {
        if (inimigo != null && inimigo.estaVivo()) {
            int escolhaJogador = random.nextInt(personagensJogador.length);
            Personagem jogadorAtacante = personagensJogador[escolhaJogador];

            jogadorAtacante.atacar(inimigo);

            if (!inimigo.estaVivo()) {
                System.out.println("Inimigo " + inimigo.getNome() + " foi derrotado. Próximo movimento.");
            }
        }
    }

    private static void realizarAtaque(Personagem jogador, Inimigos inimigos, int escolhaClasse, Random random) {
        Personagem[] vetorInimigos = null;
        switch (escolhaClasse) {
            case 1:
                vetorInimigos = inimigos.getGuerreiros();
                break;
            case 2:
                vetorInimigos = inimigos.getArqueiros();
                break;
            case 3:
                vetorInimigos = inimigos.getLanceiros();
                break;
        }

        if (vetorInimigos != null) {
            for (int i = 0; i < vetorInimigos.length; i++) {
                if (vetorInimigos[i] != null && vetorInimigos[i].estaVivo()) {
                    jogador.atacar(vetorInimigos[i]);

                    if (!vetorInimigos[i].estaVivo()) {
                        System.out.println("Inimigo " + vetorInimigos[i].getNome() + " foi derrotado. Próximo movimento.");
                    }
                }
            }
        }
    }

    private static void verificarInimigosDerrotados(Inimigos inimigos) {
        // Verifica se algum inimigo foi derrotado
        for (int i = 0; i < inimigos.getGuerreiros().length; i++) {
            verificarDerrotaInimigo(inimigos.getInimigo(1, i));
        }

        for (int i = 0; i < inimigos.getArqueiros().length; i++) {
            verificarDerrotaInimigo(inimigos.getInimigo(2, i));
        }

        for (int i = 0; i < inimigos.getLanceiros().length; i++) {
            verificarDerrotaInimigo(inimigos.getInimigo(3, i));
        }
    }

    private static void verificarDerrotaInimigo(Personagem inimigo) {
        if (inimigo != null && !inimigo.estaVivo()) {
            System.out.println("Inimigo " + inimigo.getNome() + " foi derrotado!");
            inimigo = null;
        }
    }

    private static boolean todosInimigosDerrotados(Inimigos inimigos) {
        // Verifica se todos os inimigos foram derrotados
        for (int i = 0; i < inimigos.getGuerreiros().length; i++) {
            if (inimigos.getInimigo(1, i) != null && inimigos.getInimigo(1, i).estaVivo()) {
                return false;
            }
        }

        for (int i = 0; i < inimigos.getArqueiros().length; i++) {
            if (inimigos.getInimigo(2, i) != null && inimigos.getInimigo(2, i).estaVivo()) {
                return false;
            }
        }

        for (int i = 0; i < inimigos.getLanceiros().length; i++) {
            if (inimigos.getInimigo(3, i) != null && inimigos.getInimigo(3, i).estaVivo()) {
                return false;
            }
        }

        return true;
    }

    private static void verificarPersonagensDerrotados(Personagem[] personagens) {
        // Verifica se algum personagem do jogador foi derrotado
        for (int i = 0; i < personagens.length; i++) {
            verificarDerrotaPersonagem(personagens[i]);
        }
    }

    private static void verificarDerrotaPersonagem(Personagem personagem) {
        if (personagem != null && !personagem.estaVivo()) {
            System.out.println(personagem.getNome() + " foi derrotado!");
            personagem = null;
        }
    }

    private static boolean todosPersonagensDerrotados(Personagem[] personagens) {
        // Verifica se todos os personagens do jogador foram derrotados
        for (int i = 0; i < personagens.length; i++) {
            if (personagens[i] != null && personagens[i].estaVivo()) {
                return false;
            }
        }

        return true;
    }
}