package elements;

import java.awt.*;

public abstract class Light {
    //abstract class for implementing all the different types of lighting
    Color _color;

    //Returns a version of _color, where each component is scaled by _kA, representing the color
    public Color getIntensity() {
        int red = (int)(_color.getRed()*_kA);
        int green = (int)(_color.getGreen()*_kA);
        int blue = (int)(_color.getBlue()*_kA);

        return new Color(red, green, blue);
    }
}

