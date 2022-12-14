package utilities.commands;

import java.io.IOException;

import controller.IController;

/**
 * An image command for displaying all supported image commands to user.
 */
public class Menu implements ImageCommand {

  @Override
  public void execute(IController controller) throws IOException {
    controller.menu();
  }

  /**
   * Returns the description of the command.
   * @return String containing the description of the command.
   */
  public static String description() {
    return "menu (enter)\n"
            + "\t\tImage command for displaying all supported commands "
            + "hit enter if asked for inputs.";
  }
}
