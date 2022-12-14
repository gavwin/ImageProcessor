package utilities;

/**
 * Global settings for the program.
 */
public interface Settings {
  static final double[][] BLUR_MATRIX = new double[][]{
          {0.0625, 0.125, 0.0625},
          {0.125, 0.25, 0.125},
          {0.0625, 0.125, 0.0625}
  };

  static final double[][] SHARPEN_MATRIX = new double[][]{
          {-0.125, -0.125, -0.125, -0.125, -0.125},
          {-0.125, 0.25, 0.25, 0.25, -0.125},
          {-0.125, 0.25, 1.0, 0.25, -0.125},
          {-0.125, 0.25, 0.25, 0.25, -0.125},
          {-0.125, -0.125, -0.125, -0.125, -0.125}
  };

  static final int HISTOGRAM_WIDTH = 256;
  static final int HISTOGRAM_HEIGHT = 200;

  static final int NUM_COMMANDS = 14;

  static final int RIGHT_PANEL_BORDER_SIZE = 10;
}
