/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guifabrin;

import java.io.IOException;

/**
 * @author guilherme
 */
public class LearnApplication {

    int tamanho = 6;
    NewJFrame njf;
    double[][][] estados;
    Labirinto labirinto;

    public static void main(String[] args) throws IOException {
        new LearnApplication();
    }

    private void obstaculos(){
        labirinto.adiciona(Tipo.PREMIO, 0, 0);
        labirinto.adiciona(Tipo.PAREDE, 1, 0);
        labirinto.adiciona(Tipo.PAREDE, 1, 1);
        labirinto.adiciona(Tipo.PAREDE, 1, 3);
        labirinto.adiciona(Tipo.PAREDE, 3, 0);
        labirinto.adiciona(Tipo.PAREDE, 3, 2);
        labirinto.adiciona(Tipo.PAREDE, 3, 3);
        labirinto.adiciona(Tipo.PAREDE, 3, 4);
        labirinto.adiciona(Tipo.POCO, 2, 3);
        labirinto.adiciona(Tipo.POCO, 4, 4);
    }

    public LearnApplication(){
        estados = new double[tamanho][tamanho][4];
        labirinto = new Labirinto(tamanho);
        obstaculos();
        njf = new NewJFrame(tamanho);
        njf.show();
        int IML = 5;
        int IMC = 0;
        while (maiorValor(estados[IML][IMC]) <= 0) {
            while (true) {
                Direcao direcao = maiorDirecao(estados[IML][IMC]);
                int[] NIM = ChangeValue(IML, IMC, direcao);

                if (estados[IML][IMC][direcao.valor] > 0) break;

                if (NIM[0] != IML || NIM[1] != IMC) {
                    IML = NIM[0];
                    IMC = NIM[1];
                }
                PrintTable();
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                }
            }
            IML = 5;
            IMC = 0;
        }
    }

    public void PrintTable() {
        if (njf != null)
            njf.UpdateButtons(labirinto, estados, this);
        for (int t = 0; t < tamanho; t++) {
            for (int j = 0; j < tamanho; j++) {
                System.out.print("[ ");
                for (int k = 0; k < 4; k++) {
                    System.out.print(estados[t][j][k] + " ");
                }
                System.out.print("]");
            }
            System.out.println("");
        }
        System.out.println("");

    }

    public int[] ChangeValue(int linha, int coluna, Direcao direcao) {
        direcao.setLinha(linha);
        direcao.setColuna(coluna);
        Tipo tipo = this.labirinto.tipo(direcao);
        if (tipo == Tipo.NADA){
            estados[linha][coluna][direcao.valor] += 0.1 * maiorValor(estados[direcao.getLinha()][direcao.getColuna()]) -0.01 ;
            return new int[]{direcao.getLinha(), direcao.getColuna()};
        }
        estados[linha][coluna][direcao.valor] += tipo.valor;
        return new int[]{linha, coluna};
    }

    public double maiorValor(double[] estado) {
        double maiorValor = Integer.MIN_VALUE;
        for (int indice = 0; indice < 4; indice++) {
            if (estado[indice] > maiorValor)
                maiorValor = estado[indice];
        }
        return maiorValor;
    }

    public Direcao maiorDirecao(double[] State) {
        Direcao maiorDirecao = null;
        double maiorValor = Integer.MIN_VALUE;
        for (Direcao direcao : Direcao.values()) {
            if (State[direcao.valor] > maiorValor) {
                maiorValor = State[direcao.valor];
                maiorDirecao = direcao;
            }
        }
        return maiorDirecao;
    }
}
