package utilities.pixels;

/**
 * Represents a single Pixel of an image with standard 3 channel definition.
 */
public class PixelReg extends APixel implements IPixel {

  public PixelReg(int[] rgb) throws IllegalArgumentException {
    super(rgb);
  }
}
