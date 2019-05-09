package renderer;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.*;
import javax.imageio.stream.*;

public class ImageWriter {

    // image width
    int _imageWidth;
    // image height
    int _imageHeight;
    // number of horizontal pixels
    int _Nx;
    // number of vertical pixels
    int _Ny;
    // final makes PROJECT PATH constant, and unchangeable
    final String PROJECT_PATH = System.getProperty("user.dir");
    // buffered image is an inherited class from java, that is responsible for various features of making images
    BufferedImage _image;
    // name of image
    String _imageName;

    // ***************** Constructors ********************** //

    public ImageWriter(String imageName, int width, int height, int Nx, int Ny) {
        _imageName = imageName;
        _imageWidth = width;
        _imageHeight = height;
        _Nx = Nx;
        _Ny = Ny;

        _image = new BufferedImage(_imageWidth, _imageHeight, BufferedImage.TYPE_INT_RGB);
    }

    public ImageWriter (ImageWriter imageWriter) {
        this(	imageWriter._imageName,
                imageWriter._imageWidth, imageWriter._imageHeight,
                imageWriter._Nx, imageWriter._Ny);
    }

    // ***************** Getters/Setters ********************** //
    // returns the image width for use
    public int getWidth()  { return _imageWidth;  }
    // returns the image height for use
    public int getHeight() { return _imageHeight; }
    // returns the number of vertical pixels
    public int getNy() { return _Ny; }
    // returns the number of horizontal pixels
    public int getNx() { return _Nx; }

    // sets the number of vertical pixels
    public void setNy(int _Ny) { this._Ny = _Ny; }
    // sets the number of vertical pixels
    public void setNx(int _Nx) { this._Nx = _Nx; }

    // ***************** Operations ******************** //

    public void writeToImage(){
        // makes a file, where we put the resulting jpeg image that we create
        File outFile = new File(PROJECT_PATH + "/" + _imageName + ".jpg");
        // attempt to do the following, and if not, catch it
        try {
            // name of class image writer, and we call it "jpg"
            javax.imageio.ImageWriter jpgWriter = ImageIO.getImageWritersByFormatName("jpg").next();
            // choosing the parameters of the picture, if any of them are 0, throw an exception
            // we are setting the correct parameters to jpgWriteParam
            ImageWriteParam jpgWriteParam = jpgWriter.getDefaultWriteParam();
            // specify for jpgWriteParam what parameters to set, and they are the parameters that were determined in the previous line
            jpgWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            // set the compression quality
            jpgWriteParam.setCompressionQuality(1f);
            // make a new file and set it as the output for the jpgWriter
            jpgWriter.setOutput(new FileImageOutputStream(outFile));
            //  write to the file the image
            jpgWriter.write(null,new IIOImage(_image, null, null), jpgWriteParam);
            //ImageIO.write(_image, "jpg", ouFile);
        } catch (IOException e) {
            // display error message to the console
            e.printStackTrace();
        }
    }

    // write pixel function that creates colors for red, green, blue independently
    public void writePixel(int xIndex, int yIndex, int r, int g, int b){
        // make the number of the color
        int rgb = new Color(r, g, b).getRGB();
        // set the color to individual pixel using the x and y coordinate, and the color number
        _image.setRGB(xIndex, yIndex, rgb);
    }
    // write pixel function that sets the color using the x and y index for pixels, and an array of colors
    public void writePixel(int xIndex, int yIndex, int[] rgbArray){
        // choose the color number
        int rgb = new Color(rgbArray[0], rgbArray[1], rgbArray[2]).getRGB();
        // set the color to individual pixel using the x and y coordinate, annn the color number
        _image.setRGB(xIndex, yIndex, rgb);
    }
    // write Pixel function that sets the image
    public void writePixel(int xIndex, int yIndex, Color color){
        // set the color to individual pixel using the x and y coordinate, and the color number
        _image.setRGB(xIndex, yIndex, color.getRGB());
    }

}