package utilities.factory;

import model.ImageModel;
import model.JPGImageModel;
import utilities.pixels.IPixel;

/**
 * Type of ImageAbstractFactory relevant for .jpg type images.
 */
public class JPGImageFactory implements ImageAbstractFactory {

  String imagePath;

  /**
   * Initializes the imagePath for finding the JPG image to create a JPGIMageModel.
   *
   * @param imagePath (String) representing the filepath of the relevant image
   */
  public JPGImageFactory(String imagePath) {
    this.imagePath = imagePath;
  }

  @Override
  public ImageModel createImage() {
    return new JPGImageModel(this.imagePath);
  }

  @Override
  public ImageModel createImageWithArray(IPixel[][] pixels) {
    return new JPGImageModel(pixels);
  }
}
