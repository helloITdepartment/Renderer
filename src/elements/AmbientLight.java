package elements;

import java.awt.Color;

public class AmbientLight {
	//The color of the ambient light
	Color _color;
	//The intensity of the ambient light
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
		return new Double(_kA);
	}
	//Setters
	public Color getIntensity() {
		//Testing to make sure this got sent to the right branch
	}
}
