package view;

import java.io.IOException;

import model.ImageModel;

/**
 * Interface that shows functionality offered by view for displaying
 * user view of the ImageProcessor.
 */
public interface ImageProcessorTextView {

  void renderImage(ImageModel image) throws IOException;

  /**
   * Renders a message to the user through provided data destination.
   *
   * @param message (String) of message for user to read.
   */
  void renderMessage(String message);
}
