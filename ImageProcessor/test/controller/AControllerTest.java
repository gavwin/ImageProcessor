package controller;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import utilities.ComponentType;
import model.ImageModel;
import model.PPMImageModel;
import utilities.Settings;
import view.ImageProcessorTextViewImpl;
import view.ImageProcessorTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test class for the AController class.
 */
public class AControllerTest {
  String basePath = "/Users/gavinwainwright/Documents/Courses/CS 3500/Projects/Assignment5/images/"
          + "ppm/";
  String lilbaby = basePath + "lilbaby.ppm";
  String lilbabyNew = basePath + "lilbaby-new.ppm";
  String lilbabyBrightened = basePath + "lilbaby-brighten.ppm";
  String lilbabyVisualizeRed = basePath + "lilbaby-visualize-red.ppm";
  String lilbabyVertical = basePath + "lilbaby-vertical.ppm";
  String lilbabyHorizontal = basePath + "lilbaby-horizontal.ppm";
  String lilbabyFilter = basePath + "lilbaby-filter-blur.ppm";

  ImageModel image;
  Appendable appendable;
  ImageProcessorTextView view;
  IController controller;

  @Before
  public void initializeConditions() {
    try {
      this.appendable = new StringBuilder("");
      this.view = new ImageProcessorTextViewImpl(this.appendable);
      this.controller = new ControllerSystemInput(this.view);
      image = new PPMImageModel(lilbaby);
      controller.loadImage(lilbaby, "lilbaby");
    } catch (FileNotFoundException e) {
      fail("Failed to initialize PPMImageModel in @Before method.");
    }
  }

  @Test
  public void loadAndSaveImage() throws FileNotFoundException {
    controller.visualizeComponent(ComponentType.Luma, "lilbaby", "lilbaby-new");
    try {
      controller.saveImage(lilbabyNew, "lilbaby-new");
    } catch (IOException e) {
      fail("Failed to saveImage in loadAndSaveImage test.");
    }
  }

  @Test
  public void brighten() throws FileNotFoundException {
    controller.brighten(100, "lilbaby", "lilbaby-brighten");
    try {
      controller.saveImage(lilbabyNew, "lilbaby-brighten");
      ImageModel expected = new PPMImageModel(lilbabyBrightened);
      ImageModel actual = new PPMImageModel(lilbabyNew);
      assertEquals(Arrays.toString(expected.writeImage()), Arrays.toString(actual.writeImage()));
    } catch (IOException e) {
      fail("Failed to saveImage in brighten test.");
    }
  }

  @Test
  public void flipHorizontally() throws FileNotFoundException {
    controller.flipHorizontally("lilbaby", "lilbaby-horizontal");
    try {
      controller.saveImage(lilbabyNew, "lilbaby-horizontal");
      ImageModel expected = new PPMImageModel(lilbabyHorizontal);
      ImageModel actual = new PPMImageModel(lilbabyNew);
      assertEquals(expected.writeImage(), actual.writeImage());
    } catch (IOException e) {
      fail("Failed to saveImage in flipHorizontally test.");
    }
  }

  @Test
  public void flipVertically() throws FileNotFoundException {
    controller.flipVertically("lilbaby", "lilbaby-vertical");
    try {
      controller.saveImage(lilbabyNew, "lilbaby-vertical");
      ImageModel expected = new PPMImageModel(lilbabyVertical);
      ImageModel actual = new PPMImageModel(lilbabyNew);
      assertEquals(Arrays.toString(expected.writeImage()), Arrays.toString(actual.writeImage()));
    } catch (IOException e) {
      fail("Failed to saveImage in flipVertically test.");
    }
  }

  @Test
  public void visualizeComponent() throws FileNotFoundException {
    controller.visualizeComponent(ComponentType.Red, "lilbaby",
            "lilbaby-visualized-red");
    try {
      controller.saveImage(lilbabyNew, "lilbaby-visualized-red");
      ImageModel expected = new PPMImageModel(lilbabyVisualizeRed);
      ImageModel actual = new PPMImageModel(lilbabyNew);
      assertEquals(Arrays.toString(expected.writeImage()), Arrays.toString(actual.writeImage()));
    } catch (IOException e) {
      fail("Failed to saveImage in visualizeComponent test.");
    }
  }

  @Test
  public void filter() throws FileNotFoundException {
    double[][] filter = Settings.BLUR_MATRIX;
    controller.filter("lilbaby",
            "lilbaby-filter-blur", filter);
    try {
      controller.saveImage(lilbabyNew, "lilbaby-filter-blur");
      ImageModel expected = new PPMImageModel(lilbabyFilter);
      ImageModel actual = new PPMImageModel(lilbabyNew);
      assertEquals(Arrays.toString(expected.writeImage()), Arrays.toString(actual.writeImage()));
    } catch (IOException e) {
      fail("Failed to saveImage in filter test.");
    }
  }

  @Test
  public void testLoadImageNoFile() {
    try {
      controller.loadImage("/this/directory/does/not/exist/image.ppm", "image");
    } catch (FileNotFoundException | IllegalArgumentException e) {
      assertEquals("File /this/directory/does/not/exist/image.ppm not found!",
              e.getMessage());
    }
  }

  @Test
  public void testSaveImageNoFile() {
    try {
      controller.saveImage("/this/directory/does/not/exist/image.ppm", "image");
    } catch (IOException | IllegalArgumentException e) {
      assertEquals("image not found", e.getMessage());
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBrightenNoFile() throws FileNotFoundException {
    controller.brighten(100, "/this/directory/does/not/exist/image.ppm", "image");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFlipVerticallyNoFile() throws FileNotFoundException {
    controller.flipVertically("/this/directory/does/not/exist/image.ppm", "image");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFlipHorizontallyNoFile() throws FileNotFoundException {
    controller.flipHorizontally("/this/directory/does/not/exist/image.ppm", "image");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testVisualizeComponentNoFile() throws FileNotFoundException {
    controller.visualizeComponent(ComponentType.Red,
            "/this/directory/does/not/exist/image.ppm", "image");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFilterNoFile() {
    controller.filter("/this/directory/does/not/exist/image.ppm", "image",
            Settings.BLUR_MATRIX);
  }
}