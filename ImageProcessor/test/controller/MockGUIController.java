package controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;

import javax.swing.JPopupMenu;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.ImageModel;
import utilities.ComponentType;
import utilities.factory.ImageAbstractFactory;
import view.ImageProcessorGUIView;

/**
 * Represents a mock GUIController for testing purposes.
 */
public class MockGUIController extends AController implements IController {
  protected Map<String, ImageModel> images;
  protected ImageProcessorGUIView view;
  private String current;

  /**
   * Default constructor for initializing the GUI-based controller.
   *
   * @param view the GUI view to use
   */
  public MockGUIController(ImageProcessorGUIView view) {
    super();
    this.images = new HashMap<>();
    this.view = view;
    this.current = "";
    this.view.reload();
  }

  /**
   * Brightens the referenced image by given increment to create a new image,
   * referred to henceforth by given destination name.
   */
  public void brighten() {
    ImageModel image = this.getImage(this.current);
    int increment;
    try {
      String promptMessage = "Enter a positive number to brighten or a negative number to darken.";
      increment = this.view.promptUserInput(promptMessage);
    } catch (CancellationException e) {
      return;
    }
    ImageModel newImage = image.brighten(increment);
    this.current += " brighten";
    this.images.put(this.current, newImage);
    this.view.renderMessage("brighten successful");
    this.view.renderImage(this.getImage(this.current));
  }

  /**
   * Flips the image horizontally.
   */
  public void flipHorizontally() {
    ImageModel image = this.getImage(this.current);
    ImageModel newImage = image.flipHorizontally();
    this.current += " flip-horizontal";
    this.images.put(this.current, newImage);
    this.view.renderMessage("flip-horizontal successful");
    this.view.renderImage(this.getImage(this.current));
  }

  /**
   * Flips the image vertically.
   */
  public void flipVertically() {
    ImageModel image = this.getImage(this.current);
    ImageModel newImage = image.flipVertically();
    this.current += " flip-vertical";
    this.images.put(this.current, newImage);
    this.view.renderMessage("flip-vertical successful");
    this.view.renderImage(this.getImage(this.current));
  }

  /**
   * Visualizes the provided ComponentType.
   * @param type the ComponentType to visualize
   */
  public void visualizeComponent(ComponentType type) {
    ImageModel image = this.getImage(this.current);
    System.out.println(this.current);
    ImageModel newImage = image.visualizeComponents(type);
    this.current += " visualize";
    this.images.put(this.current, newImage);
    this.view.renderMessage("visualizing successful");
    this.view.renderImage(this.getImage(this.current));
  }

  /**
   * Applies a filter to the image.
   * @param filter the filter matrix (represented by a double[][]) to use as a filter
   */
  public void filter(double[][] filter) {
    ImageModel image = this.getImage(this.current);
    ImageModel newImage = image.filter(filter);
    this.current += " filter";
    this.images.put(this.current, newImage);
    this.view.renderMessage("filter successful");
    this.view.renderImage(this.getImage(this.current));
  }

  /**
   * Loads an image from a specified path and saves the ImageModel instance into hashmap and the
   * key is the passed image name argument.
   */
  public String loadImage() {
    String imagePath;
    try {
      imagePath = this.view.filePrompt(fileChooser -> {
        FileNameExtensionFilter fileFilter = new FileNameExtensionFilter(
                "JPG, PNG, BMP, & PPM images",
                "jpg", "jpeg", "png", "bmp", "ppm");
        fileChooser.setFileFilter(fileFilter);
        return fileChooser.showOpenDialog(new JPopupMenu());
      });
    } catch (CancellationException e) {
      imagePath = "";
      return imagePath;
    }

    try {
      String fileExtension = imagePath.substring(imagePath.lastIndexOf("."));
      ImageAbstractFactory factory = ImageAbstractFactory.getFactory(fileExtension, imagePath);
      this.current = "";
      this.images.put(this.current, factory.createImage());
      this.view.renderMessage("load successful");
      this.view.renderImage(this.images.get(this.current));
    } catch (FileNotFoundException e) {
      this.view.renderMessage("File " + imagePath + " not found!");
    }

    return imagePath;
  }

  /**
   * Saves the references image to the given file path (which includes name of file).
   */
  public void saveImage() {
    try {
      ImageModel image = this.getImage(this.current);
      String imagePath = this.view.filePrompt(fileChooser -> {
        return fileChooser.showSaveDialog(new JPopupMenu());
      });

      String fileExtension = imagePath.substring(imagePath.lastIndexOf("."));
      ImageAbstractFactory factory = ImageAbstractFactory.getFactory(fileExtension, imagePath);
      ImageModel newImage = factory.createImageWithArray(image.getPixelArray());

      byte[] fileBytes = newImage.writeImage();
      FileOutputStream file = new FileOutputStream(imagePath);
      file.write(fileBytes);
      file.close();

      this.view.renderMessage("save successful");
    } catch (IllegalArgumentException | CancellationException e) {
      return;
    } catch (FileNotFoundException e) {
      this.view.renderMessage("Could not find the file.");
    } catch (IOException e) {
      this.view.renderMessage("Failed to save image.");
      return;
    }

    this.view.reload();
  }

  @Override
  protected ImageModel getImage(String imageName) {
    ImageModel image = this.images.getOrDefault(imageName, null);
    if (image == null) {
      throw new IllegalArgumentException(imageName + " not found");
    }

    return image;
  }

  @Override
  public void handleInput() throws IOException {
    // don't need to do anything here since the input is handled through lambdas for this view
  }
}
