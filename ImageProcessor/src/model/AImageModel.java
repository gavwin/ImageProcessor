package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.imageio.ImageIO;

import utilities.ComponentType;
import utilities.pixels.IPixel;
import utilities.pixels.PixelCommands;
import utilities.pixels.PixelReg;

/**
 * Abstract class for reusable functionality between different ImageModel implementation types.
 */
public abstract class AImageModel implements ImageModel {
  protected IPixel[][] image;
  Map<ComponentType, Function<IPixel, int[]>> pixelCommand = new HashMap<>();

  AImageModel(IPixel[][] image) {
    this.image = image;
  }

  AImageModel(String filename) throws IllegalArgumentException {
    this.readImage(filename);
    this.pixelCommand = PixelCommands.getPixelCommands();
  }

  @Override
  public void readImage(String imagePath) {
    try {
      File file = new File(imagePath);
      BufferedImage image = ImageIO.read(file);
      this.image = new IPixel[image.getHeight()][image.getWidth()];
      for (int i = 0; i < this.getHeight(); i++) {
        for (int j = 0; j < this.getWidth(); j++) {
          Color pixel = new Color(image.getRGB(j, i), false);
          this.image[i][j] = new PixelReg(
                  new int[]{pixel.getRed(), pixel.getGreen(), pixel.getBlue()}
          );
        }
      }
    } catch (IndexOutOfBoundsException | IOException e) {
      throw new IllegalArgumentException("Invalid File Passed");
    }
  }

  @Override
  public ImageModel visualizeComponents(ComponentType type) {
    IPixel[][] newImage = new PixelReg[this.getHeight()][this.getWidth()];
    for (int i = 0; i < this.getHeight(); i++) {
      for (int j = 0; j < this.getWidth(); j++) {
        int[] value = pixelCommand.get(type).apply(this.image[i][j]);
        int[] newValue = new int[]{convert(value[0]), convert(value[1]), convert(value[2])};
        IPixel pixel = new PixelReg(newValue);
        newImage[i][j] = pixel;
      }
    }
    return this.renderImage(newImage);
  }

  @Override
  public ImageModel flipHorizontally() {
    IPixel[][] newImage = new PixelReg[this.getHeight()][this.getWidth()];
    for (int i = 0; i < this.getHeight(); i++) {
      for (int j = 0; j <= this.getWidth() / 2; j++) {
        newImage[i][j] = this.image[i][this.getWidth() - 1 - j];
        newImage[i][this.getWidth() - 1 - j] = this.image[i][j];
      }
    }
    return this.renderImage(newImage);
  }

  @Override
  public ImageModel flipVertically() {
    IPixel[][] newImage = new PixelReg[this.getHeight()][this.getWidth()];
    for (int i = 0; i <= this.getHeight() / 2; i++) {
      for (int j = 0; j < this.getWidth(); j++) {
        newImage[i][j] = this.image[this.getHeight() - 1 - i][j];
        newImage[this.getHeight() - 1 - i][j] = this.image[i][j];
      }
    }
    return this.renderImage(newImage);
  }

  @Override
  public ImageModel brighten(int increment) {
    PixelReg[][] newImage = new PixelReg[this.getHeight()][this.getWidth()];
    for (int i = 0; i < this.getHeight(); i++) {
      for (int j = 0; j < this.getWidth(); j++) {
        PixelReg pixel = new PixelReg(
                new int[]{
                        convert(this.image[i][j].getR() + increment),
                        convert(this.image[i][j].getG() + increment),
                        convert(this.image[i][j].getB() + increment)}
        );
        newImage[i][j] = pixel;
      }
    }
    return this.renderImage(newImage);
  }

  @Override
  public ImageModel filter(double[][] filter) {
    PixelReg[][] newImage = new PixelReg[this.getHeight()][this.getWidth()];
    // getting dimensions of filter "box"
    int filterHeight = filter.length;
    int filterWidth = filter[0].length;
    for (int i = 0; i < this.getHeight(); i++) {
      for (int j = 0; j < this.getWidth(); j++) {
        double[][] redMatrix = this.getValueMatrix(ComponentType.Red, i, j,
                filterHeight, filterWidth);
        double[][] greenMatrix = this.getValueMatrix(ComponentType.Green, i, j,
                filterHeight, filterWidth);
        double[][] blueMatrix = this.getValueMatrix(ComponentType.Blue, i, j,
                filterHeight, filterWidth);
        PixelReg pixel = new PixelReg(new int[]{
                filterMultiplication(filter, redMatrix),
                filterMultiplication(filter, greenMatrix),
                filterMultiplication(filter, blueMatrix)
        });
        newImage[i][j] = pixel;
      }
    }
    return this.renderImage(newImage);
  }

  @Override
  public int getWidth() {
    return this.image[0].length;
  }

  @Override
  public int getHeight() {
    return this.image.length;
  }

  @Override
  public IPixel[][] getPixelArray() {
    return this.image.clone();
  }

  private double[][] getValueMatrix(ComponentType type, int row, int col,
                                    int matrixHeight, int matrixWidth) {
    double[][] newMatrix = new double[matrixHeight][matrixWidth];
    for (int i = row - (matrixHeight / 2); i <= row + (matrixHeight / 2); i++) {
      for (int j = col - (matrixWidth / 2); j <= col + (matrixWidth / 2); j++) {
        int[] value = new int[]{0, 0, 0};
        try {
          value = pixelCommand.get(type).apply(this.image[i][j]);
        } catch (IndexOutOfBoundsException e) {
          // if out of bounds, then just default to 0
        }

        int newI = i - row + (matrixHeight / 2);
        int newJ = j - col + (matrixWidth / 2);
        newMatrix[newI][newJ] = value[0]; // Just want to set to single value, not array
      }
    }
    return newMatrix;
  }

  private static int convert(int colorValue) {
    if (colorValue < 0) {
      return 0;
    }
    return Math.min(colorValue, 255);
  }

  private static int filterMultiplication(double[][] filter, double[][] pixelValues) {
    if (filter.length != pixelValues.length || filter[0].length != pixelValues[0].length) {
      throw new IllegalArgumentException("Filter and pixel array are not in the same shape");
    }

    double sum = 0;
    for (int i = 0; i < filter.length; i++) {
      for (int j = 0; j < filter[0].length; j++) {
        sum += filter[i][j] * pixelValues[i][j];
      }
    }

    return convert((int) sum);
  }
}
