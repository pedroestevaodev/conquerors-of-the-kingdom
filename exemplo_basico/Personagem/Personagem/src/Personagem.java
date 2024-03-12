class Personagem {
    private String nome;
    private int vida;

    public Personagem(String nome, int vida) {
        this.nome = nome;
        this.vida = vida;
    }

    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }

    public void receberDano(int dano) {
        vida -= dano;
        if (vida < 0) {
            vida = 0;
        }
        System.out.println(nome + " recebeu " + dano + " de dano. Vida restante: " + vida);
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public void atacar(Personagem alvo) {
        int dano = 30;
        alvo.receberDano(dano);
    }
}