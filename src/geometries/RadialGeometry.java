package geometries;

import java.util.List;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

//An abstract class representing Geometries that have some sort of radius
public abstract class RadialGeometry extends Geometry{
	//radius determines the radius of the shape
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
		//sets the radius of the called-on object to the radius of the passed parameter
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
	
	//Each RadialGeometry must implement a method that:
	//Returns a vector normal to the RadialGeometry at a given Point
	public abstract Vector getNormal(Point3D p);

	//Returns a list of points where a given Ray would intersect that RadialGeometry
	public abstract List<Point3D> findIntersection(Ray r);

}
