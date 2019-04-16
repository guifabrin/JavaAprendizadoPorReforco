/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guifabrin;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;

/**
 * @author guilherme
 */
public class NewJFrame extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    int SIZE;
    int BUTTON_SIZE = 50;
    int BUTTON_MARGIN = 10;
    int LOCK = -1;

    JButton[][] Paineis;

    public NewJFrame(int SIZE) {
        initComponents();
        this.SIZE = SIZE;
        Paineis = new JButton[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                Paineis[i][j] = new JButton();
                Paineis[i][j].setSize(BUTTON_SIZE, BUTTON_SIZE);
                Paineis[i][j].setLocation(j * BUTTON_SIZE + BUTTON_MARGIN, i * BUTTON_SIZE + BUTTON_MARGIN);
                this.add(Paineis[i][j]);
            }
        }
        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        int IMAGE_SIZE = BUTTON_SIZE / 2;
        final JButton WALL = new JButton();
        WALL.setLocation(BUTTON_MARGIN, BUTTON_SIZE * SIZE + SIZE * BUTTON_MARGIN - 20);
        WALL.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LOCK = 0;
                WALL.setEnabled(false);
            }
        });
        try {
            Image img = ImageIO.read(getClass().getResource("index.jpeg")).getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, java.awt.Image.SCALE_SMOOTH);
            WALL.setIcon(new ImageIcon(img));
        }catch (Exception e){

        }
        WALL.setSize(120, 120);

        this.add(WALL);

        JButton WIN = new JButton();
        WIN.setLocation(BUTTON_MARGIN + 120, BUTTON_SIZE * SIZE + SIZE * BUTTON_MARGIN - 20);
        WIN.setSize(120, 120);
        WIN.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                LOCK = 1;
                WALL.setEnabled(false);
            }
        });


        this.add(WIN);

        JButton LOSE = new JButton();
        LOSE.setLocation(BUTTON_MARGIN + 240, BUTTON_SIZE * SIZE + SIZE * BUTTON_MARGIN - 20);
        LOSE.setSize(120, 120);
        LOSE.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                LOCK = 2;
                WALL.setEnabled(false);
            }
        });
        this.add(LOSE);


        JButton START = new JButton();
        START.setLocation(BUTTON_MARGIN + 360, BUTTON_SIZE * SIZE + SIZE * BUTTON_MARGIN - 20);
        START.setSize(120, 120);
        START.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                LOCK = 3;
                WALL.setEnabled(false);
            }
        });
        this.add(START);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 267, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 196, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void UpdateButtons(Labirinto Table, double[][][] States, LearnApplication la) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                Tipo tipo = Table.tipo(i, j);
                Direcao maiorDirecao = la.maiorDirecao(States[i][j]);
                if (tipo == Tipo.NADA) {
                    Paineis[i][j].setIcon(new ImageIcon(maiorDirecao.imagem));
                } else {
                    Paineis[i][j].setIcon(new ImageIcon(tipo.imagem));
                }
            }
        }
    }
}
