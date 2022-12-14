package utilities.pixels;

/**
 * Represents a single pixel with 4 channel definition (r g b alpha).
 */
public class PixelAlpha extends APixel implements IPixel {

  private final int alpha;

  /**
   * Default constructor.
   *
   * @param rgb   an int[] of length 3 that represents the red, green, and blue values respectively
   * @param alpha representing the alpha (transparency) of the pixel
   * @throws IllegalArgumentException if the length of the provided array does not equal 3
   */
  public PixelAlpha(int[] rgb, int alpha) throws IllegalArgumentException {
    super(rgb);
    this.alpha = alpha;
  }

  /**
   * Returns the alpha of this pixel.
   *
   * @return (int) alpha value
   */
  public int getAlpha() {
    return this.alpha;
  }
}
