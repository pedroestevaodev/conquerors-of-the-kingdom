class Inimigos {
    private Guerreiro[] guerreiros;
    private Arqueiro[] arqueiros;
    private Lanceiro[] lanceiros;

    public Inimigos(int quantidadeGuerreiros, int quantidadeArqueiros, int quantidadeLanceiros) {
        guerreiros = new Guerreiro[quantidadeGuerreiros];
        arqueiros = new Arqueiro[quantidadeArqueiros];
        lanceiros = new Lanceiro[quantidadeLanceiros];

        for (int i = 0; i < quantidadeGuerreiros; i++) {
            guerreiros[i] = new Guerreiro("Inimigo G" + (i + 1), 100);
        }

        for (int i = 0; i < quantidadeArqueiros; i++) {
            arqueiros[i] = new Arqueiro("Inimigo A" + (i + 1), 100);
        }

        for (int i = 0; i < quantidadeLanceiros; i++) {
            lanceiros[i] = new Lanceiro("Inimigo L" + (i + 1), 100);
        }
    }

    public Personagem getInimigo(int classe, int indice) {
        Personagem[] vetorInimigos = null;
        switch (classe) {
            case 1:
                vetorInimigos = guerreiros;
                break;
            case 2:
                vetorInimigos = arqueiros;
                break;
            case 3:
                vetorInimigos = lanceiros;
                break;
        }

        if (vetorInimigos != null && indice >= 0 && indice < vetorInimigos.length) {
            return vetorInimigos[indice];
        }

        return null;
    }

    public Guerreiro[] getGuerreiros() {
        return guerreiros;
    }

    public Arqueiro[] getArqueiros() {
        return arqueiros;
    }

    public Lanceiro[] getLanceiros() {
        return lanceiros;
    }
}