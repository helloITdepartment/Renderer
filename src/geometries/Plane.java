package geometries;
import java.util.List;
import java.util.ArrayList;

import primitives.*;

public class Plane extends Geometry{
	//Any plane can be uniquely represented by 3 points
	Point3D _p1;
	Point3D _p2;
	Point3D _p3;
	//The vector normal to the plane
	Vector _normal;

	//Constructors
	//Empty constructor
	public Plane(){
		//Creates a plane with three default points and a default normal to the plane
		_p1 = new Point3D();
		_p2 = new Point3D();
		_p3 = new Point3D();
		_normal = new Vector();
		
		_material = new Material();
	}

	//Parameterized constructor
	public Plane(Point3D p1, Point3D p2, Point3D p3, Material material){
		//creates a plane with three points passed in as parameters and calculates the normal vector, and sets the material to the one passed in
		_p1 = p1;
		_p2 = p2;
		_p3 = p3;
		_normal = getNormal(p1);
		
		_material = material;
	}

	//Copy constructor
	public Plane(Plane other){
		//copies the points from the passed plane to the plane it is called on
		_p1 = other._p1;
		_p2 = other._p2;
		_p3 = other._p3;
		_normal = getNormal(_p1);
		
		_material = other.getMaterial();
	}

	//Getters
	public Point3D getP1(){
		//Returns new Point3D with the same value as our _p1, so that changes made at the callsite wont affect our variables
		return new Point3D(_p1);
	}

	public Point3D getP2(){
		//Returns new Point3D with the same value as our _p2, so that changes made at the callsite wont affect our variables
		return new Point3D(_p2);
	}

	public Point3D getP3(){
		//Returns new Point3D with the same value as our _p3, so that changes made at the callsite wont affect our variables
		return new Point3D(_p3);
	}
	
	public Material getMaterial(){
		//Returns new Point3D with the same value as our _p1, so that changes made at the callsite wont affect our variables
		return new Material(_material);
	}

	//Setters
	public void setP1(Point3D p1){
		//Allows for the setting of the p1 data member from the passed parameters
		_p1 = p1;
	}

	public void setP2(Point3D p2){
		//Allows for the setting of the p2 data member from the passed parameters
		_p2 = p2;
	}

	public void setP3(Point3D p3){
		//Allows for the setting of the p3 data member from the passed parameters
		_p3 = p3;
	}
	
	public void setMaterial(Material material){
		//Allows for the setting of the material data member from the passed parameters
		_material = material;
	}

	public List<Point3D> findIntersection(Ray r) {
		//Initializes an empty list that will contain the point(s) of intersection, if any
		List<Point3D> listToReturn = new ArrayList<>();

		//denominator holds the result of the dot product between the ray and the plane
		double denominator = _normal.dotProduct(r.getDirection());

		//if the dot product returns zero it means the ray is perpendicular to the normal which is perpendicular to the plane and therefore runs parallel to the plane and does not intersect
		if(denominator == 0) {
			//returns an empty list of intersection
			return listToReturn;
		}

		//t holds the distance from the source of the ray (camera) to the plane
		double t = (new Vector(_p1.subtract(r.getSource())).dotProduct(_normal))/denominator;

		//We scale the Ray by t to get the point of intersection of the ray and the plane
		Point3D intersectedPoint = new Point3D(r.getSource().add(r.getDirection().scale(t)));

		//we add the new point of intersection to our list
		listToReturn.add(intersectedPoint);

		//return the list of intersections
		return listToReturn;
	}
	
	public Vector getNormal(Point3D p) {
		return new Vector(_p2.subtract(_p1)).crossProduct(new Vector(_p3.subtract(_p1))).normalize();
	}
}
