package utilities.commands;

import java.io.FileNotFoundException;

import controller.IController;

/**
 * A type of ImageCommand that has a controller create and save an equivalent image
 * except flipped vertically.
 */
public class FlipVertical implements ImageCommand {

  String imageName;
  String newName;

  /**
   * Initializes the necessary parameters for carrying out the flip-vertically functionality.
   *
   * @param imageName (String) name of the image to load and flip
   * @param newName   (String) reference name of the new flipped image
   */
  public FlipVertical(String imageName, String newName) {
    this.imageName = imageName;
    this.newName = newName;
  }

  @Override
  public void execute(IController controller) throws FileNotFoundException {
    controller.flipVertically(this.imageName, this.newName);
  }

  /**
   * Returns the description of the command.
   * @return String containing the description of the command.
   */
  public static String description() {
    return "flip-vertical image-name new-name\n"
            + "\t\tImage command for flipping an image vertically and\n"
            + "\t\tsaves the image as new-name for access later.";
  }
}
