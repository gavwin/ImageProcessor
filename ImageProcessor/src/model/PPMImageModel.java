package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import utilities.pixels.IPixel;
import utilities.pixels.PixelReg;

/**
 * Implementation type of ImageModel representing the .ppm image type.
 */
public class PPMImageModel extends AImageModel implements ImageModel {

  public PPMImageModel(String fileName) {
    super(fileName);
  }

  public PPMImageModel(IPixel[][] image) {
    super(image);
  }

  @Override
  public void readImage(String imagePath) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(imagePath));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + imagePath + " not found!");
    }

    StringBuilder builder = new StringBuilder();
    // Read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    // Now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }

    int width = sc.nextInt();
    int height = sc.nextInt();
    sc.nextInt(); // get nextInt after getting width and height to ignore max value

    this.image = new PixelReg[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();

        int[] rgb = new int[]{r, g, b};


        this.image[i][j] = new PixelReg(rgb);
      }
    }
  }

  @Override
  public byte[] writeImage() {
    StringBuilder rep = new StringBuilder();
    rep.append("P3\n").append(this.getWidth()).append(" ").append(this.getHeight())
            .append("\n255\n");
    for (int i = 0; i < this.getHeight(); i++) {
      for (int j = 0; j < this.getWidth(); j++) {
        rep.append(this.image[i][j].getR(

        )).append("\n").append(this.image[i][j].getG(

        )).append("\n").append(this.image[i][j].getB(

        )).append("\n");
      }

    }

    return rep.toString().getBytes();
  }

  @Override
  public ImageModel renderImage(IPixel[][] image) {
    return new PPMImageModel(image);
  }
}
