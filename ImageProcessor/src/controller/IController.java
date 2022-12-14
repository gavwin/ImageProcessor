package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import utilities.ComponentType;

/**
 * Interface for creating a controller that handles all loaded images and makes necessary
 * changes/saves based on user input.
 */
public interface IController {

  /**
   * Loads an image from a specified path and saves the ImageModel instance into hashmap and the
   * key is the passed image name argument.
   *
   * @param imagePath (String) representing the file path of the image to be loaded
   * @param imageName (String) representing the reference name of the image henceforth
   * @throws FileNotFoundException if the file cannot be found
   */
  void loadImage(String imagePath, String imageName) throws FileNotFoundException;

  /**
   * Saves the references image to the given file path (which includes name of file).
   *
   * @param imagePath (String) representing the file path to save the image to
   * @param imageName (String) representing the reference name of the image to save
   * @throws IOException if the file cannot be found
   */
  void saveImage(String imagePath, String imageName) throws IOException;

  /**
   * Brightens the referenced image by given increment to create a new image,
   * referred to henceforth by given destination name.
   *
   * @param increment (int) the amount to increment the rgb values by
   *                  positive ints yield a brighter image
   *                  negative ints yield a darker image
   * @param imageName (String) the name of the image
   * @param newName   (String) the new name to set for the modified image
   */
  void brighten(int increment, String imageName, String newName);

  /**
   * Flips the referenced image horizontally to create a new image,
   * referred to henceforth by given destination name.
   *
   * @param imageName (String) the name of the image
   * @param newName   (String) the new name to set for the modified image
   */
  void flipHorizontally(String imageName, String newName);

  /**
   * Flips the referenced image horizontally to create a new image,
   * referred to henceforth by given destination name.
   *
   * @param imageName (String) the name of the image
   * @param newName   (String) the new name to set for the modified image
   */
  void flipVertically(String imageName, String newName);

  /**
   * Sets the value of all pixels to the given Component.
   *
   * @param type      (ComponentType) of value to set all r g b values to
   * @param imageName (String) the name of the image
   * @param newName   (String) the new name to set for the modified image
   */
  void visualizeComponent(ComponentType type, String imageName,
                          String newName);

  /**
   * Applies given filter to all pixels. Filters are applied to each channel of each pixel
   * by preforming a weighted sum, centering the filter on the relevant pixel and then preforming
   * the summation.
   *
   * @param imageName (String) the name of the image
   * @param newName   (String) the new name to set for the modified image
   * @param filter    (double[][]) represeting the matrix form of the filter
   */
  void filter(String imageName, String newName, double[][] filter);

  /**
   * Displays a list of all supported commands and a description to view. Description
   * explains format for using command and brief explanation of
   * what the functionality accomplishes.
   */
  void menu();

  /**
   * Handles command line user inputs and carries out desired functionality.
   */
  void handleInput() throws IOException;
}
