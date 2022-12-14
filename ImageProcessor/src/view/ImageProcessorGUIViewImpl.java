package view;

import java.awt.Rectangle;
import java.awt.GraphicsEnvironment;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.BorderLayout;

import java.io.File;
import java.util.concurrent.CancellationException;
import java.util.function.Function;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.ScrollPaneConstants;
import javax.swing.JViewport;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import controller.GUIController;
import model.ImageModel;
import utilities.Settings;
import view.panels.HistogramPanel;
import view.panels.ImagePanel;
import view.panels.LoadSavePanel;
import view.panels.CommandPanel;

/**
 * View for the GUI-based version of the program.
 */
public class ImageProcessorGUIViewImpl extends JFrame implements ImageProcessorGUIView {
  private final HistogramPanel histogramPanel;
  private final CommandPanel commandPanel;
  private final LoadSavePanel loadSavePanel;
  private final ImagePanel imagePanel;
  private ImageModel model;

  /**
   * Default constructor which will run the GUI-based view.
   */
  public ImageProcessorGUIViewImpl() {
    this.model = null;

    // set the size of the window
    Rectangle bounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
    Dimension d = new Dimension(bounds.width, bounds.height);

    // right side
    JPanel side = new JPanel();
    side.setPreferredSize(new Dimension(d.width * 2 / 10, d.height));
    side.setLayout(new GridLayout(3, 0, 0, 5));
    side.setBorder(new EmptyBorder(0, 10, 0, 0));

    // load and save buttons
    this.loadSavePanel = new LoadSavePanel();
    side.add(this.loadSavePanel);

    // command buttons
    this.commandPanel = new CommandPanel();
    JScrollPane commandScrollbar = new JScrollPane(this.commandPanel);
    commandScrollbar.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    commandScrollbar.setBorder(null);
    side.add(commandScrollbar);

    // histogram
    this.histogramPanel = new HistogramPanel();
    histogramPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    histogramPanel.setPreferredSize(new Dimension(Settings.HISTOGRAM_WIDTH,
            Settings.HISTOGRAM_HEIGHT));
    JScrollPane histogramScrollbar = new JScrollPane(this.histogramPanel);
    histogramScrollbar.setBorder(null);
    side.add(histogramScrollbar);

    // add the side panel to the layout
    this.add(side, BorderLayout.WEST);

    // image
    this.imagePanel = new ImagePanel(d);
    JScrollPane imageScrollbar = new JScrollPane(this.imagePanel);
    imageScrollbar.setPreferredSize(new Dimension(d.width * 8 / 10, d.height));
    imageScrollbar.setBorder(null);
    imageScrollbar.getViewport().setScrollMode(JViewport.BACKINGSTORE_SCROLL_MODE);
    imageScrollbar.getVerticalScrollBar().setUnitIncrement(16);
    imageScrollbar.getHorizontalScrollBar().setUnitIncrement(16);
    this.add(imageScrollbar, BorderLayout.EAST);

    // finishing touches
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();
    this.setFocusable(true);
    this.setVisible(true);
  }

  @Override
  public void reload() {
    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    this.requestFocus();
    this.repaint();
  }

  @Override
  public void renderImage(ImageModel image) {
    this.model = image;
    this.imagePanel.setImage(this.model);
    //this.histogramPanel.setImage(image);
    this.reload();
  }

  @Override
  public void linkCommands(GUIController controller) {
    this.commandPanel.linkCommands(controller);
    this.loadSavePanel.linkCommands(controller);
  }

  @Override
  public String filePrompt(Function<JFileChooser, Integer> destination)
          throws CancellationException {
    File file;
    JFileChooser fileChooser = new JFileChooser(".");
    int value = destination.apply(fileChooser);

    if (value == JFileChooser.APPROVE_OPTION) {
      file = fileChooser.getSelectedFile();
    } else {
      throw new CancellationException("User did not select a file path.");
    }

    return file.getAbsolutePath();
  }

  @Override
  public int promptUserInput(String message) throws CancellationException {
    int input;
    while (true) {
      String prompt = JOptionPane.showInputDialog(message);
      if (prompt == null) {
        throw new CancellationException("The user closed the input prompt.");
      }
      try {
        input = Integer.parseInt(prompt);
        break;
      } catch (NumberFormatException e) {
        this.renderMessage("");
        JOptionPane.showMessageDialog(this, "Your input must be an integer.");
      }
    }

    return input;
  }

  @Override
  public void renderMessage(String message) {
    JOptionPane.showMessageDialog(this, message);
  }

  private void test() {
    JPanel panel = this.histogramPanel;
  }
}