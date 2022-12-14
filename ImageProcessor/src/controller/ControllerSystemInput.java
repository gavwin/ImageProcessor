package controller;

import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.function.Function;

import utilities.commands.ImageCommand;
import view.ImageProcessorTextView;

/**
 * Implementation of a controller that utilizes system input to
 * access and make relevant save/changes to correct instance of ImageModel.
 */
public class ControllerSystemInput extends AController implements IController {

  private final Readable input;

  /**
   * Default constructor that initializes the image HashMap to blank.
   */
  public ControllerSystemInput(ImageProcessorTextView view) {
    super(view);
    this.input = new InputStreamReader(System.in);
  }

  public ControllerSystemInput(ImageProcessorTextView view, Readable input) {
    super(view);
    this.input = input;
  }


  @Override
  public void handleInput() {
    Scanner sc = new Scanner(this.input);

    while (true) {
      this.view.renderMessage("Type a command: ");
      String input = sc.nextLine();
      if (input.equalsIgnoreCase("q")) {
        this.view.renderMessage("Thanks for using our application!");
        break;
      } else {
        Function<String[], ImageCommand> command = this.commands.getOrDefault(
                input, null);
        this.view.renderMessage("Type all inputs separated by a space: ");
        String[] inputs = sc.nextLine().split(" ");
        this.handleCommand(command, inputs);
      }
    }
    sc.close();
  }
}
