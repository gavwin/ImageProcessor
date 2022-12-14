package view.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.ImageModel;

/**
 * Class represents the visible panel of the image processor.
 */
public class ImagePanel extends JPanel {
  private ImageModel model;
  private BufferedImage image;
  private Dimension dimension;

  /**
   * Default constructor.
   *
   * @param dimension the dimensions to build the image panel
   * @throws NullPointerException if the provided dimension object is null
   */
  public ImagePanel(Dimension dimension) throws NullPointerException {
    super();
    this.dimension = Objects.requireNonNull(dimension);
    this.model = null;

    this.setBackground(Color.white);
    this.dimension = new Dimension(this.dimension.width * 8 / 10, this.dimension.height);
    this.setPreferredSize(this.dimension);
    this.renderImage();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.renderImage();
    int[] pos = this.centerImage();
    g.drawImage(this.image, pos[0], pos[1], Color.WHITE, this);
  }

  /**
   * Centers the image so that it fits onto the JPanel correctly.
   *
   * @return the x and y coordinate to place the image (starting from the top-left)
   */
  private int[] centerImage() {
    int[] pos = new int[2];
    int imageWidth = 10;
    int imageHeight = 10;
    try {
      imageWidth = this.image.getWidth();
      imageHeight = this.image.getHeight();
      int panelWidth = this.getWidth();
      int panelHeight = this.getHeight();

      if (imageWidth < panelWidth) {
        pos[0] = (panelWidth / 2) - (imageWidth / 2);
      }
      if (imageHeight < panelHeight) {
        pos[1] = (panelHeight / 2) - (imageHeight / 2);
      }
    } catch (NullPointerException e) {
      // do nothing, no image loaded yet
    }

    return pos;
  }

  /**
   * Converts the byte[] representation of the image from the ImageModel to a BufferedImage.
   */
  private void renderImage() {
    try {
      int width = this.model.getWidth();
      int height = this.model.getHeight();

      byte[] byteArray = this.model.writeImage();
      InputStream in = new ByteArrayInputStream(byteArray);
      this.image = ImageIO.read(in);

      this.setPreferredSize(new Dimension(width, height));
      this.revalidate();
    } catch (NullPointerException | IOException e) {
      // do nothing, no image loaded yet
    }
  }

  /**
   * Setter for this.model.
   *
   * @param model the image model to set to
   */
  public void setImage(ImageModel model) {
    this.model = model;
  }

  /**
   * Getter for this.dimension.
   * @return the dimensions of this panel
   */
  public Dimension getDimension() {
    return this.dimension;
  }
}
