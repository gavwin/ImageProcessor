package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import utilities.pixels.IPixel;

/**
 * Represents a BMP image.
 */
public class BMPImageModel
        extends AImageModel implements ImageModel {

  public BMPImageModel(String fileName) {
    super(fileName);
  }

  public BMPImageModel(IPixel[][] image) {
    super(image);
  }

  @Override
  public byte[] writeImage() throws IOException {
    int width = this.getWidth();
    int height = this.getHeight();
    BufferedImage buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        IPixel pixel = image[i][j];
        Color color = new Color(pixel.getR(), pixel.getG(), pixel.getB());
        buffer.setRGB(j, i, color.getRGB());
      }
    }

    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
    ImageIO.write(buffer, "bmp", byteStream);
    return byteStream.toByteArray();
  }

  @Override
  public ImageModel renderImage(IPixel[][] image) {
    return new BMPImageModel(image);
  }
}
