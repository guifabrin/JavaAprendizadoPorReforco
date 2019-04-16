package guifabrin;

import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.ByteArrayInputStream;

public class Helper {

    public static Image base64ToImagem(String imagem64){
        Image imagem = null;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] imageByte = decoder.decodeBuffer(imagem64);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            imagem = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) { }
        return imagem;
    }
}
