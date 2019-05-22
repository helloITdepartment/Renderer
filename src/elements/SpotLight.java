package elements;

import java.awt.Color;

import primitives.Point3D;
import primitives.Vector;

public class SpotLight extends PointLight implements LightSource{
    //direction of the spotlight lighting
    Vector _direction;

    //constructor which accepts a vector as a parameter and sets the direction of the spotlight accordingly
    public SpotLight(Vector direction){
        _direction = direction.normalize();
    }

    //default constructor which creates a default vector as the direction of the spotlight
    public SpotLight(){
        _direction = new Vector();
    }

    //getter for the direction of the spotlight
    public Vector getdirection() {
        return _direction;
    }

    //setter for the direction of the spotlight
    public void setdirection(Vector direction) {
        _direction = direction;
    }
    
	public Color getIntensity(Point3D point) {
		double distance = _position.distanceTo(point);
		int newRed = (int) (_color.getRed()*(_direction.dotProduct(getL(point))) /((int) (_Kc + _Kl*distance + _Kq*distance*distance)));
		int newGreen = (int) (_color.getGreen()*(_direction.dotProduct(getL(point))) /((int) (_Kc + _Kl*distance + _Kq*distance*distance)));
		int newBlue = (int) (_color.getBlue()*(_direction.dotProduct(getL(point))) /((int) (_Kc + _Kl*distance + _Kq*distance*distance)));
		
		return new Color(newRed, newGreen, newBlue);
	}

	public Vector getL(Point3D point) {
		return new Vector(point.subtract(_position)).normalize();
	}

}