package elements;

import java.awt.Color;

import primitives.Point3D;
import primitives.Vector;

public class PointLight extends Light implements LightSource{

    //point in three dimensional space representing the position of the light
    Point3D _position;
    //the three factors for attenuation with a distance
    //Constant attenuation 
    double _Kc;
    //Linear Attenuation
    double _Kl;
    //Quadratic attenuation
    double _Kq;

    //default constructor
    public PointLight()
    {
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
    
    public PointLight(Point3D position)
    {
    	//Sets the position to the origin
    	_position = position;
    	//Sets _kc, _kl, _kq to default values that work nicely
    	_Kc = 0.00000000001;
    	_Kl = 0.00000000001;
    	_Kq = 0.00000000001;
    	//Sets color to a default white
    	_color = new Color(255, 255, 255);
    	//Sets _kA to a default 1.0
    	_kA = 1.0;
    }

    //constructor which receives the position of the vector and the factors of attenuation
    public PointLight(Point3D pos, double Kc, double Kl, double Kq)
    {
    	//Sets data members to parameters passed
    	_position = pos;
    	_Kc = Kc;
    	_Kl = Kl;
    	_Kq = Kq;
    	//Sets _color to a default white
    	_color = new Color(255, 255, 255);
    	//Sets _kA to a default 1.0
    	_kA = 1.0;

    }

    //constructor which receives the position of the vector and the factors of attenuation and color of the light
    public PointLight(Point3D pos, double Kc, double Kl, double Kq, Color color)
    {
    	//Sets data members to parameters passed
    	_position = pos;
    	_Kc = Kc;
    	_Kl = Kl;
    	_Kq = Kq;
    	_color = color;
    	//Sets _kA to a default 1.0
    	_kA = 1.0;

    }

    //constructor which receives the position of the vector and the factors of attenuation, color of the light, and kA
    public PointLight(Point3D pos, double Kc, double Kl, double Kq, Color color, double kA)
    {
    	//Sets all data members to parameters passed
    	_position = pos;
    	_Kc = Kc;
    	_Kl = Kl;
    	_Kq = Kq;
    	_color = color;
    	_kA = kA;

    }
    
    //Copy constructor
    public PointLight(PointLight other) {
    	//sets all internal variables to copies of other's
    	_position = other.getPosition();
    	_Kc = other.getKc();
    	_Kl = other.getKl();
    	_Kq = other.getKq();
    	_color = other.getColor();
    	_kA = other.getKa();
    }

    //getter for the position of the point light
    public Point3D getPosition() {
    	return _position;
    }
    
    //getters for the three measurements of attenuation Kc, Kl, Kq
    public double getKc()
    {
        return new Double(_Kc);
    }
    
    public double getKl()
    {
        return new Double(_Kl);
    }
    
    public double getKq()
    {
        return new Double(_Kq);
    }
    
    public Color getColor() {
    	//REturns a new Color with the same RGB values as our _color so that changes at the callsite won't affect our variables
    	return new Color(_color.getRGB());
    }
    
    public double getKa() {
    	return new Double(_kA);
    }

    
    //allows for the setting of the position of the light
    public void set_position(Point3D position) {
    	//Allows for setting of private data member _position
        _position = position;
    }
    //allows for the setting of the factors of attenuation
    public void setKc(double Kc) {
    	//Allows for setting of private data member _Kc
        _Kc = Kc;
    }

    public void setKl(double Kl) {
    	//Allows for setting of private data member _Kl
        _Kl = Kl;
    }

    public void setKq(double Kq) {
    	//Allows for setting of private data member _Kq
        _Kq = Kq;
    }
    
    //setter for the color of the spotlight
    public void setColor(Color color) {
    	//Allows for setting of private data member _color
    	_color = color;
    }
    
    public void setKa(double kA) {
    	//Allows for setting of private data member _kA
    	_kA = kA;
    }
    
	//Returns a version of _color, where each component is scaled by _kA, representing the color
	public Color getIntensity(Point3D point) {
		double distance = _position.distanceTo(point);
		double factor = (_Kc + Math.pow(_Kl, distance) + Math.pow(_Kq, distance*distance));
				//(_Kc + _Kl/distance + _Kq/(distance*distance));
		
		return scaleColor(getColor(), factor);
	}

	//Return the vector that a photon coming from this PointLight would travel along to intersect the given Point
	public Vector getL(Point3D point) {
		return new Vector(point.subtract(_position));
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