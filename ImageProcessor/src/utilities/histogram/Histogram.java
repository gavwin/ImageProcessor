package utilities.histogram;

import java.util.Arrays;

import java.util.Map;
import java.util.function.Function;

import model.ImageModel;
import utilities.ComponentType;
import utilities.pixels.IPixel;
import utilities.pixels.PixelCommands;

/**
 * Represents a histogram.
 */
public class Histogram {
  ImageModel model;
  Map<ComponentType, Function<IPixel, int[]>> pixelCommand;

  /**
   * Default constructor.
   */
  public Histogram() {
    this.pixelCommand = PixelCommands.getPixelCommands();
  }

  /**
   * Helper method for getting a ComponentType distribution in order to build the histogram.
   * @param type the component type to get the distribution for
   * @return the distribution as an int[]
   */
  public int[] getComponentDistribution(ComponentType type, ImageModel model) {
    int[] distribution = new int[256];
    Arrays.fill(distribution, 0);
    for (IPixel[] row : model.visualizeComponents(type).getPixelArray()) {
      for (IPixel pixel : row) {
        int[] value = pixelCommand.get(type).apply(pixel);
        distribution[value[0]]++;
      }
    }

    return distribution;
  }
}
