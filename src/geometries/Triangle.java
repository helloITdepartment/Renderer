package geometries;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import primitives.*;

public class Triangle extends Geometry implements FlatGeometry{
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
		_material = new Material();
		_emission = new Color(0,0,0);
	}

	//Parameterized constructor
	public Triangle(Point3D p1, Point3D p2, Point3D p3, Material material, Color emission){
		//sets the three vertices, material, and emission color to the three parameters passed
		_p1 = p1;
		_p2 = p2;
		_p3 = p3;
		
		_material = material;
		
		_emission = emission;
	}

	//Copy constructor
	public Triangle(Triangle other){
		//copies the vertices of the passed triangle, its material, and its emission color onto the object it is called on
		_p1 = other._p1;
		_p2 = other._p2;
		_p3 = other._p3;
		_material = other.getMaterial();
		_emission = other.getEmission();
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
		//Returns new Material with the same value as our _material, so that changes made at the callsite wont affect our variables
		return new Material(_material);
	}
	
	public Color getEmission(){
		//Returns new Color with the same RGB values as our _emission, so that changes made at the callsite wont affect our variables
		return new Color(_emission.getRGB());
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
	
	public void setMaterial(Material material){
		//Allows for the setting of the material data member from the passed parameter
		_material = material;
	}
	
	public void setEmission(Color emission) {
		//Allows for the setting of the emission data member from the passed parameter
		_emission = emission;
	}

	//Returns a List of points that a given ray would intersect with on this Triangle
	public List<Point3D> findIntersection(Ray r) {
		//Initializes an empty list that will contain the point(s) of intersection, if any
		List<Point3D> listToReturn = new ArrayList<>();

		//Creates a plane representing the plane that the triangle lives in
		Plane planeEncompassingTriangle = new Plane(_p1, _p2, _p3, _material, _emission);
		//Finds the point that the ray would intersect into that plane at
		List<Point3D> listOfPotentialPoints = planeEncompassingTriangle.findIntersection(r);
		//if it missed the plane entirely, it has no chance of hitting the triangle and we return an empty list
		if(listOfPotentialPoints.isEmpty()) {
			return listToReturn;
		}else { //otherwise we check the point using the formula given in class to see if it's in the triangle
			Point3D potentialPoint = listOfPotentialPoints.get(0);

			//Side 1
			Vector v11 = new Vector(_p1);
			Vector v12 = new Vector(_p2);
			Vector n1 = v11.crossProduct(v12).scale(1/(v11.crossProduct(v12).length()));
			
			double sign1 = Math.signum(((new Vector(potentialPoint)).subtract(new Vector()).dotProduct(n1)));

			//Side 2
			Vector v21 = new Vector(_p2);
			Vector v22 = new Vector(_p3);
			Vector n2 = v21.crossProduct(v22).scale(1/(v21.crossProduct(v22).length()));
			
			double sign2 = Math.signum(((new Vector(potentialPoint)).subtract(new Vector()).dotProduct(n2)));
			
			//Side 3
			Vector v31 = new Vector(_p3);
			Vector v32 = new Vector(_p1);
			Vector n3 = v31.crossProduct(v32).scale(1/(v31.crossProduct(v32).length()));
			
			double sign3 = Math.signum(((new Vector(potentialPoint)).subtract(new Vector()).dotProduct(n3)));
			
			//if all the signs are equal, the point is in the triangle, add it to the list
			if(sign1 == sign2 && sign2 == sign3) {
				listToReturn.add(potentialPoint);
			}

			//And return the list, empty or otherwise
			return listToReturn;
		}
	}
	
	//Returns the Vector normal to the triangle at the given point
	public Vector getNormal(Point3D p) {
		//Again by embedding the triangle in a plane and using Plane's getNormal function
		return new Plane(_p1, _p2, _p3, _material, _emission).getNormal(p);
	}

}
