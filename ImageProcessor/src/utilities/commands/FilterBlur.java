package utilities.commands;

import utilities.Settings;

/**
 * A type of Filtering ImageCommand that creates a filter to blur the image slightly.
 */
public class FilterBlur extends Filtering implements ImageCommand {

  /**
   * Initializes the necessary parameters for carrying out the blur functionality.
   *
   * @param imageName (String) name of the image to load and blur
   * @param newName   (String) reference name of the new blurred image
   */
  public FilterBlur(String imageName, String newName) {
    super(imageName, newName, Settings.BLUR_MATRIX);
  }

  /**
   * Returns the description of the command.
   * @return String containing the description of the command.
   */
  public static String description() {
    return "blur image-name new-name\n"
            + "\t\tFilter type command that blurs image slightly and "
            + "saves the image as new-name to access later.";
  }
}
