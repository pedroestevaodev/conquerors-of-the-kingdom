package com.jogo;

import com.player.Player;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static java.lang.System.*;

public class Mensagens {
    public String parametrosMensagem(String mensagem, String... parametros) {
        for (int i = 0; i < parametros.length; i++) {
            mensagem = mensagem.replace("{"+i+"}", parametros[i]);
        }
        return mensagem;
    }

    public String exibirMensagem(String mensagem) {
        Properties prop = new Properties();

        try {
            prop.load(Mensagens.class.getResourceAsStream("/resources/mensagens.properties"));
            return prop.getProperty(mensagem);
        } catch (IOException e) {
            e.printStackTrace();
            return "Erro ao carregar mensagem;";
        }
    }
}
