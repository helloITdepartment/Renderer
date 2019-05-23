package elements;

import java.awt.Color;

import primitives.Point3D;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource {

	//Vector representing the direction of the light
	Vector _direction;

	//default constructor which creates a default vector as the direction of the DirectionalLight and default values for _color and _kA
	public DirectionalLight(){
		_direction = new Vector();
		_color = new Color(255, 255, 255);
        _kA = 1.0;
	}

	//constructor which accepts a vector as a parameter and sets the direction of the DirectionalLight accordingly and default values for _color and _kA
	public DirectionalLight(Vector direction){
		_direction = direction;
        _color = new Color(255, 255, 255);
        _kA = 1.0;
	}
	
	//Parameterized constructor
	public DirectionalLight(Vector direction, Color color, double kA) {
		//Sets internal variables to those passed
		_direction = direction;
		_color = color;
		_kA = kA;
	}

	//Copy constructor
	public DirectionalLight(DirectionalLight other) {
		//Sets internal variables to copies of other's
		_direction = other.getdirection();
		_color = other.getColor();
		_kA = other.getKa();
	}
	//getter for the direction of the DirectionalLight
	public Vector getdirection() {
		//Returns a new Vector with the same value as our _direction so that changes made at the callsite won't affect our variables
		return new Vector(_direction);
	}
	
	public Color getColor() {
		//Returns a new Color with the same RGB values as our _color so that changes made at the callsite won't affect our variables
		return new Color(_color.getRGB());
	}
	
	public double getKa() {
		return _kA;
	}

	//setter for the direction of the DirectionalLight
	public void setdirection(Vector direction) {
		_direction = direction;
	}
	
	public void setColor(Color color) {
		//Allows for setting of protected data member _color
		_color = color;
	}
	
	public void setKa(double kA) {
		//Allows for setting of protected data member _color
		_kA = kA;
	}

	//Returns a version of _color, where each component is scaled by _kA, representing the color
	public Color getIntensity(Point3D point) {
		int red = (int)(_color.getRed()*_kA);
		int green = (int)(_color.getGreen()*_kA);
		int blue = (int)(_color.getBlue()*_kA);

		return new Color(red, green, blue);
	}

	//Return the vector that a photon coming from this DirectionalLight would travel along to intersect the given Point
	public Vector getL(Point3D point) {
		//Which is just the direction of the light
		return _direction.normalize();
	}
}