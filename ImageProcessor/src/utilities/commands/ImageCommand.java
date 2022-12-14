package utilities.commands;

import java.io.IOException;

import controller.IController;

/**
 * Interface representing a command type that will execute a piece of
 * functionality in model.
 */
public interface ImageCommand {

  /**
   * Executes a command (aka functionality) through the passed controller.
   *
   * @param controller (IController) to execute the functionality through
   */
  void execute(IController controller) throws IOException;
}
