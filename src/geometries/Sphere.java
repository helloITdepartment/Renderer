package geometries;
import java.util.ArrayList;
import java.util.List;

import primitives.*;

public class Sphere extends RadialGeometry implements Geometry{
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
}
