package controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import utilities.commands.Brighten;
import utilities.commands.FilterBlur;
import utilities.commands.FilterSharpen;
import utilities.commands.FlipHorizontal;
import utilities.commands.FlipVertical;
import utilities.commands.ImageCommand;
import utilities.commands.Load;
import utilities.commands.Menu;
import utilities.commands.Save;
import utilities.commands.VisualizeComponent;
import utilities.factory.ImageAbstractFactory;
import utilities.ComponentType;
import model.ImageModel;
import view.ImageProcessorTextView;

/**
 * Abstract Controller to handle all of the functionality of the Controller.
 */
public abstract class AController implements IController {

  protected Map<String, ImageModel> images;
  protected ImageProcessorTextView view;
  protected Map<String, Function<String[], ImageCommand>> commands;

  /**
   * Default constructor that initializes the image HashMap to blank.
   */
  public AController(ImageProcessorTextView view) {
    this.images = new HashMap<>();
    // key: image-reference-name --> value: relevant-instance-of-ImageModel
    this.initializeCommands();
    this.view = view;
  }

  /**
   * Constructor being used by the GUI-based version to bypass initializing the view to an
   * ImageProcessorTextView.
   */
  public AController() {
    this.images = new HashMap<>();
    // key: image-reference-name --> value: relevant-instance-of-ImageModel
    this.initializeCommands();
  }

  private void initializeCommands() {
    this.commands = new HashMap<>();
    this.commands.put("load", inputs -> new Load(inputs[0], inputs[1]));
    this.commands.put("save", inputs -> new Save(inputs[0], inputs[1]));

    this.commands.put("brighten", inputs -> new Brighten(
            Integer.parseInt(inputs[0]), inputs[1], inputs[2]));

    this.commands.put("red-component", inputs -> new VisualizeComponent(
            ComponentType.Red, inputs[0], inputs[1]));
    this.commands.put("green-component", inputs -> new VisualizeComponent(
            ComponentType.Green, inputs[0], inputs[1]));
    this.commands.put("blue-component", inputs -> new VisualizeComponent(
            ComponentType.Blue, inputs[0], inputs[1]));
    this.commands.put("value-component", inputs -> new VisualizeComponent(
            ComponentType.Value, inputs[0], inputs[1]));
    this.commands.put("intensity-component", inputs -> new VisualizeComponent(
            ComponentType.Intensity, inputs[0], inputs[1]));
    this.commands.put("luma-component", inputs -> new VisualizeComponent(
            ComponentType.Luma, inputs[0], inputs[1]));
    this.commands.put("greyscale", inputs -> new VisualizeComponent(
            ComponentType.Luma, inputs[0], inputs[1]));
    this.commands.put("sepia", inputs -> new VisualizeComponent(
            ComponentType.Sepia, inputs[0], inputs[1]));

    this.commands.put("blur", inputs -> new FilterBlur(inputs[0], inputs[1]));
    this.commands.put("sharpen", inputs -> new FilterSharpen(inputs[0], inputs[1]));

    this.commands.put("flip-vertical", inputs -> new FlipVertical(inputs[0], inputs[1]));
    this.commands.put("flip-horizontal", inputs -> new FlipHorizontal(inputs[0], inputs[1]));

    this.commands.put("menu", inputs -> new Menu());
  }

