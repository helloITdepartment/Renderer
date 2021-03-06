package geometries;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import primitives.*;

public class Cylinder extends RadialGeometry{
	//Ray representing the central axis around which the cylinder revolves, at a radius inherited from RadialGeometry
	Ray _axisRay;

	//Constructors
	//Empty constructor
	public Cylinder(){
		//use super class to create basic geometric forms
		super();
		//Implement the axis ray using a Ray constructor
		_axisRay = new Ray();
	}

	//Parameterized constructors
	public Cylinder(Ray axisRay){
		//use super class to create basic geometric form
		super();
		//set the axisRay with the passed axisRay
		_axisRay = axisRay;
	}

	public Cylinder(double radius, Ray axisRay){
		//constructor given the radius and axisRay as parameters
		_radius = radius;
		_axisRay = axisRay;
	}

	//Copy constructor
	public Cylinder(Cylinder other){
		//copies the data members from the passed cylinder to the cylinder it was called on
		_radius = other._radius;
		_axisRay = other._axisRay;
	}

	//Getters
	//radius getter is unnecessary as it's inherited from RadialGeometry
	public Ray getAxisRay(){
		//Returns new Ray with the same value as our _axisRay, so that changes made at the callsite wont affect our variables
		return new Ray(_axisRay);
	}
	
	public Material getMaterial() {
		//Returns new Material with the same value as our _material, so that changes made at the callsite wont affect our variables
		return new Material(_material);
	}

	//Setters
	//radius setter is unnecessary as it's inherited from RadialGeometry
	public void setAxisRay(Ray axisRay){
		//allows us to set a value for the axisRay data member
		_axisRay = axisRay;
	}

	//Returns a (possibly empty) list of points where a given Ray r would intersect our Cylinder
	public List<Point3D> findIntersection(Ray r) {
		//Unimplemented for now as Cylinder is an extra project
		List<Point3D> listToReturn = new ArrayList<>();
		return listToReturn;
	}
	
	//Returns a vector normal to the cylinder at a given point
	public Vector getNormal(Point3D p) {
		//Unimplemented for now as Cylinder is an extra project
		return new Vector();
	}

	public Color getEmission() {
		//Returns a new Color with the same RGB values as out _emission, so that changes made at the callsite wont affect our variables
		return new Color(_emission.getRGB());
	}
}
