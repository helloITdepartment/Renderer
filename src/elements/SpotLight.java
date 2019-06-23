package elements;

import java.awt.Color;

import primitives.Point3D;
import primitives.Vector;

public class SpotLight extends PointLight implements LightSource{
    //direction of the spotlight lighting
    Vector _direction;

    //default constructor
    public SpotLight(){
    	//Sets Direction to a default vector
        _direction = new Vector();
        //Sets the position to the origin
    	_position = new Point3D();
    	//Sets _kc, _kl, _kq to default values that work nicely
    	_Kc = 0.00000000001;
    	_Kl = 0.00000000001;
    	_Kq = 0.00000000001;
    	//Sets color to a default white
    	_color = new Color(255, 255, 255);
    	//Sets _kA to a default 1.0
    	_kA = 1.0;
    }
    
    public SpotLight(Point3D position, double kc, double kl, double kq, Vector direction, Color color, double kA){
    	//Sets all variables to parameters passed
    	_position = position;
    	_Kc = kc;
    	_Kl = kl;
    	_Kq = kq;
        _direction = direction.normalize();
        _color = color;
        _kA = kA;
    }
    
    public SpotLight(Point3D position, Vector direction, Color color){
    	//Sets variables to parameters passed
    	_position = position;
        _direction = direction.normalize();
        _color = color;
        //Sets kA to default value of 1.0
        _kA = 1.0;
        //Sets _kc, _kl, _kq to default values that work nicely
    	_Kc = 0.00000000001;
    	_Kl = 0.00000000001;
    	_Kq = 0.00000000001;
    }
    
    public SpotLight(Point3D position, Vector direction, Color color, double kA){
    	//Sets variables to parameters passed
    	_position = position;
        _direction = direction.normalize();
        _color = color;
        _kA = kA;
        //Sets _kc, _kl, _kq to default values that work nicely
    	_Kc = 0.00000000001;
    	_Kl = 0.00000000001;
    	_Kq = 0.00000000001;
    }
    
    public SpotLight(Point3D position, Vector direction){
    	//Sets variables to parameters passed
    	_position = position;
        _direction = direction.normalize();
        //Sets _kc, _kl, _kq to default values that work nicely
    	_Kc = 0.00000000001;
    	_Kl = 0.00000000001;
    	_Kq = 0.00000000001;
    	//Sets color to a default white
    	_color = new Color(255, 255, 255);
    	//Sets _kA to a default 1.0
    	_kA = 1.0;
        
    }
    
    //constructor which accepts a vector as a parameter and sets the direction of the spotlight accordingly
    public SpotLight(Vector direction){
    	//Sets variables to parameters passed
        _direction = direction.normalize();
        //Sets position to the origin
        _position = new Point3D();
        //Sets _kc, _kl, _kq to default values that work nicely
    	_Kc = 0.00000000001;
    	_Kl = 0.00000000001;
    	_Kq = 0.00000000001;
    	//Sets color to a default white
    	_color = new Color(255, 255, 255);
    	//Sets _kA to a default 1.0
    	_kA = 1.0;
    }

    //getter for the direction of the spotlight
    public Vector getdirection() {
    	//Returns new vector with the same value as our _direction, so that changes at the callsite don't affect our variables
        return new Vector(_direction);
    }
    
    //getter for the color of the spotlight
    public Color getColor() {
    	//Returns new Color with the same RGB values as our _color, so that changes at the callsite don't affect our variables
    	return new Color(_color.getRGB());
    }

    //All other getters are implemented by superclasses
    
    //setter for the direction of the spotlight
    public void setdirection(Vector direction) {
        _direction = direction;
    }
    //All other setters are implemented by superclasses
    
    //Returns the intensity at a point
	public Color getIntensity(Point3D point) {
		//Finds the distance to that point
		double distance = _position.distanceTo(point);
		//Calculates the amount to scale the color by, given the constant, linear, and quadratic attenuation factors
		double factor = (_direction.dotProduct(getL(point))) / (_Kc + _Kl/distance + _Kq/(distance*distance));
		return scaleColor(getColor(), factor);
	}

	//Return the vector that a photon coming from this SpotLight would travel along to intersect the given Point
	public Vector getL(Point3D point) {
		return new Vector(point.subtract(_position)).normalize();
	}
	
	//A method to scale colors which makes sure values never go over 255 or below 0
	public Color scaleColor(Color color, double factor) {
		//Scales the red component. It it's above 255, just caps it at 255
		int newRed = ((int)(color.getRed()*factor) <= 255 ? (int)(color.getRed()*factor) : 255);
		//if it's below 0, brings it back up to 0
		newRed = (newRed >= 0 ? newRed : 0);
		
		//Scales the blue component. It it's above 255, just caps it at 255
		int newGreen = ((int)(color.getGreen()*factor) <= 255 ? (int)(color.getGreen()*factor) : 255);
		//if it's below 0, brings it back up to 0
		newGreen = (newGreen >= 0 ? newGreen : 0);
		
		//Scales the green component. It it's above 255, just caps it at 255
		int newBlue = ((int)(color.getBlue()*factor) <= 255 ? (int)(color.getBlue()*factor) : 255);
		//if it's below 0, brings it back up to 0
		newBlue = (newBlue >= 0 ? newBlue : 0);
		
		return new Color(newRed, newGreen, newBlue);
	}

}