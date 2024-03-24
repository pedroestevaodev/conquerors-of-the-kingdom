import com.jogo.Mensagens;
import com.jogo.Menu;
import com.player.CriarPlayer;
import com.player.Player;
import com.territorio.GerenciadorReinos;
import com.territorio.Reino;
import com.utils.Utils;
import java.util.List;
import java.util.Scanner;
import static java.lang.System.*;

public class Jogo {
    public static void main(String[] args) {
        /*Testes*/
//        Reino reino = new Reino("Aventura", 1000, 10);
//        Player player = new Player("João", "rei", reino);

        Scanner scanner = new Scanner(in);
        Utils ut = new Utils(scanner);
        Mensagens msg = new Mensagens();

        CriarPlayer criar = new CriarPlayer(scanner);
        Player player = criar.criarJogador();

        String perguntaIniciar = ut.validarInfo("Está pronto para iniciar a jornada? (sim ou nao)", "Por favor, digite 'sim' ou 'nao'.", valor -> valor.equalsIgnoreCase("sim") || valor.equalsIgnoreCase("nao"));
        if (perguntaIniciar.equalsIgnoreCase("nao")) {
            out.println("\nEncerrando o jogo...");
            exit(0);
        }

        ut.limparPrompt();
        ut.exibirTextoPausado(msg.parametrosMensagem(msg.exibirMensagem("mensagem.start."+player.getGenero()), player.getNome(), player.getGenero(), player.getReino().getNome()).replace("[BREAK]", "\n"));
        out.println("\n\n");

        List<Reino> reinos = new GerenciadorReinos().gerarReinos();

        Menu menu = new Menu(player, reinos);

        menu.exibirMenu();

        scanner.close();
    }
}