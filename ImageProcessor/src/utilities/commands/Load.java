package utilities.commands;

import java.io.FileNotFoundException;

import controller.IController;


/**
 * A type of ImageCommand that has a controller find and store an image,
 * accessible by passed reference name.
 */
public class Load implements ImageCommand {

  String imagePath;
  String imageName;

  /**
   * Initializes the necessary parameters for carrying out the load functionality. If imageName
   * has already been used, function will load the new image and replace the old image.
   *
   * @param imagePath (String) representing the file path location of the image to be loaded
   * @param imageName (String) representing the reference name of the image henceforth
   */
  public Load(String imagePath, String imageName) {
    this.imagePath = imagePath;
    this.imageName = imageName;
  }

  @Override
  public void execute(IController controller) throws FileNotFoundException {
    controller.loadImage(this.imagePath, this.imageName);
  }

  /**
   * Returns the description of the command.
   * @return String containing the description of the command.
   */
  public static String description() {
    return "load image-path image-name\n"
            + "\t\tImage command for loading an image and saving as "
            + "image-name to access later.";
  }
}
