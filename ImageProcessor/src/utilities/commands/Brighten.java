package utilities.commands;

import java.io.FileNotFoundException;

import controller.IController;

/**
 * A type of ImageCommand that has a controller change all r g b values
 * by a given increment for a specific image, and saves the changed image as given name.
 */
public class Brighten implements ImageCommand {

  int increment;
  String imageName;
  String newName;

  /**
   * Initializes the necessary parameters for carrying out the brighten functionality.
   *
   * @param increment (int) amount to change all r g b values by. Positive to brighten,
   *                  negative to darken
   * @param imageName (String) name of the image to load and brighten
   * @param newName   (String) reference name of the new brightened image
   */
  public Brighten(int increment, String imageName, String newName) {
    this.increment = increment;
    this.imageName = imageName;
    this.newName = newName;
  }

  @Override
  public void execute(IController controller) throws FileNotFoundException {
    controller.brighten(this.increment, this.imageName, this.newName);
  }

  /**
   * Returns the description of the command.
   * @return String containing the description of the command.
   */
  public static String description() {
    return "brighten increment image-name new-name\n"
            + "\t\tImage command for changing all r g b values by given increment and "
            + "saves the image as new-name to access later";
  }
}
