package controller;

import org.junit.Test;

import view.ImageProcessorGUIView;
import view.ImageProcessorGUIViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the GUI Controller.
 */
public class GUIControllerTest {
  private ImageProcessorGUIView view = new ImageProcessorGUIViewImpl();
  private MockGUIController controller = new MockGUIController(this.view);

  @Test
  public void testLoadImage() {
    String expected = "/Users/gavinwainwright/Documents/Courses/CS 3500/Projects/Assignment5/./"
            + "images/newimagetest.jpg";
    assertEquals(expected, this.controller.loadImage());
  }
}
