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
		super();
		_center = new Point3D();
		_material = new Material();
		_emission = new Color(0,0,0);
	}
	
	//Parameterized constructors
	public Sphere(Point3D center, Material material){
		super();
		_center = center;
		_material = material;
	}
	
	public Sphere(double radius, Point3D center, Material material, Color emission){
		_radius = radius;
		_center = center;
		_material = material;
		_emission = emission;
	}
	
	//Copy constructor
	public Sphere(Sphere other){
		_radius = other._radius;
		_center = other._center;
		_material = other.getMaterial();
		_emission = other.getEmission();
	}
		
	//Getters
	//radius getter is unnecessary as it's inherited from RadialGeometry
	public Point3D getCenter(){
		return new Point3D(_center);
	}
	
	public Material getMaterial() {
		return new Material(_material);
	}
	
	public Color getEmission(){
		//Returns new Color with the same RGB values as our _emission, so that changes made at the callsite wont affect our variables
		return new Color(_emission.getRGB());
	}
		
	//Setters
	//radius setter is unnecessary as it's inherited from RadialGeometry
	public void setCenter(Point3D center){
		_center = center;
	}
	
	public void setMaterial(Material material) {
		_material = material;
	}
	
	public void setEmission(Color emission) {
		//Allows for the setting of the emission data member from the passed parameter
		_emission = emission;
	}

	public List<Point3D> findIntersection(Ray r) {
		// TODO Auto-generated method stub
		List<Point3D> listToReturn = new ArrayList<>();
		
		Vector l = new Vector(_center.subtract(new Point3D(0,0,0)));
		double tM = l.dotProduct(r.getDirection().normalize());
		double d = Math.sqrt(l.length()*l.length() - tM*tM);
		
		if(d > _radius) {
			return new ArrayList<>();
		}
		
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
