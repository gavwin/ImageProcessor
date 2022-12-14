package controller;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import view.ImageProcessorTextViewImpl;
import view.ImageProcessorTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test class for the ControllerFileInput class.
 */
public class ControllerFileInputTest {
  IController controller;
  ImageProcessorTextView view = new ImageProcessorTextViewImpl(new StringBuilder());

  @Test
  public void handleInput() throws IOException {
    String path = "/Users/gavinwainwright/Documents/Courses/CS 3500/Projects/Assignment5/"
            + "test_commands.txt";
    try {
      controller = new ControllerFileInput(view, path);
    } catch (FileNotFoundException e) {
      fail("Failed to load file in handleInput test.");
    }
    controller.handleInput();
    String expected = "load successful\n"
            + "visualizing successful\n"
            + "Thanks for using our application!\n";
    assertEquals(expected, view.toString());
  }

  @Test
  public void handleInputInvalidCommand() throws IOException {
    String path = "/Users/gavinwainwright/Documents/Courses/CS 3500/Projects/Assignment5/"
            + "test_commands_invalid.txt";
    try {
      controller = new ControllerFileInput(view, path);
    } catch (FileNotFoundException e) {
      fail("Failed to load file in handleInput test.");
    }
    controller.handleInput();
    String expected = "load successful\n"
            + "visualizing successful\n"
            + "Invalid command\n"
            + "Thanks for using our application!\n";
    assertEquals(expected, view.toString());
  }

  @Test
  public void handleInputInvalidFile() throws IOException {
    String path = "/User/Projects/Assignment5/doesntexist.txt";
    try {
      controller = new ControllerFileInput(view, path);
    } catch (FileNotFoundException e) {
      fail("Failed to load file in handleInput test.");
    }
    controller.handleInput();
    String expected = "File /User/Projects/Assignment5/doesntexist.txt not found!\n"
            + "Invalid command\n"
            + "Thanks for using our application!\n";
    assertEquals(expected, view.toString());
  }
}