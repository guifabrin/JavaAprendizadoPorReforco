package guifabrin;

public enum Direcao {
    DIRETA(0), ESQUERDA(1), CIMA(2), BAIXO(3);

    public int valor;
    private int linha;
    private int coluna;

    private Direcao(int valor) {
        this.valor = valor;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha + (this == Direcao.CIMA ? 1 : (this == Direcao.BAIXO ? -1 : 0));
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna + (this == Direcao.DIRETA ? 1 : (this == Direcao.ESQUERDA ? -1 : 0));
    }
}