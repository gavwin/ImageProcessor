package utilities.pixels;

/**
 * Interface for a Pixel to represent a single pixel of an image with standard 3 channel definition.
 */
public interface IPixel {

  /**
   * Gets the red value of this pixel.
   *
   * @return the intensity of the red value of this pixel
   */
  int getR();

  /**
   * Gets the green value of this pixel.
   *
   * @return the intensity of the green value of this pixel
   */
  int getG();

  /**
   * Gets the blue value of this pixel.
   *
   * @return the intensity of the blue value of this pixel
   */
  int getB();

  /**
   * Gets the value of a pixel.
   *
   * @return the max value out of the red, green, and blue values
   */
  int getValue();

  /**
   * Gets the intensity of a pixel.
   *
   * @return the average intensity of the red, green, and blue values
   */
  int getIntensity();

  /**
   * Gets the luma of a pixel.
   *
   * @return the weighted sum of the red, green, and blue values (0.2126r + 0.7152g + 0.0722b)
   */
  int getLuma();

  /**
   * Gets the sepia return for the rgb channel.
   *
   * @return resulting rgb array from a sepia color transformation
   */
  int[] getSepia();
}
