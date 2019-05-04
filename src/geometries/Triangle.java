package geometries;
import java.util.ArrayList;
import java.util.List;

import primitives.*;

public class Triangle implements Geometry{
	Point3D _p1;
	Point3D _p2;
	Point3D _p3;
	
	//Constructors
	//Empty constructor
	public Triangle(){
		_p1 = new Point3D();
		_p2 = new Point3D();
		_p3 = new Point3D();
	}
			
	//Parameterized constructor
	public Triangle(Point3D p1, Point3D p2, Point3D p3){
		_p1 = p1;
		_p2 = p2;
		_p3 = p3;
	}
		
	//Copy constructor
	public Triangle(Triangle other){
		_p1 = other._p1;
		_p2 = other._p2;
		_p3 = other._p3;
	}
		
	//Getters
	public Point3D getP1(){
		return new Point3D(_p1);
	}
	
	public Point3D getP2(){
		return new Point3D(_p2);
	}
	
	public Point3D getP3(){
		return new Point3D(_p3);
	}
	
	//Setters
	public void setP1(Point3D p1){
		_p1 = p1;
	}
	
	public void setP2(Point3D p2){
		_p2 = p2;
	}
	
	public void setP3(Point3D p3){
		_p3 = p3;
	}
	
	private Vector findNormal(){
		return new Vector();
	}

	@Override
	public List<Point3D> findIntersection(Ray r) {
		// TODO Auto-generated method stub
		List<Point3D> listToReturn = new ArrayList<>();
		
		double d = findNormal().dotProduct(new Vector());
		//Check the dot product between the vector normal to the triangle and the vector indicating the direction of the Ray.
		//If it is 0, the ray and the triangle are parallel and return an empty List
		double numerator = findNormal().dotProduct(new Vector(r.getSource())) + d;
		double denominator = findNormal().dotProduct(r.getDirection());
		
		if(denominator == 0) {
			System.out.println("Den was 0");
			return new ArrayList<>();
		}
		
		//If t is negative, the triangle is behind the camera and should not be visible, and should also return an empty List
		double t = numerator/denominator;
		if(t < 0) {
			System.out.println("T was negative");

			return new ArrayList<>();
		}
		
		//The point along the way that we're investigating to see if it intersects with the triangle
		Point3D potentialPoint = (new Vector(r.getDirection())).scale(t).getHead();
		
		//Side 1
		Vector v11 = new Vector(_p1);
		Vector v12 = new Vector(_p2);
		Vector n1 = v11.crossProduct(v12).scale(1/(v11.crossProduct(v12).length()));
		double sign1 = ((new Vector(potentialPoint)).subtract(new Vector()).dotProduct(n1))/Math.abs(((new Vector(potentialPoint)).subtract(new Vector()).dotProduct(n1)));
		
		//Side 2
		Vector v21 = new Vector(_p1);
		Vector v22 = new Vector(_p2);
		Vector n2 = v21.crossProduct(v22).scale(1/(v21.crossProduct(v22).length()));
		double sign2 = ((new Vector(potentialPoint)).subtract(new Vector()).dotProduct(n2))/Math.abs(((new Vector(potentialPoint)).subtract(new Vector()).dotProduct(n2)));
		
		//Side 3
		Vector v31 = new Vector(_p1);
		Vector v32 = new Vector(_p2);
		Vector n3 = v31.crossProduct(v32).scale(1/(v31.crossProduct(v32).length()));
		double sign3 = ((new Vector(potentialPoint)).subtract(new Vector()).dotProduct(n3))/Math.abs(((new Vector(potentialPoint)).subtract(new Vector()).dotProduct(n3)));
		
		if(sign1 == sign2 && sign2 == sign3) {
			listToReturn.add(potentialPoint);
		}
		
		return listToReturn;
	}

}
