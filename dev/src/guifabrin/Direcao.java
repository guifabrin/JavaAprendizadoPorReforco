package guifabrin;

import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

public enum Direcao {
    DIRETA(0, "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"),
    ESQUERDA(1, "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"),
    CIMA(2, "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"),
    BAIXO(3, "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");

    public int valor;
    public Image imagem;
    private int linha;
    private int coluna;

    private Direcao(int valor, String base64imagem) {
        this.valor = valor;
        this.imagem = Helper.base64ToImagem(base64imagem);
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