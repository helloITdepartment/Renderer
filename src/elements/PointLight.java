package elements;

import java.awt.Color;

import primitives.Point3D;
import primitives.Vector;

public class PointLight extends Light implements LightSource{

    //point in three dimensional space representing the position of the light
    Point3D _position;
    //What dis? plz to explain
    //the three factors for attenuation with a distance
    //Attenuates constantly
    double _Kc;
    //Attenuates linearly
    double _Kl;
    //Attenuates quadratically
    double _Kq;

    //default constructor
    public PointLight()
    {
    	_position = new Point3D();
    	_Kc = 0.00000000001;
    	_Kl = 0.00000000001;
    	_Kq = 0.00000000001;
    }

    //constructor which receives the position of the vector and the factors of attenuation
    public PointLight(Point3D pos, double Kc, double Kl, double Kq)
    {
    	_position = pos;
    	_Kc = Kc;
    	_Kl = Kl;
    	_Kq = Kq;
    	_color = new Color(255, 255, 255);

    }

    //constructor which receives the position of the vector and the factors of attenuation and color of the light
    public PointLight(Point3D pos, double Kc, double Kl, double Kq, Color color)
    {
    	_position = pos;
    	_Kc = Kc;
    	_Kl = Kl;
    	_Kq = Kq;
    	_color = color;
    	_kA = 1.0;

    }

    //constructor which receives the position of the vector and the factors of attenuation, color of the light, and kA
    public PointLight(Point3D pos, double Kc, double Kl, double Kq, Color color, double kA)
    {
    	_position = pos;
    	_Kc = Kc;
    	_Kl = Kl;
    	_Kq = Kq;
    	_color = color;
    	_kA = kA;

    }

    //getter for the position of the point light
    public Point3D getposition() {
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

    
    //allows for the setting of the position of the light
    public void set_position(Point3D position) {
        _position = position;
    }
    //allows for the setting of the factors of attenuation
    public void setKc(double Kc) {
        _Kc = Kc;
    }

    public void setKl(double Kl) {
        _Kl = Kl;
    }

    public void setKq(double Kq) {
        _Kq = Kq;
    }
    
    //setter for the color of the spotlight
    public void setColor(Color color) {
    	_color = color;
    }
    
	public Color getIntensity(Point3D point) {
		double distance = _position.distanceTo(point);
		int newRed = _color.getRed()/((int) (_Kc + _Kl*distance + _Kq*distance*distance));
		int newGreen = _color.getGreen()/((int) (_Kc + _Kl*distance + _Kq*distance*distance));
		int newBlue = _color.getBlue()/((int) (_Kc + _Kl*distance + _Kq*distance*distance));
		
		return new Color(newRed, newGreen, newBlue);
	}

	public Vector getL(Point3D point) {
		return new Vector(point.subtract(_position)).normalize();
	}
}