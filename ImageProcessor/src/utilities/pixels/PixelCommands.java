package utilities.pixels;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import utilities.ComponentType;

/**
 * Class for setting commands for the pixels using lambdas.
 */
public class PixelCommands {

  /**
   * Method for actually setting the commands.
   *
   * @return the pixel command to get
   */
  public static Map<ComponentType, Function<IPixel, int[]>> getPixelCommands() {
    Map<ComponentType, Function<IPixel, int[]>> pixelCommand = new HashMap<>();

    pixelCommand.put(ComponentType.Red, pixel -> new int[]{pixel.getR(), pixel.getR(),
            pixel.getR()});
    pixelCommand.put(ComponentType.Green, pixel -> new int[]{pixel.getG(), pixel.getG(),
            pixel.getG()});
    pixelCommand.put(ComponentType.Blue, pixel -> new int[]{pixel.getB(), pixel.getB(),
            pixel.getB()});
    pixelCommand.put(ComponentType.Value, pixel -> new int[]{pixel.getValue(), pixel.getValue(),
            pixel.getValue()});
    pixelCommand.put(ComponentType.Intensity, pixel -> new int[]{pixel.getIntensity(),
            pixel.getIntensity(), pixel.getIntensity()});
    pixelCommand.put(ComponentType.Luma, pixel -> new int[]{pixel.getIntensity(),
            pixel.getIntensity(), pixel.getIntensity()});
    pixelCommand.put(ComponentType.Sepia, IPixel::getSepia);

    return pixelCommand;
  }
}
