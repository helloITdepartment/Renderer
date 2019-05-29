package elements;

import primitives.*;
import java.awt.*;

//An interface representing different types of Light Sources.
public interface LightSource {

	//Anything that wants to be a LightSource must implement methods that:
	//Return a color representing the brightness at a certain point
	public Color getIntensity(Point3D point);
	
	//Return the vector that a photon coming from the light source would travel along to intersect the given Point
	public Vector getL(Point3D point);
}
