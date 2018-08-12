/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author guilherme
 */
public class JavaApplication {

    static int SIZE = 6;
    
    static double[][][] States;
    static int[][] Table;
    
    public static void main(String[] args) throws IOException {

        States = new double[SIZE][SIZE][4];
        Table = new int[SIZE][SIZE];
        
        NewJFrame njf = new NewJFrame(SIZE);
        njf.show();
        
        
        Table[1][0] = 2;
        Table[1][1] = 2;
        Table[1][3] = 2;
        
        Table[3][0] = 2;
        Table[3][2] = 2;
        Table[3][3] = 2;
        
        Table[3][4] = 2;
        
        Table[0][0] = 1;
        Table[2][3] = -1;
        Table[4][4] = -1;
        
        int IML = 5;
        int IMC = 0;
        
        while(MaxValue(States[IML][IMC])<=0){
            while(true){
                int MaxIndex = MaxIndex(States[IML][IMC]);
                int[] NIM = ChangeValue(IML, IMC,MaxIndex);
                
                if(States[IML][IMC][MaxIndex]>0){
                    break;
                }

                if (NIM[0] != IML || NIM[1] != IMC) {
                    IML = NIM[0];
                    IMC = NIM[1];
                }
            }
            IML = 5;
            IMC = 0;
        }
        try{
        Thread.sleep(100);
        } catch (Exception e){}
        njf.UpdateButtons(Table, States);
        PrintTable();
    }

    public static void PrintTable(){
        for (int t = 0; t < SIZE; t++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print("[ ");
                for (int k = 0; k < 4; k++) {
                    System.out.print(States[t][j][k] + " ");
                }
                System.out.print("]");
            }
            System.out.println("");
        }
        System.out.println("");
    
    }
    
    public static int[] ChangeValue(int linha, int coluna, int acao) {

        int linhaTo = linha;
        int colunaTo = coluna;

        switch (acao) {
            case 0:
                colunaTo = coluna + 1;
                break;
            case 1:
                colunaTo = coluna - 1;
                break;
            case 2:
                linhaTo = linha + 1;
                break;
            case 3:
                linhaTo = linha - 1;
                break;
        }

        try {
            if (Table[linhaTo][colunaTo] == 2) {
                throw new Exception("Parede.");
            } 
            
            if (Table[linhaTo][colunaTo] == 1) {
                States[linha][coluna][acao] += Integer.MAX_VALUE;
                
                return new int[]{linha, coluna};
            }
            
            if (Table[linhaTo][colunaTo] == -1) {
                States[linha][coluna][acao] += -1.01;
                return new int[]{linha, coluna};
            }
            
            States[linha][coluna][acao] += -0.01 + 0.1 * MaxValue(States[linhaTo][colunaTo]);
            return new int[]{linhaTo, colunaTo};

        } catch (Exception e) {
            States[linha][coluna][acao] += -0.01;
            return new int[]{linha, coluna};
        }
    }

    public static double MaxValue(double[] State) {
        double MaxValue = Integer.MIN_VALUE;
        for (int i = 0; i < 4; i++) {
            if (State[i] > MaxValue) {
                MaxValue = State[i];
            }
        }
        return MaxValue;
    }
    public static int MaxIndex(double[] State) {
        int MaxIndex = 0;
        double MaxValue = Integer.MIN_VALUE;
        for (int i = 0; i < 4; i++) {
            if (State[i] > MaxValue) {
                MaxValue = State[i];
                MaxIndex = i;
            }
        }
        return MaxIndex;
    }
}
