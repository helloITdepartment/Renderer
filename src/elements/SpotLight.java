package elements;

import java.awt.Color;

import primitives.Point3D;
import primitives.Vector;

public class SpotLight extends PointLight implements LightSource{
    //direction of the spotlight lighting
    Vector _direction;

    //constructor which accepts a vector as a parameter and sets the direction of the spotlight accordingly
    public SpotLight(Point3D position, Vector direction, Color color){
    	_position = position;
        _direction = direction.normalize();
        _color = color;
        _kA = 1.0;
    }
    
    public SpotLight(Point3D position, Vector direction, Color color, double kA){
    	_position = position;
        _direction = direction.normalize();
        _color = color;
        _kA = kA;
    }
    
    public SpotLight(Point3D position, Vector direction){
    	_position = position;
        _direction = direction.normalize();
        _color = new Color(255, 255, 255);
        _kA = 1.0;
    }
    
    public SpotLight(Vector direction){
    	_position = new Point3D();
        _direction = direction.normalize();
        _color = new Color(255, 255, 255);
        _kA = 1.0;
    }

    //default constructor which creates a default vector as the direction of the spotlight
    public SpotLight(){
        _direction = new Vector();
    }

    //getter for the direction of the spotlight
    public Vector getdirection() {
        return _direction;
    }
    
    //getter for the color of the spotlight
    public Color getColor() {
    	return new Color(_color.getRGB());
    }

    //setter for the direction of the spotlight
    public void setdirection(Vector direction) {
        _direction = direction;
    }
    
	public Color getIntensity(Point3D point) {
		double distance = _position.distanceTo(point);
		double factor = (_direction.dotProduct(getL(point))) / (_Kc + _Kl*distance + _Kq*distance*distance);
		return scaleColor(getColor(), factor);
	}

	public Vector getL(Point3D point) {
		return new Vector(point.subtract(_position)).normalize();
	}
	
	public Color scaleColor(Color color, double factor) {
		int newRed = ((int)(color.getRed()*factor) <= 255 ? (int)(color.getRed()*factor) : 255);
		newRed = (newRed >= 0 ? newRed : 0);
		int newGreen = ((int)(color.getGreen()*factor) <= 255 ? (int)(color.getGreen()*factor) : 255);
		newGreen = (newGreen >= 0 ? newGreen : 0);
		int newBlue = ((int)(color.getBlue()*factor) <= 255 ? (int)(color.getBlue()*factor) : 255);
		newBlue = (newBlue >= 0 ? newBlue : 0);
		
		return new Color(newRed, newGreen, newBlue);
	}

}