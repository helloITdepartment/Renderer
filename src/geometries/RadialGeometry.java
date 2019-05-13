package geometries;

import java.util.List;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public abstract class RadialGeometry extends Geometry{
	//radius determines the radius of the shape for which the ray is going to shine through
	double _radius;

	//Constructors
	//Empty constructor
	public RadialGeometry(){
		//default value of 0.0 as the radius of the geometric shape
		_radius = 0.0;
	}

	//Parameterized constructor
	public RadialGeometry(double radius){
		//sets the radius to the value passed as a parameter
		_radius = radius;
	}

	//Copy constructor
	public RadialGeometry(RadialGeometry other){
		//sets the radius of the called on object to the radius of the passed parameter
		_radius = other._radius;
	}

	//Getters
	public double getRadius(){
		//Returns new double with the same value as our _radius, so that changes made at the callsite wont affect our variables
		return new Double(_radius);
	}

	//Setters
	public void setRadius(double radius){
		//allows access to the radius data member
		_radius = radius;
	}
	
	public abstract Vector getNormal(Point3D p);

	public abstract List<Point3D> findIntersection(Ray r);

}
