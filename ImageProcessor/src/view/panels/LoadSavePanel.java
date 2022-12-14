package view.panels;

import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import controller.GUIController;

/**
 * Represents the panel containing the load and save buttons.
 */
public class LoadSavePanel extends JPanel implements ButtonPanel {
  private final JButton load;
  private final JButton save;

  /**
   * Default constructor.
   */
  public LoadSavePanel() {
    super();

    // set up the panel
    this.setLayout(new GridLayout(2, 1));
    this.setBorder(new EmptyBorder(0, 10, 0, 10));
    this.setBackground(Color.white);

    // set the buttons
    this.load = new JButton("Load Image");
    this.save = new JButton("Save Image");

    // add the buttons to the panel
    this.add(load);
    this.add(save);
  }

  @Override
  public void linkCommands(GUIController controller) {
    this.load.addActionListener(event -> controller.loadImage());
    this.save.addActionListener(event -> controller.saveImage());
  }
}
