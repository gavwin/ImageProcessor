package view;

import java.util.concurrent.CancellationException;
import java.util.function.Function;

import javax.swing.JFileChooser;

import controller.GUIController;
import model.ImageModel;

/**
 * Interface that shows functionality offered by view for displaying
 * user view of the ImageProcessor.
 */
public interface ImageProcessorGUIView {

  /**
   * Updates the image to be the current version.
   * @param image the current version of the image
   */
  void renderImage(ImageModel image);

  /**
   * Renders a message to the user through provided data destination.
   * @param message (String) of message for user to read.
   */
  void renderMessage(String message);

  /**
   * Tells the various ButtonPanels to link their function objects to the correct controller for
   * handling user input.
   * @param controller the GUI-based controller containing the image command methods
   */
  void linkCommands(GUIController controller);

  /**
   * Reloads the components on the screen so that when a command is executed on an image, the
   * render of the image updates.
   */
  void reload();

  /**
   * Prompts the user to choose a file.
   * @param destination the file destination to load from or save to
   * @return the file path as a String
   */
  String filePrompt(Function<JFileChooser, Integer> destination) throws CancellationException;

  /**
   * Prompts the user to enter an integer with the provided message.
   * @param message the message to use in the input prompt
   * @return the user's input
   */
  int promptUserInput(String message) throws CancellationException;
}
