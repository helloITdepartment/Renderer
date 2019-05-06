package elements;

import java.awt.Color;

public class AmbientLight {
	//The color of the ambient light
	Color _color;
	//The intensity of the ambient light, ranging from 0.0 to 1.0
	double _kA;

	
	//Empty constructor
	public AmbientLight() {
		//Assigns color a default value of black
		_color = new Color(0,0,0);
		//And a default value of intensity of 0.0
		_kA = 0.0;
	}
	
	//Parameterized constructor
	public AmbientLight(Color color, double intensity) {
		//Assigns values to data members to those given
		_color = color;
		_kA = intensity;
	}
	
	//Getters
	public Color getColor() {
		//Returns new Color with the same RGB values as our _color, so that changes made at the callsite wont affect our variables
		return new Color(_color.getRGB());
	}
	
	public Double getkA() {
		//Returns new double with the same value as our kA, so that changes made at the callsite wont affect our variables
		return new Double(_kA);
	}
	
	//Setters
	public void setColor(Color other){
		// allows setting of protected member _color
		_color = other;
	}
	
	public void setkA(double other) {
		// allows setting of protected member _kA
		_kA = other;
	}
	
	//Returns a version of _color, where each component is scaled by _kA, representing the color
	public Color getIntensity() {
		int red = (int)(_color.getRed()*_kA);
		int green = (int)(_color.getGreen()*_kA);
		int blue = (int)(_color.getBlue()*_kA);
		
		return new Color(red, green, blue);
	}
}
