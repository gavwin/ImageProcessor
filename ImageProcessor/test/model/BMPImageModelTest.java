package model;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import controller.ControllerFileInput;
import controller.IController;
import utilities.ComponentType;
import view.ImageProcessorTextViewImpl;
import view.ImageProcessorTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test class for the BPMImageModel.
 */
public class BMPImageModelTest {
  String basePath = "/Users/gavinwainwright/Documents/Courses/CS 3500/Projects/Assignment5/images/"
          + "bmp/";
  String lilbabyVisualizeRed = basePath + "lilbaby-red.bmp";
  String imagePath = basePath + "lilbaby.bmp";
  ImageModel image;
  ImageModel visualizeRed;
  IController controller;
  ImageProcessorTextView view = new ImageProcessorTextViewImpl(new StringBuilder());

  @Before
  public void initializeConditions() {
    try {
      image = new BMPImageModel(imagePath);
      visualizeRed = new BMPImageModel(lilbabyVisualizeRed);
      controller = new ControllerFileInput(view, imagePath);
    } catch (FileNotFoundException e) {
      fail("Failed to load test image.");
    }
  }

  @Test
  public void writeImage() throws IOException {
    ImageModel modified = image.visualizeComponents(ComponentType.Red);
    assertEquals(Arrays.toString(visualizeRed.writeImage()),
            Arrays.toString(modified.writeImage()));
  }
}