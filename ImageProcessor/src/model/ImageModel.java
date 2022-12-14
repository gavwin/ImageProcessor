package model;

import java.io.FileNotFoundException;
import java.io.IOException;

import utilities.ComponentType;
import utilities.pixels.IPixel;

/**
 * Interface for modeling different Image file types (currently supports PPM).
 */
public interface ImageModel {

  /**
   * Creates an ImageModel to visualize a different Component Type by setting the
   * all the rgb values to the component type.
   *
   * @param type (ComponentType) representing the component/channel to be visualized
   * @return (ImageModel) greyscale of the same image with the relevant component visualized.
   */
  ImageModel visualizeComponents(ComponentType type);

  /**
   * Creates an ImageModel identical to this image but flipped horizontally.
   *
   * @return (ImageModel) of the identical image except flipped horizontally
   */
  ImageModel flipHorizontally();

  /**
   * Creates an ImageModel identical to this image but flipped vertically.
   *
   * @return (ImageModel) of the identical image except flipped vertically
   */
  ImageModel flipVertically();

  /**
   * Creates an ImageModel identical to this image but all rgb values changed by the increment.
   *
   * @param increment (int) a positive value to brighten the image or
   *                  a negative value to darken the image
   * @return (ImageModel) with all the r g b values change by the increment
   */
  ImageModel brighten(int increment);

  /**
   * Creates an ImageModel identical to this image, but all rgb values are set by applied filter.
   * Filters work by doing the appropriate sized matrix centered at the relevant pixel, and sets the
   * value of r g b components to the value defined by the filter.
   *
   * @param filter the double[][] representing a matrix of values to apply as a filter
   * @return the updated ImageModel
   */
  ImageModel filter(double[][] filter);

  /**
   * Returns the width of the image, number of pixels across the x-axis.
   *
   * @return (int) of the width
   */
  int getWidth();

  /**
   * Returns the height of the image, number of pixels across the y-axis.
   *
   * @return (int) of the height.
   */
  int getHeight();

  /**
   * Function for reading in the image and creating a 2D array representation of the pixels.
   *
   * @param imagePath (String) representing the file path of the image
   * @throws FileNotFoundException if there does not exist a file at the specified path.
   */
  void readImage(String imagePath) throws FileNotFoundException;

  /**
   * Creates a string representation of the image.
   *
   * @return (String) representing the image
   */
  byte[] writeImage() throws IOException;

  /**
   * Returns this ImageModel implementation type with the passed in 2D pixel array representation.
   *
   * @param image (Pixel[][]) representing the image that needs to be
   * @return (ImageModel) of the same implementation type of this instance
   */
  ImageModel renderImage(IPixel[][] image);

  /**
   * Returns a copy of the pixel array.
   * @return (Pixel[][]) copy of the pixel array
   */
  IPixel[][] getPixelArray();
}
