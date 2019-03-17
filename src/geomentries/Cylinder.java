package geomentries;
import java.util.List;

import primitives.*;

public class Cylinder extends RadialGeometry implements Geometry{
	Ray _axisRay;
	
	//Constructors
	//Empty constructor
	public Cylinder(){
		super();
		_axisRay = new Ray();
	}
			
	//Parameterized constructors
	public Cylinder(Ray axisRay){
		super();
		_axisRay = axisRay;
	}
	
	public Cylinder(double radius, Ray axisRay){
		_radius = radius;
		_axisRay = axisRay;
	}
		
	//Copy constructor
	public Cylinder(Cylinder other){
		_radius = other._radius;
		_axisRay = other._axisRay;
	}
		
	//Getters
	//radius getter is unnecessary as it's inherited from RadialGeometry
	public Ray getAxisRay(){
		return new Ray(_axisRay);
	}
	
	//Setters
	//radius setter is unnecessary as it's inherited from RadialGeometry
	public void setAxisRay(Ray axisRay){
		_axisRay = axisRay;
	}

	public List<Point3D> findIntersection(Ray r) {
		// TODO Auto-generated method stub
		return null;
	}
}
