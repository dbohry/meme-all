package com.lhamacorp.com.service

import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

import javax.imageio.ImageIO
import java.awt.*
import java.awt.image.BufferedImage

@Component
class ImageService {

    def upload(MultipartFile file, String text1) {
        BufferedImage image = convertMultipartFile(file)
        process(image, text1)
    }

    private String process(BufferedImage image, String text1) {

        text1 = text1.toUpperCase()

        try {
            Integer width = image.getWidth()
            Integer height = image.getHeight()

            Graphics2D graphics = image.getGraphics()
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON)

            graphics.setColor(new Color(1f, 0f, 0f, 0.0f))
            graphics.fillRect(0, 0, 200, 50)

            Font font = new Font("Impact", Font.BOLD, width / 20)
            Rectangle rect = new Rectangle(0, 0, width, height);

            FontMetrics metrics = graphics.getFontMetrics(font);
            int x = (rect.width - metrics.stringWidth(text1)) / 2;
            int y = ((rect.height - metrics.getHeight()) / 1) + metrics.getAscent();
            graphics.setFont(font);

            graphics.setColor(Color.BLACK)
            graphics.drawString(text1, ShiftWest(x, 3), ShiftNorth(y, 3));
            graphics.drawString(text1, ShiftWest(x, 3), ShiftSouth(y, 3));
            graphics.drawString(text1, ShiftEast(x, 3), ShiftNorth(y, 3));
            graphics.drawString(text1, ShiftEast(x, 3), ShiftSouth(y, 3));
            graphics.setColor(Color.WHITE);
            graphics.drawString(text1, x, y);

            ImageIO.write(image, "jpg", new File("/tmp/image.jpg"));

            "Criado com sucesso!"
        } catch (Exception e) {
            "Falha: " + e.getMessage()
        }
    }

    int ShiftNorth(int p, int distance) {
        p - distance
    }

    int ShiftSouth(int p, int distance) {
        p + distance
    }

    int ShiftEast(int p, int distance) {
        p + distance
    }

    int ShiftWest(int p, int distance) {
        p - distance
    }

    private BufferedImage convertMultipartFile(MultipartFile file) {
        InputStream buff = file.getInputStream()
        ImageIO.read(buff)
    }

}
