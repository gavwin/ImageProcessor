package utilities.factory;


import model.BMPImageModel;
import model.ImageModel;
import utilities.pixels.IPixel;

/**
 * Type of ImageAbstractFactory relevant for .bpm type images.
 */
public class BPMImageFactory implements ImageAbstractFactory {
  String imagePath;

  /**
   * Initializes the imagePath for finding the BPM image to create a BPMIMageModel.
   *
   * @param imagePath (String) representing the filepath of the relevant image
   */
  public BPMImageFactory(String imagePath) {
    this.imagePath = imagePath;
  }

  @Override
  public ImageModel createImage() {
    return new BMPImageModel(this.imagePath);
  }

  @Override
  public ImageModel createImageWithArray(IPixel[][] pixels) {
    return new BMPImageModel(pixels);
  }
}
