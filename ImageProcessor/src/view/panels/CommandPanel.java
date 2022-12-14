package view.panels;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import controller.GUIController;
import utilities.ComponentType;
import utilities.Settings;

/**
 * Represents the panel containing buttons for choosing a command to apply to the image.
 */
public class CommandPanel extends JPanel implements ButtonPanel {
  private final JButton visualizeRed;
  private final JButton visualizeBlue;
  private final JButton visualizeGreen;
  private final JButton value;
  private final JButton luma;
  private final JButton intensity;
  private final JButton greyscale;
  private final JButton sepia;
  private final JButton blur;
  private final JButton sharpen;
  private final JButton brighten;
  private final JButton flipHorizontally;
  private final JButton flipVertically;

  /**
   * Default constructor.
   */
  public CommandPanel() {
    super();

    // set up the panel
    this.setLayout(new GridLayout(Settings.NUM_COMMANDS, 0));
    this.setBorder(new EmptyBorder(0, Settings.RIGHT_PANEL_BORDER_SIZE, 0,
            Settings.RIGHT_PANEL_BORDER_SIZE));
    this.setBackground(Color.white);

    // set the buttons
    this.visualizeRed = new JButton("Visualize Red Component");
    this.visualizeGreen = new JButton("Visualize Green Component");
    this.visualizeBlue = new JButton("Visualize Blue Component");
    this.value = new JButton("Visualize Value Component");
    this.luma = new JButton("Visualize Luma Component");
    this.intensity = new JButton("Visualize Intensity Component");
    this.greyscale = new JButton("Greyscale");
    this.sepia = new JButton("Sepia");
    this.blur = new JButton("Blur");
    this.sharpen = new JButton("Sharpen");
    this.brighten = new JButton("Brighten/Darken");
    this.flipHorizontally = new JButton("Flip Horizontally");
    this.flipVertically = new JButton("Flip Vertically");

    // add the buttons to the panel
    this.add(this.visualizeRed);
    this.add(this.visualizeGreen);
    this.add(this.visualizeBlue);
    this.add(this.value);
    this.add(this.luma);
    this.add(this.intensity);
    this.add(this.greyscale);
    this.add(this.sepia);
    this.add(this.blur);
    this.add(this.sharpen);
    this.add(this.brighten);
    this.add(this.flipHorizontally);
    this.add(this.flipVertically);
  }

  @Override
  public void linkCommands(GUIController controller) {
    this.visualizeRed.addActionListener(event -> controller.visualizeComponent(ComponentType.Red));
    this.visualizeGreen.addActionListener(event ->
            controller.visualizeComponent(ComponentType.Green));
    this.visualizeBlue.addActionListener(event ->
            controller.visualizeComponent(ComponentType.Blue));
    this.value.addActionListener(event -> controller.visualizeComponent(ComponentType.Value));
    this.luma.addActionListener(event -> controller.visualizeComponent(ComponentType.Luma));
    this.intensity.addActionListener(event ->
            controller.visualizeComponent(ComponentType.Intensity));
    this.sepia.addActionListener(event -> controller.visualizeComponent(ComponentType.Sepia));
    this.greyscale.addActionListener(event -> controller.visualizeComponent(ComponentType.Luma));
    this.blur.addActionListener(event -> controller.filter(Settings.BLUR_MATRIX));
    this.sharpen.addActionListener(event -> controller.filter(Settings.SHARPEN_MATRIX));
    this.brighten.addActionListener(event -> controller.brighten());
    this.flipHorizontally.addActionListener(event -> controller.flipHorizontally());
    this.flipVertically.addActionListener(event -> controller.flipVertically());
  }
}
