package com.utils;

import org.jetbrains.annotations.NotNull;
import java.util.Scanner;
import java.util.function.Predicate;
import static java.lang.System.out;

public class Utils {
    private final Scanner scanner;

    public Utils(Scanner scanner) {
        this.scanner = scanner;
    }

    public String gerarPergunta(String pergunta) {
        out.println(pergunta);
        return scanner.nextLine();
    }

    public int gerarPerguntaInt(String pergunta) {
        out.println(pergunta);
        return scanner.nextInt();
    }

    public String validarInfo(String mensagem, String mensagemErro, Predicate<String> validacao) {
        String valor;

        do {
            valor = gerarPergunta(mensagem);

            if (!validacao.test(valor)) {
                out.println(mensagemErro);
            }
        } while (!validacao.test(valor));

        return valor;
    }

    public void limparPrompt() {
        for (int i =0; i < 50; i++) {
            out.println();
        }
    }

    public void textoPausado(int tempoPausa) {
        try {
            Thread.sleep(tempoPausa);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void exibirTextoPausado(@NotNull String texto) {
        for (char c : texto.toCharArray()) {
            out.print(c);
            textoPausado(0);
        }
    }
}
