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
 * Test class for the PNGImageModel.
 */
public class PNGImageModelTest {
  String basePath = "/Users/gavinwainwright/Documents/Courses/CS 3500/Projects/Assignment5/images/"
          + "png/";
  String lilbabySepia = basePath + "lilbaby-sepia.png";
  String imagePath = basePath + "lilbaby.png";
  ImageModel image;
  ImageModel sepia;
  IController controller;
  ImageProcessorTextView view = new ImageProcessorTextViewImpl(new StringBuilder());

  @Before
  public void initializeConditions() {
    try {
      image = new PNGImageModel(imagePath);
      sepia = new PNGImageModel(lilbabySepia);
      controller = new ControllerFileInput(view, imagePath);
    } catch (FileNotFoundException e) {
      fail("Failed to load test image.");
    }
  }

  @Test
  public void writeImage() throws IOException {
    ImageModel modified = image.visualizeComponents(ComponentType.Sepia);
    assertEquals(Arrays.toString(sepia.writeImage()),
            Arrays.toString(modified.writeImage()));
  }
}