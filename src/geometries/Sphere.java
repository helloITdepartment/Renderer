package geometries;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import primitives.*;

public class Sphere extends RadialGeometry{
	//Center of the sphere. Radius is given by superclass, RadialGeometry
	Point3D _center;
	
	//Constructors
	//Empty constructor
	public Sphere(){
		//Super calls the empty RadialGeometry constructor which sets the radius
		super();
		//Sets the other data members to default values
		_center = new Point3D();
		_material = new Material();
		_emission = new Color(0,0,0);
	}
	
	//Parameterized constructors
	public Sphere(Point3D center, Material material){
		//Sets a default radius
		super();
		//Sets the other members to parameters passed
		_center = center;
		_material = material;
	}
	
	public Sphere(double radius, Point3D center, Material material, Color emission){
		//Sets all the variables to parameters passed
		_radius = radius;
		_center = center;
		_material = material;
		_emission = emission;
	}
	
	//Copy constructor
	public Sphere(Sphere other){
		//Sets all variables to copies of other's
		_radius = other._radius;
		_center = other._center;
		_material = other.getMaterial();
		_emission = other.getEmission();
	}
		
	//Getters
	//radius getter is unnecessary as it's inherited from RadialGeometry
	public Point3D getCenter(){
		//Returns new Point3D with the same value as our _center, so that changes made at the callsite wont affect our variables
		return new Point3D(_center);
	}
	
	public Material getMaterial() {
		//Returns new Material with the same value as our _material, so that changes made at the callsite wont affect our variables
		return new Material(_material);
	}
	
	public Color getEmission(){
		//Returns new Color with the same RGB values as our _emission, so that changes made at the callsite wont affect our variables
		return new Color(_emission.getRGB());
	}
		
	//Setters
	//radius setter is unnecessary as it's inherited from RadialGeometry
	public void setCenter(Point3D center){
		//Allows for setting of protected data member _center;
		_center = center;
	}
	
	public void setMaterial(Material material) {
		//Allows for setting of protected data member _material;
		_material = material;
	}
	
	public void setEmission(Color emission) {
		//Allows for the setting of the emission data member from the passed parameter
		_emission = emission;
	}

	//Returns a (possibly empty) list of points where a given Ray would intersect our Sphere
	public List<Point3D> findIntersection(Ray r) {
		//Initializes an empty List
		List<Point3D> listToReturn = new ArrayList<>();
		
		//l is the vector from the center of the camera to the center of the sphere
		Vector l = new Vector(_center.subtract(r.getSource()));
		
		//tM is the length of the base of a right triangle made by extending the Ray to the middle of the Sphere, where l is the hypotenuse
		double tM = l.dotProduct(r.getDirection().normalize());
		
		//d is the height of the triangle, calculated by manipulating the Pythegorean formula
		double d = Math.sqrt(l.length()*l.length() - tM*tM);
		
		//If d is bigger than the radius, the ray passes over the sphere and we return and empty list
		if(d > _radius) {
			return new ArrayList<>();
		}
		
		
		//Uses the formula given in class to calculate the point(s) of intersection
		double tH = Math.sqrt(_radius*_radius - d*d);
		double t1 = tM-tH;
		double t2 = tM+tH;
		
		if(t1 >0) {
			Point3D p1 = new Point3D().add(r.getDirection().normalize().scale(t1));
			listToReturn.add(p1);
		}
		
		if(t2 >0) {
			Point3D p2 = new Point3D().add(r.getDirection().normalize().scale(t2));
			listToReturn.add(p2);
		}
		
		return listToReturn;
	}
	
	public Vector getNormal(Point3D p) {
		return new Vector((p.subtract(_center))).scale(p.distanceTo(_center));
	}
}
