package utilities.pixels;

/**
 * Abstract class for reusing functionality within Pixels.
 */
public abstract class APixel implements IPixel {
  /* INVARIANT:
   * rgb must be an int[] of length 3 with red, green, and blue intensity values respectively
   */
  private final int[] rgb;

  /**
   * Default constructor.
   *
   * @param rgb an int[] of length 3 that represents the red, green, and blue values respectively
   * @throws IllegalArgumentException if the length of the provided array does not equal 3
   */
  public APixel(int[] rgb) throws IllegalArgumentException {
    if (rgb.length != 3) {
      throw new IllegalArgumentException("Provided array must be of length 3.");
    }
    this.rgb = rgb;
  }

  /**
   * Gets the red value of this pixel.
   *
   * @return the intensity of the red value of this pixel
   */
  public int getR() {
    return this.rgb[0];
  }

  /**
   * Gets the green value of this pixel.
   *
   * @return the intensity of the green value of this pixel
   */
  public int getG() {
    return this.rgb[1];
  }

  /**
   * Gets the blue value of this pixel.
   *
   * @return the intensity of the blue value of this pixel
   */
  public int getB() {
    return this.rgb[2];
  }

  /**
   * Gets the value of a pixel.
   *
   * @return the max value out of the red, green, and blue values
   */
  public int getValue() {
    return Math.max(this.rgb[0], Math.max(this.rgb[1], this.rgb[2]));
  }

  /**
   * Gets the intensity of a pixel.
   *
   * @return the average intensity of the red, green, and blue values
   */
  public int getIntensity() {
    return (this.rgb[0] + this.rgb[1] + this.rgb[2]) / 3;
  }

  /**
   * Gets the luma of a pixel.
   *
   * @return the weighted sum of the red, green, and blue values (0.2126r + 0.7152g + 0.0722b)
   */
  public int getLuma() {
    return (int) (0.2126 * this.rgb[0] + 0.7152 * this.rgb[1] + 0.0722 * this.rgb[2]);
  }

  /**
   * Gets the sepia return for the rgb channel.
   *
   * @return resulting rgb array from a sepia color transformation
   */
  public int[] getSepia() {
    int newR = (int) (0.393 * this.getR() + 0.769 * this.getG() + 0.189 * this.getB());
    int newG = (int) (0.349 * this.getR() + 0.686 * this.getG() + 0.168 * this.getB());
    int newB = (int) (0.272 * this.getR() + 0.534 * this.getG() + 0.131 * this.getB());
    return new int[]{newR, newG, newB};
  }
}
