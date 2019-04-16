package guifabrin;

public enum Tipo {
    POCO(-1.01) , NADA(0), PREMIO(Double.MAX_VALUE), PAREDE(-0.01);

    public double valor;

    private Tipo(double valor){
        this.valor = valor;
    }
}