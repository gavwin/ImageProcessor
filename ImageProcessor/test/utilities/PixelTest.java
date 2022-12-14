package utilities;

import org.junit.Test;

import utilities.pixels.IPixel;
import utilities.pixels.PixelReg;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the Pixel class.
 */
public class PixelTest {

  int[] rgb1 = new int[]{209, 43, 123};
  PixelReg pixel1 = new PixelReg(rgb1);

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInvalidArrayLength() {
    int[] rgb = new int[5];
    IPixel pixel = new PixelReg(rgb);
  }

  @Test
  public void getR() {
    assertEquals(209, this.pixel1.getR());
  }

  @Test
  public void getG() {
    assertEquals(43, this.pixel1.getG());
  }

  @Test
  public void getB() {
    assertEquals(123, this.pixel1.getB());
  }

  @Test
  public void value() {
    assertEquals(209, this.pixel1.getValue());
  }

  @Test
  public void intensity() {
    assertEquals(125, this.pixel1.getIntensity());
  }

  @Test
  public void luma() {
    assertEquals(84.0676, this.pixel1.getLuma(), 0.001);
  }
}