package utilities.factory;

import model.ImageModel;
import model.PPMImageModel;
import utilities.pixels.IPixel;

/**
 * Type of ImageAbstractFactory relevant for .ppm type images.
 */
public class PPMImageFactory implements ImageAbstractFactory {
  String imagePath;

  /**
   * Initializes the imagePath for finding the PPM image to create a PPMImageModel.
   *
   * @param imagePath (String) representing the filepath of the relevant image
   */
  public PPMImageFactory(String imagePath) {
    this.imagePath = imagePath;
  }

  @Override
  public ImageModel createImage() {
    return new PPMImageModel(this.imagePath);
  }

  @Override
  public ImageModel createImageWithArray(IPixel[][] pixels) {
    return new PPMImageModel(pixels);
  }
}
