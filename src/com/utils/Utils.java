package com.utils;

import com.exercito.Tropa;
import com.territorio.Edificio;
import com.territorio.Reino;
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
        String valor = gerarPergunta(mensagem);

        while (!validacao.test(valor)) {
            if (!validacao.test(valor)) {
                out.println(mensagemErro);
            }

            valor = gerarPergunta(mensagem);
        }

        return valor;
    }

    public int validarInfoInt(String mensagem, String mensagemErro, Predicate<Integer> validacao) {
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

    public void exibirTextoPausado(String texto) {
        for (char c : texto.toCharArray()) {
            out.print(c);
            textoPausado(50);
        }
    }

    public boolean validarCompra(Reino reino, int qtd, Object objecto) {
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
