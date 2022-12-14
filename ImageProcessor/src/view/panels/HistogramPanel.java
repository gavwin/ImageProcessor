package view.panels;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Dimension;

import javax.swing.JPanel;

import model.ImageModel;
import utilities.ComponentType;
import utilities.Settings;
import utilities.histogram.Histogram;

/**
 * Represents the JPanel for displaying the histogram.
 */
public class HistogramPanel extends JPanel {
  ImageModel model;
  Graphics2D g2d;

  /**
   * Default constructor.
   */
  public HistogramPanel() {
    super();

    this.model = null;

    this.setLayout(new BorderLayout());
    this.setBackground(Color.white);
    this.setMinimumSize(new Dimension(Settings.HISTOGRAM_WIDTH, Settings.HISTOGRAM_HEIGHT));
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.g2d = (Graphics2D) g;
    this.g2d.drawRect(0, 0, Settings.HISTOGRAM_WIDTH, Settings.HISTOGRAM_HEIGHT);
    this.renderHistogram();
  }

  /**
   * Setter for this.model.
   * @param image an image model to set to
   */
  public void setImage(ImageModel image) {
    this.model = image;
  }

  private void renderHistogram() {
    if (this.model != null) {
      Histogram hist = new Histogram();
      int[][] plotsToDraw = new int[3][256];
      plotsToDraw[0] = hist.getComponentDistribution(ComponentType.Red, this.model);
      plotsToDraw[1] = hist.getComponentDistribution(ComponentType.Blue, this.model);
      plotsToDraw[2] = hist.getComponentDistribution(ComponentType.Green, this.model);
      for (int[] distribution : plotsToDraw) {
        int x = 0;
        int increment = 256 / Settings.HISTOGRAM_WIDTH;
        for (int frequency : distribution) {
          this.g2d.drawOval(x, Settings.HISTOGRAM_HEIGHT - frequency, 1, 1);
          x += increment;
        }
      }
    }
  }
}
