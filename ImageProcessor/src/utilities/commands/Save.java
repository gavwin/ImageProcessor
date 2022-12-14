package utilities.commands;

import java.io.IOException;

import controller.IController;

/**
 * A type of ImageCommand that has a controller save a referenced image to relevant file path.
 */
public class Save implements ImageCommand {
  String imagePath;
  String imageName;

  /**
   * Initializes the necessary parameters for carrying out the load functionality.
   *
   * @param imagePath (String) representing the file path to save the image to
   * @param imageName (String) representing the reference name of the image to be saved
   */
  public Save(String imagePath, String imageName) {
    this.imagePath = imagePath;
    this.imageName = imageName;
  }

  @Override
  public void execute(IController controller) throws IOException {
    controller.saveImage(this.imagePath, this.imageName);
  }

  /**
   * Returns the description of the command.
   * @return String containing the description of the command.
   */
  public static String description() {
    return "save image-path image-name\n"
            + "\t\tAccess passed image and save to computer at image-path";
  }
}
