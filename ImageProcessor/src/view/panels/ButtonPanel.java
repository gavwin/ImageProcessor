package view.panels;

import controller.GUIController;

/**
 * Interface for JPanels that contain one or more JButtons.
 */
public interface ButtonPanel {
  /**
   * Links the JButtons to their respective methods in the Controller.
   *
   * @param controller the controller with the correct command methods
   */
  public void linkCommands(GUIController controller);
}
