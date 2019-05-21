package elements;

import primitives.*;
import java.awt.*;

public interface LightSource {

	public Color getIntensity(Point3D point);
	
	public Vector getL(Point3D point);
}
