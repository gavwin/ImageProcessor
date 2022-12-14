package view;

import java.io.IOException;
import java.util.Objects;

import model.ImageModel;

/**
 * Implementation of the view for text-based versions of the program.
 */
public class ImageProcessorTextViewImpl implements ImageProcessorTextView {
  private Appendable appendable;

  public ImageProcessorTextViewImpl(Appendable appendable) throws NullPointerException {
    this.appendable = Objects.requireNonNull(appendable);
  }

  public ImageProcessorTextViewImpl() {
    this(System.out);
  }

  @Override
  public void renderImage(ImageModel image) throws IOException {
    /* this method doesn't need to do anything in this implementation since there is no image
       to render.
     */
    return;
  }

  @Override
  public void renderMessage(String message) {
    try {
      this.appendable.append(message + "\n");
    } catch (IOException e) {
      throw new RuntimeException("Failed to render message to appendable within view.");
    }
  }
}
