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
 * Test class for the JPGImageModel.
 */
public class JPGImageModelTest {
  String basePath = "/Users/gavinwainwright/Documents/Courses/CS 3500/Projects/Assignment5/images/"
          + "jpeg/";
  String lilbabyGreyscale = basePath + "lilbaby-greyscale.jpeg";
  String imagePath = basePath + "lilbaby.jpeg";
  ImageModel image;
  ImageModel greyscale;
  IController controller;
  ImageProcessorTextView view = new ImageProcessorTextViewImpl(new StringBuilder());

  @Before
  public void initializeConditions() {
    try {
      image = new JPGImageModel(imagePath);
      greyscale = new JPGImageModel(lilbabyGreyscale);
      controller = new ControllerFileInput(view, imagePath);
    } catch (FileNotFoundException e) {
      fail("Failed to load test image.");
    }
  }

  @Test
  public void writeImage() throws IOException {
    ImageModel modified = image.visualizeComponents(ComponentType.Luma);
    assertEquals(Arrays.toString(greyscale.writeImage()),
            Arrays.toString(modified.writeImage()));
  }
}