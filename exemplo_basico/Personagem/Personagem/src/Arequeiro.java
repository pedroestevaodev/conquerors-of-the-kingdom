class Arqueiro extends Personagem {
    public Arqueiro(String nome, int vida) {
        super(nome, vida);
    }

    @Override
    public void atacar(Personagem alvo) {
        int dano = 30;
        alvo.receberDano(dano);
    }
}