  @Override
  public void loadImage(String imagePath, String imageName) {
    try {
      String fileExtension = imagePath.substring(imagePath.lastIndexOf("."));
      ImageAbstractFactory factory = ImageAbstractFactory.getFactory(fileExtension, imagePath);
      this.images.put(imageName, factory.createImage());
      this.view.renderMessage("load successful");
      this.view.renderImage(this.images.get(imageName));
    } catch (IndexOutOfBoundsException | FileNotFoundException e) {
      this.view.renderMessage("File " + imagePath + " not found!");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void saveImage(String imagePath, String imageName) throws IllegalArgumentException {
    try {
      ImageModel image = this.getImage(imageName);

      String fileExtension = imagePath.substring(imagePath.lastIndexOf("."));
      ImageAbstractFactory factory = ImageAbstractFactory.getFactory(fileExtension, imagePath);
      ImageModel newImage = factory.createImageWithArray(image.getPixelArray());

      byte[] fileBytes = newImage.writeImage();
      FileOutputStream file = new FileOutputStream(imagePath);
      file.write(fileBytes);
      file.close();
      this.view.renderMessage("save successful");
    } catch (IOException e) {
      throw new IllegalArgumentException("Invalid file name provided");
    }
  }

  @Override
  public void brighten(int increment, String imageName, String newName) {
    ImageModel image = this.getImage(imageName);
    ImageModel newImage = image.brighten(increment);
    this.images.put(newName, newImage);
    this.view.renderMessage("brighten successful");
  }

  @Override
  public void flipHorizontally(String imageName, String newName) {
    ImageModel image = this.getImage(imageName);
    ImageModel newImage = image.flipHorizontally();
    this.images.put(newName, newImage);
    this.view.renderMessage("flip-horizontal successful");
  }

  @Override
  public void flipVertically(String imageName, String newName) {
    ImageModel image = this.getImage(imageName);
    ImageModel newImage = image.flipVertically();
    this.images.put(newName, newImage);
    this.view.renderMessage("flip-vertical successful");
  }

  @Override
  public void visualizeComponent(ComponentType type, String imageName, String newName) {
    ImageModel image = this.getImage(imageName);
    ImageModel newImage = image.visualizeComponents(type);
    this.images.put(newName, newImage);
    this.view.renderMessage("visualizing successful");
  }

  @Override
  public void filter(String imageName, String newName, double[][] filter) {
    ImageModel image = this.getImage(imageName);
    ImageModel newImage = image.filter(filter);
    this.images.put(newName, newImage);
    this.view.renderMessage("filter successful");
  }

  @Override
  public void menu() {
    Map<String, String> descriptions = new HashMap<>();
    descriptions.put("load", Load.description());

    descriptions.put("blur", FilterBlur.description());
    descriptions.put("sharpen", FilterSharpen.description());

    descriptions.put("red-component", VisualizeComponent.description(
            "red-component"));
    descriptions.put("green-component", VisualizeComponent.description(
            "green-component"));
    descriptions.put("blue-component", VisualizeComponent.description(
            "blue-component"));
    descriptions.put("value-component", VisualizeComponent.description(
            "value-component"));
    descriptions.put("intensity-component", VisualizeComponent.description(
            "intensity-component"));
    descriptions.put("luma-component", VisualizeComponent.description(
            "luma-component"));
    descriptions.put("greyscale", VisualizeComponent.description("greyscale"));
    descriptions.put("sepia", VisualizeComponent.description("sepia"));

    descriptions.put("flip-vertical", FlipVertical.description());
    descriptions.put("flip-horizontal", FlipHorizontal.description());

    descriptions.put("brighten", Brighten.description());
    descriptions.put("save", Save.description());

    descriptions.put("menu", Menu.description());
    this.view.renderMessage("Commands list:");
    for (String command : this.commands.keySet()) {
      this.view.renderMessage("\t" + "Name: " + command);
      this.view.renderMessage("\t\t" + "Description: " + descriptions.get(command));
    }
  }

  /**
   * Method that handles executing a command from the user.
   *
   * @param command represents the function for executing an image command given the inputs as
   *                strings.
   * @param inputs  represents all necessary inputs (as strings) for executing command
   */
  protected void handleCommand(Function<String[], ImageCommand> command, String[] inputs) {
    if (command == null) {
      this.view.renderMessage("Invalid command");
    } else {
      try {
        ImageCommand cmd = command.apply(inputs);
        cmd.execute(this);
      } catch (IllegalArgumentException | IOException e) {
        this.view.renderMessage("Invalid inputs");
      } catch (IndexOutOfBoundsException e) {
        this.view.renderMessage("Please include all inputs");
      }
    }
  }

  protected ImageModel getImage(String imageName) {
    ImageModel image = this.images.getOrDefault(imageName, null);
    if (image == null) {
      throw new IllegalArgumentException(imageName + " not found");
    }

    return image;
  }
}
