package utilities.factory;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import model.ImageModel;
import utilities.pixels.IPixel;

/**
 * Interface for creating correct ImageModel given a command.
 */
public interface ImageAbstractFactory {

  /**
   * Method that will create the correct type of image model.
   *
   * @return (ImageModel) creates relevant implementation type of ImageModel
   */
  ImageModel createImage() throws FileNotFoundException;

  /**
   * Method that will create the correct type of image model.
   * @param pixels (Pixel[][]) representing the pixels of the image
   *
   * @return (ImageModel) creates relevant implementation type of ImageModel
   */
  ImageModel createImageWithArray(IPixel[][] pixels) throws FileNotFoundException;

  /**
   * Factory method for creating the correct type of ImageModel based on the image path extension.
   *
   * @param command   (String) representing
   * @param imagePath (String) representing the file path of the image
   * @return (ImageAbstractFactory) that will initialize the correct type of image model.
   */
  static ImageAbstractFactory getFactory(String command, String imagePath) {
    Map<String, Function<String, ImageAbstractFactory>> models = new HashMap<>();
    models.put(".ppm", PPMImageFactory::new);
    models.put(".jpg", JPGImageFactory::new);
    models.put(".jpeg", JPGImageFactory::new);
    models.put(".png", PNGImageFactory::new);
    models.put(".bmp", BPMImageFactory::new);

    Function<String, ImageAbstractFactory> factoryFunction = models.get(command);

    return factoryFunction.apply(imagePath);
  }
}
