package primitives;

public class Vector{
	Point3D _head;
	
	//Constructors
	//Empty constructor
	public Vector(){
		
	}
			
	//Parameterized constructor
	public Vector(Point3D head){
		_head = head;
	}
		
	//Copy constructor
	public Vector(Vector other){
		_head = other._head;
	}
		
	//Getters
	public Point3D getHead(){
		return _head;
	}
		
	//Setters
	public void setHead(Point3D head){
		_head = head;
	}
}
