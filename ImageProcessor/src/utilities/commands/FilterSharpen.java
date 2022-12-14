package utilities.commands;

import utilities.Settings;

/**
 * A type of Filtering ImageCommand that creates a filter to sharpen the image slightly.
 */
public class FilterSharpen extends Filtering implements ImageCommand {

  /**
   * Initializes the necessary parameters for carrying out the sharpen functionality.
   *
   * @param imageName (String) name of the image to load and sharpen
   * @param newName   (String) reference name of the new sharpened image
   */
  public FilterSharpen(String imageName, String newName) {
    super(imageName, newName, Settings.SHARPEN_MATRIX);
  }

  /**
   * Returns the description of the command.
   *
   * @return String containing the description of the command.
   */
  public static String description() {
    return "sharpen image-name new-name\n"
            + "\t\tFilter type command that sharpen image slightly and "
            + " saves image as new-name for access later.";
  }
}
