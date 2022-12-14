package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;

import utilities.commands.ImageCommand;
import view.ImageProcessorTextView;

/**
 * Implementation of controller that utilizes user input to
 * access and make relevant changes/saves to correct instance of ImageModel.
 */
public class ControllerFileInput extends AController implements IController {
  private final String[] fileRepresentation;

  /**
   * Default constructor that initializes the image HashMap to blank.
   */
  public ControllerFileInput(ImageProcessorTextView view, String filepath)
          throws FileNotFoundException {
    super(view);
    this.fileRepresentation = this.loadTextFile(filepath);
  }


  @Override
  public void handleInput() {
    for (String commandLine : this.fileRepresentation) {
      String[] commandFormated = commandLine.split(" ");
      String commandName = commandFormated[0];
      String[] inputs;
      Function<String[], ImageCommand> command = this.commands.getOrDefault(
              commandName, null);
      if (commandName.equalsIgnoreCase("menu")) {
        inputs = new String[0];
      } else {
        inputs = Arrays.copyOfRange(commandFormated, 1, commandFormated.length);
      }
      this.handleCommand(command, inputs);
    }
    this.view.renderMessage("Thanks for using our application!");
  }

  private String[] loadTextFile(String filepath) throws FileNotFoundException {
    Scanner sc;
    String s = "";

    sc = new Scanner(new FileInputStream(filepath));
    while (sc.hasNextLine()) {
      s += sc.nextLine() + "\n";
    }
    return s.split("\n");
  }
}
