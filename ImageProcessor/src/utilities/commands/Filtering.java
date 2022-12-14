package utilities.commands;

import java.io.IOException;

import controller.IController;

/**
 * Abstract class of type ImageCommand to avoid duplication among different
 * filter types.
 */
abstract class Filtering implements ImageCommand {

  String imageName;
  String newName;
  double[][] filter;

  /**
   * Initializes the general parameters that all Filter type ImageCommands will need at a minimum.
   *
   * @param imageName (String) name of the image to load and filter
   * @param newName   (String) reference name of the new filtered image
   * @param filter (double[][]) of a square box filter for applying a weighted sum and
   *               setting the middle pixel equal to the weighted sum of relevant value
   */
  Filtering(String imageName, String newName, double[][] filter) {
    this.imageName = imageName;
    this.newName = newName;
    this.filter = filter;
  }

  @Override
  public void execute(IController controller) throws IOException {
    controller.filter(this.imageName, this.newName, this.filter);
  }
}
