package utilities.commands;

import java.io.FileNotFoundException;

import controller.IController;

/**
 * A type of ImageCommand that has a controller create and save an equivalent image
 * except flipped horizontally.
 */
public class FlipHorizontal implements ImageCommand {
  String imageName;
  String newName;

  /**
   * Initializes the necessary parameters for carrying out the flip-horizontally functionality.
   *
   * @param imageName (String) name of the image to load and flip horizontally
   * @param newName   (String) reference name of the new flipped image
   */
  public FlipHorizontal(String imageName, String newName) {
    this.imageName = imageName;
    this.newName = newName;
  }

  @Override
  public void execute(IController controller) throws FileNotFoundException {
    controller.flipHorizontally(this.imageName, this.newName);
  }

  /**
   * Returns the description of the command.
   * @return String containing the description of the command.
   */
  public static String description() {
    return "flip-horizontal image-name new-name\n"
            + "\t\tA image command for flipping an image horizontally and\n"
            + "\t\tsaves the image as new-name for access later.";
  }
}
