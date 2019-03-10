package geomentries;
import primitives.*;

public class Plane {
	Point3D _p1;
	Point3D _p2;
	Point3D _p3;
	
	//Constructors
	//Empty constructor
	public Plane(){
		
	}
			
	//Parameterized constructor
	public Plane(Point3D p1, Point3D p2, Point3D p3){
		_p1 = p1;
		_p2 = p2;
		_p3 = p3;
	}
		
	//Copy constructor
	public Plane(Plane other){
		_p1 = other._p1;
		_p2 = other._p2;
		_p3 = other._p3;
	}
		
	//Getters
	public Point3D getP1(){
		return _p1;
	}
	
	public Point3D getP2(){
		return _p2;
	}
	
	public Point3D getP3(){
		return _p3;
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

}
