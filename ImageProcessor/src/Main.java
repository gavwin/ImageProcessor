import java.io.FileNotFoundException;
import java.io.IOException;

import controller.ControllerFileInput;
import controller.ControllerSystemInput;
import controller.GUIController;
import controller.IController;
import view.ImageProcessorGUIView;
import view.ImageProcessorGUIViewImpl;
import view.ImageProcessorTextView;
import view.ImageProcessorTextViewImpl;

/**
 * Class for running the program.
 */
public class Main {

  /**
   * Main method to call the Controller to run the program.
   * @param args the string arguments that are taken in as commands
   * @throws IOException if the file cannot be found
   */
  public static void main(String[] args) throws IOException {
    IController controller;

    if (args.length > 0) {
      ImageProcessorTextView view = new ImageProcessorTextViewImpl();
      try {
        controller = new ControllerFileInput(view, args[0]);
      } catch (FileNotFoundException e) {
        view.renderMessage("Invalid file given, defaulting to console.");
        controller = new ControllerSystemInput(view);
      }
    } else {
      ImageProcessorGUIView view = new ImageProcessorGUIViewImpl();
      controller = new GUIController(view);
    }

    controller.handleInput();
  }
}
