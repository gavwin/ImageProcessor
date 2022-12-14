package model;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import utilities.ComponentType;
import utilities.Settings;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the AImageModel class.
 * Uses the PPMImageModel and .ppm image files to run the tests.
 * The methods tested within this class only need to be tested once because all of the functionality
 * with the exception of the writeImage() method has been abstracted out to the AImageModel class.
 */
public class AImageModelTest {
  String basePath = "/Users/gavinwainwright/Documents/Courses/CS 3500/Projects/Assignment5/images/"
          + "ppm/";
  String lilbaby = basePath + "lilbaby.ppm";
  String lilbabyVertical = basePath + "lilbaby-vertical.ppm";
  String lilbabyHorizontal = basePath + "lilbaby-horizontal.ppm";
  String lilbabyVisualizeRed = basePath + "lilbaby-visualize-red.ppm";
  String lilbabyVisualizeGreen = basePath + "lilbaby-visualize-green.ppm";
  String lilbabyVisualizeBlue = basePath + "lilbaby-visualize-blue.ppm";
  String lilbabyVisualizeValue = basePath + "lilbaby-visualize-value.ppm";
  String lilbabyVisualizeIntensity = basePath + "lilbaby-visualize-intensity.ppm";
  String lilbabyVisualizeLuma = basePath + "lilbaby-visualize-luma.ppm";
  String lilbabyVisualizeSepia = basePath + "lilbaby-visualize-sepia.ppm";
  String lilbabyBrightened = basePath + "lilbaby-brighten.ppm";
  String lilbabyDarkened = basePath + "lilbaby-darken.ppm";
  String lilbabyBlurred = basePath + "lilbaby-filter-blur.ppm";
  String lilbabySharpened = basePath + "lilbaby-filter-sharpen.ppm";

  ImageModel image;
  ImageModel vertical;
  ImageModel horizontal;
  ImageModel visualizeRed;
  ImageModel visualizeGreen;
  ImageModel visualizeBlue;
  ImageModel visualizeValue;
  ImageModel visualizeIntensity;
  ImageModel visualizeLuma;
  ImageModel visualizeSepia;
  ImageModel brightened;
  ImageModel darkened;
  ImageModel blur;
  ImageModel sharpen;

  @Before
  public void initializeConditions() {
    image = new PPMImageModel(lilbaby);
    vertical = new PPMImageModel(lilbabyVertical);
    horizontal = new PPMImageModel(lilbabyHorizontal);
    visualizeRed = new PPMImageModel(lilbabyVisualizeRed);
    visualizeGreen = new PPMImageModel(lilbabyVisualizeGreen);
    visualizeBlue = new PPMImageModel(lilbabyVisualizeBlue);
    visualizeValue = new PPMImageModel(lilbabyVisualizeValue);
    visualizeIntensity = new PPMImageModel(lilbabyVisualizeIntensity);
    visualizeLuma = new PPMImageModel(lilbabyVisualizeLuma);
    visualizeSepia = new PPMImageModel(lilbabyVisualizeSepia);
    brightened = new PPMImageModel(lilbabyBrightened);
    darkened = new PPMImageModel(lilbabyDarkened);
    blur = new PPMImageModel(lilbabyBlurred);
    sharpen = new PPMImageModel(lilbabySharpened);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDefaultConstructorInvalidFilePath() {
    ImageModel image = new PPMImageModel("/this/directory/does/not/exist/image.ppm");
  }

  @Test
  public void visualizeRed() throws IOException {
    ImageModel modified = image.visualizeComponents(ComponentType.Red);
    assertEquals(Arrays.toString(visualizeRed.writeImage()),
            Arrays.toString(modified.writeImage()));
  }

  @Test
  public void visualizeGreen() throws IOException {
    ImageModel modified = image.visualizeComponents(ComponentType.Green);
    assertEquals(Arrays.toString(visualizeGreen.writeImage()),
            Arrays.toString(modified.writeImage()));
  }

  @Test
  public void visualizeBlue() throws IOException {
    ImageModel modified = image.visualizeComponents(ComponentType.Blue);
    assertEquals(Arrays.toString(visualizeBlue.writeImage()),
            Arrays.toString(modified.writeImage()));
  }

  @Test
  public void visualizeValue() throws IOException {
    ImageModel modified = image.visualizeComponents(ComponentType.Value);
    assertEquals(Arrays.toString(visualizeValue.writeImage()),
            Arrays.toString(modified.writeImage()));
  }

  @Test
  public void visualizeIntensity() throws IOException {
    ImageModel modified = image.visualizeComponents(ComponentType.Intensity);
    assertEquals(Arrays.toString(visualizeIntensity.writeImage()),
            Arrays.toString(modified.writeImage()));
  }

  @Test
  public void visualizeLuma() throws IOException {
    ImageModel modified = image.visualizeComponents(ComponentType.Luma);
    assertEquals(Arrays.toString(visualizeLuma.writeImage()),
            Arrays.toString(modified.writeImage()));
  }

  @Test
  public void visualizeSepia() throws IOException {
    ImageModel modified = image.visualizeComponents(ComponentType.Sepia);
    assertEquals(Arrays.toString(visualizeSepia.writeImage()),
            Arrays.toString(modified.writeImage()));
  }

  @Test
  public void flipHorizontally() throws IOException {
    ImageModel modified = image.flipHorizontally();
    assertEquals(Arrays.toString(horizontal.writeImage()), Arrays.toString(modified.writeImage()));
  }

  @Test
  public void flipVertically() throws IOException {
    ImageModel modified = image.flipVertically();
    assertEquals(Arrays.toString(vertical.writeImage()), Arrays.toString(modified.writeImage()));
  }

  @Test
  public void brighten() throws IOException {
    ImageModel modified = image.brighten(100);
    assertEquals(Arrays.toString(brightened.writeImage()), Arrays.toString(modified.writeImage()));
  }

  @Test
  public void darken() throws IOException {
    ImageModel modified = image.brighten(-75);
    assertEquals(Arrays.toString(darkened.writeImage()), Arrays.toString(modified.writeImage()));
  }

  @Test
  public void blur() throws IOException {
    double[][] matrix = Settings.BLUR_MATRIX;
    ImageModel modified = image.filter(matrix);
    assertEquals(Arrays.toString(blur.writeImage()), Arrays.toString(modified.writeImage()));
  }

  @Test
  public void sharpen() throws IOException {
    double[][] matrix = Settings.SHARPEN_MATRIX;
    ImageModel modified = image.filter(matrix);
    assertEquals(Arrays.toString(sharpen.writeImage()), Arrays.toString(modified.writeImage()));
  }

  @Test
  public void getWidth() {
    assertEquals(225, image.getWidth());
  }

  @Test
  public void getHeight() {
    assertEquals(224, image.getHeight());
  }
}