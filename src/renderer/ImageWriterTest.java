package renderer;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.awt.*;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class ImageWriterTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(ImageWriter.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void writeToImageTest() {
        // set test image of type Image Writer to a height and width of 150, with 500 pixels horizontally and vertically
        ImageWriter testImage = new ImageWriter("rstlne", 150, 150, 500, 500);
        testImage.writeToImage();
    }

    @Test
    // write pixel test 1, set every 50th pixel to white
    public void writePixel1Test() {
         ImageWriter testImage = new ImageWriter("testPixel1", 150, 150, 500, 500);
         for(int i =0; i < 500; i++) {
             for(int j = 0; j < 500; j++) {
                 // if i or j are multiples of 50, make the pixel at the x,y coordinate white, if not, make the pixel black
                 if(i % 50 == 0 || j % 50 == 0) {
                     // 255 is white , make the current pixel white
                     testImage.writePixel(i, j, 255, 255, 255);
                 }
                 else{
                     // 0 is black, make the current pixel black
                     testImage.writePixel(i, j, 0, 0, 0);
                 }
             }
         }

         testImage.writeToImage(); // write the image to the file

    }

    @Test
    // write pixel test 2, set every 50th pixel to white
    public void writePixel2Test() {

        ImageWriter testImage = new ImageWriter("testPixel2", 150, 150, 500, 500);
        // color array
        int [] rgbArray = new int[3];
        for(int i =0; i < 500; i++) {
            for(int j = 0; j < 500; j++) {
                // if i or j are multiples of 50, make the pixel at the x,y coordinate white, if not, make the pixel black
                if(i % 50 == 0 || j % 50 == 0) {
                    // make the pixel white
                    rgbArray[0] = 255;
                    rgbArray[1] = 255;
                    rgbArray[2] = 255;
                }
                else{
                    // make the pixel black
                    rgbArray[0] = 0;
                    rgbArray[1] = 0;
                    rgbArray[2] = 0;
                }
                // set the pixel to the correct color
                testImage.writePixel(i, j, rgbArray);
            }
        }
        // write the image to the file
        testImage.writeToImage();
    }

    @Test
    // write pixel test 3, set every 50th pixel to white
    public void writePixel3() {
         ImageWriter testImage = new ImageWriter("testPixel3", 150, 150, 500, 500);
        for(int i =0; i < 500; i++) {
            for(int j = 0; j < 500; j++) {
                // if i or j are multiples of 50, make the pixel at the x,y coordinate white, if not, make the pixel black
                if(i % 50 == 0 || j % 50 == 0) {
                    // set the color to white
                    testImage.writePixel(i, j, new Color(255, 255, 255));
                }
                else{
                    // set the color to black
                    testImage.writePixel(i, j, new Color(0, 0, 0));
                }
            }
        }
        // write the image to the file
        testImage.writeToImage();
    }
}
