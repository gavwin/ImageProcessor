package utilities.commands;

import java.io.FileNotFoundException;

import controller.IController;
import utilities.ComponentType;

/**
 * A type of ImageCommand that has a controller change all r g b values to a specified ComponentType
 * in order to visualize that component.
 */
public class VisualizeComponent implements ImageCommand {
  ComponentType type;
  String imageName;
  String newName;

  /**
   * Initializes relevant parameters for the controller to execute the
   * visualize component functionality.
   *
   * @param type      (ComponentType) representing the component to be visualized from the image
   * @param imageName (String) name of the image to load and visualize component of
   * @param newName   (String) reference name of the new visualized image
   */
  public VisualizeComponent(ComponentType type, String imageName, String newName) {
    this.type = type;
    this.imageName = imageName;
    this.newName = newName;
  }

  @Override
  public void execute(IController controller) throws FileNotFoundException {
    controller.visualizeComponent(this.type, this.imageName, this.newName);
  }

  /**
   * Returns the description of the command.
   * @return String containing the description of the command.
   */
  public static String description(String component) {
    return component + " image-name new-name\n"
            + "\t\tImage command for visualizing the " + component + " of given image and\n"
            + "\t\tsaves images as new-name to access later.";
  }
}
