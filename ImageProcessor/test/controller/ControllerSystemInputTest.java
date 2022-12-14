package controller;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import view.ImageProcessorTextViewImpl;
import view.ImageProcessorTextView;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the ControllerSystemInput class.
 */
public class ControllerSystemInputTest {
  Appendable output;
  ImageProcessorTextView view;
  IController controller;
  String path = "/Users/gavinwainwright/Documents/lilbaby.ppm";

  @Before
  public void initializeConditions() {
    this.output = new StringBuilder();
    this.view = new ImageProcessorTextViewImpl(this.output);
  }

  @Test
  public void testRenderMessage() {
    this.view.renderMessage("test");
    assertEquals("test\n", this.output.toString());
  }

  @Test
  public void testHandleInput() throws IOException {
    String expected = "Type a command: \n"
            + "Type all inputs separated by a space: \n"
            + "load successful\n"
            + "Type a command: \n"
            + "Type all inputs separated by a space: \n"
            + "brighten successful\n"
            + "Type a command: \n"
            + "Thanks for using our application!\n";
    Readable input = new StringReader("load\n" + path + " lilbaby\n"
            + "brighten\n10 lilbaby lilbaby-brighten\nq");
    controller = new ControllerSystemInput(this.view, input);
    controller.handleInput();
    assertEquals(expected, this.view.toString());
  }

  @Test
  public void testHandleInputInvalidCommand() throws IOException {
    String expected = "Type a command: \n"
            + "Type all inputs separated by a space: \n"
            + "load successful\n"
            + "Type a command: \n"
            + "Type all inputs separated by a space: \n"
            + "Invalid inputs\n"
            + "Type a command: \n"
            + "Thanks for using our application!\n";
    Readable input = new StringReader("load\n" + path + " lilbaby\n"
            + "brighten\ninvalid lilbaby lilbaby-brighten\nq");
    controller = new ControllerSystemInput(this.view, input);
    controller.handleInput();
    assertEquals(expected, this.view.toString());
  }
}