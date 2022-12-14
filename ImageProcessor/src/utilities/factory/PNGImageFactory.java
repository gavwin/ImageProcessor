package utilities.factory;

import model.ImageModel;
import model.PNGImageModel;
import utilities.pixels.IPixel;

/**
 * Type of ImageAbstractFactory relevant for .png type images.
 */
public class PNGImageFactory implements ImageAbstractFactory {

  String imagePath;

  /**
   * Initializes the imagePath for finding the PNG image to create a PNGIMageModel.
   *
   * @param imagePath (String) representing the filepath of the relevant image
   */
  public PNGImageFactory(String imagePath) {
    this.imagePath = imagePath;
  }

  @Override
  public ImageModel createImage() {
    return new PNGImageModel(this.imagePath);
  }

  @Override
  public ImageModel createImageWithArray(IPixel[][] pixels) {
    return new PNGImageModel(pixels);
  }
}
