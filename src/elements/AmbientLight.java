package elements;

import java.awt.Color;

//A class representing a light that ambiently lights a scene, like a light bulb or the sun would
public class AmbientLight extends Light {
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
	

}
