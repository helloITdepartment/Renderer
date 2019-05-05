package geometries;
import java.util.ArrayList;
import java.util.List;

import primitives.*;

public class Triangle implements Geometry{
	//three points representing the three vertices of the triangle
	Point3D _p1;
	Point3D _p2;
	Point3D _p3;
	
	//Constructors
	//Empty constructor
	public Triangle(){
		//default constructor setting the points to default 3d points
		_p1 = new Point3D();
		_p2 = new Point3D();
		_p3 = new Point3D();
	}
			
	//Parameterized constructor
	public Triangle(Point3D p1, Point3D p2, Point3D p3){
		//sets the three vertices to the three parameters passed
_p1 = p1;
		_p2 = p2;
		_p3 = p3;
	}
		
	//Copy constructor
	public Triangle(Triangle other){
		//copies the vertices of the passed triangle onto the object it is called on
_p1 = other._p1;
		_p2 = other._p2;
		_p3 = other._p3;
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
	
	//Setters
	public void setP1(Point3D p1){
		//allows for setting of vertex 1 from passed parameter
_p1 = p1;
	}
	
	public void setP2(Point3D p2){
		//allows for setting of vertex 2 from passed parameter
_p2 = p2;
	}
	
	public void setP3(Point3D p3){
		//allows for setting of vertex 3 from passed parameter
_p3 = p3;
	}

	@Override
	public List<Point3D> findIntersection(Ray r) {
		// TODO Auto-generated method stub
		List<Point3D> listToReturn = new ArrayList<>();
		
		Plane planeEncompassingTriangle = new Plane(_p1, _p2, _p3);
		List<Point3D> listOfPotentialPoints = planeEncompassingTriangle.findIntersection(r);
		if(listOfPotentialPoints.isEmpty()) {
			return listToReturn;
		}else {
			Point3D potentialPoint = listOfPotentialPoints.get(0);
			
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

}
