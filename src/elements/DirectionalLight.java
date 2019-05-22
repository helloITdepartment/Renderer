package elements;

import java.awt.Color;

import primitives.Point3D;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource {

	//Vector representing the direction of the light
	Vector _direction;

	//default constructor which creates a default vector as the direction of the DirectionalLight
	public DirectionalLight(){
		_direction = new Vector();
	}

	//constructor which accepts a vector as a parameter and sets the direction of the DirectionalLight accordingly
	public DirectionalLight(Vector direction){
		_direction = direction;
	}


	//getter for the direction of the DirectionalLight
	public Vector getdirection() {
		return _direction;
	}

	//setter for the direction of the DirectionalLight
	public void setdirection(Vector direction) {
		_direction = direction;
	}

	//Returns a version of _color, where each component is scaled by _kA, representing the color
	public Color getIntensity(Point3D point) {
		int red = (int)(_color.getRed()*_kA);
		int green = (int)(_color.getGreen()*_kA);
		int blue = (int)(_color.getBlue()*_kA);

		return new Color(red, green, blue);
	}

	public Vector getL(Point3D point) {
		// TODO Auto-generated method stub
		return _direction.normalize();
	}
}