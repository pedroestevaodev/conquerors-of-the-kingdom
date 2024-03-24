package com.utils;

import com.exercito.Tropa;
import com.territorio.Edificio;
import com.territorio.Reino;
import java.util.Scanner;
import java.util.function.Predicate;
import static java.lang.System.out;
import org.jetbrains.annotations.NotNull;

public class Utils {
    private final Scanner scanner;

    public Utils(Scanner scanner) {
        this.scanner = scanner;
    }

    public String gerarPergunta(@NotNull String pergunta) {
        out.println(pergunta);
        return scanner.nextLine();
    }

    public int gerarPerguntaInt(@NotNull String pergunta) {
        out.println(pergunta);
        return scanner.nextInt();
    }

    public String validarInfo(@NotNull String mensagem, String mensagemErro, @NotNull Predicate<String> validacao) {
        String valor = gerarPergunta(mensagem);

        while (!validacao.test(valor)) {
            if (!validacao.test(valor)) {
                out.println(mensagemErro);
            }

            valor = gerarPergunta(mensagem);
        }

        return valor;
    }

    public int validarInfoInt(@NotNull String mensagem, String mensagemErro, @NotNull Predicate<Integer> validacao) {
        int valor = gerarPerguntaInt(mensagem);

        while (!validacao.test(valor)) {
            if (!validacao.test(valor)) {
                out.println(mensagemErro);
            }

            valor = gerarPerguntaInt(mensagem);
        }

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
            textoPausado(50);
        }
    }

    public boolean validarCompra(@NotNull Reino reino, int qtd, @NotNull Object objecto) {
        int recursosDiponiveis = reino.getRecursos();
        int custo = 0;

        if (objecto instanceof Tropa) {
            custo = ((Tropa) objecto).getCusto();
        } else if (objecto instanceof Edificio) {
            custo = ((Edificio) objecto).getCusto();
        }

        int compraValor = qtd * custo;

        return qtd >= 0 && compraValor <= recursosDiponiveis;
    }
}
