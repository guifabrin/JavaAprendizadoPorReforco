package guifabrin;

public class Labirinto {

    private int tamanho;
    private double[][] tabela;

    public Labirinto(int tamanho){
        this.tabela = new double[tamanho][tamanho];
    }

    public void adiciona(Tipo tipo, int linha, int coluna){
        this.tabela[linha][coluna] = tipo.valor;
    }

    public Double celula(int linha, int coluna){
        try{
            return this.tabela[linha][coluna];
        } catch (ArrayIndexOutOfBoundsException exception){
            return null;
        }
    }

    public boolean ehParede(int linha, int coluna){
        return this.tabela[linha][coluna] == Tipo.PAREDE.valor;
    }

    public boolean ehPremio(int linha, int coluna){
        return this.tabela[linha][coluna] == Tipo.PREMIO.valor;
    }

    public boolean ehPoco(int linha, int coluna){
        return this.tabela[linha][coluna] == Tipo.POCO.valor;
    }

    public boolean ehNada(int linha, int coluna){
        return this.tabela[linha][coluna] == Tipo.POCO.valor;
    }

    public Tipo tipo(int linha, int coluna){
        if (this.celula(linha, coluna) == null) return Tipo.PAREDE;
        for (Tipo tipo : Tipo.values())
            if (this.tabela[linha][coluna] == tipo.valor) return tipo;
        return null;
    }

    public Tipo tipo(Direcao direcao){
        return this.tipo(direcao.getLinha(), direcao.getColuna());
    }
}
