package geomentries;
import primitives.*;

public class Sphere extends RadialGeometry{
	Point3D _center;
	
	//Constructors
	//Empty constructor
	public Sphere(){
		super();
		_center = new Point3D();
	}
	
	//Parameterized constructors
	public Sphere(Point3D center){
		super();
		_center = center;
	}
	
	public Sphere(double radius, Point3D center){
		_radius = radius;
		_center = center;
	}
	
	//Copy constructor
	public Sphere(Sphere other){
		_radius = other._radius;
		_center = other._center;
	}
		
	//Getters
	//radius getter is unnecessary as it's inherited from RadialGeometry
	public Point3D getCenter(){
		return new Point3D(_center);
	}
		
	//Setters
	//radius setter is unnecessary as it's inherited from RadialGeometry
	public void setCenter(Point3D center){
		_center = center;
	}

}
