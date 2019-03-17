package primitives;

public class Vector{
	Point3D _head;
	
	//Constructors
	//Empty constructor
	public Vector(){
		_head = new Point3D();
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
		return new Point3D(_head);
	}
		
	//Setters
	public void setHead(Point3D head){
		_head = head;
	}

	//Calls the compareTo method already implemented for Point3D to compare the heads of 2 vectors. Returns 1 if equal, 0 otherwise
	public int compareTo(Vector other){
		return _head.compareTo(other.getHead());
	}

	//Creates new x, y, and z coordinates by adding the two heads of the vectors, returns a new Vector with a head with the new Coordinates
	public Vector add(Vector other){
		Point3D newHead = _head.add(other);
		
		return new Vector(newHead);
	}

	//Creates new x, y, and z coordinates by subtracting the two heads of the vectors, returns a new Vector with a head with the new Coordinates
	public Vector subtract(Vector other){
		Point3D newHead = _head.subtract(other);
		
		return new Vector(newHead);
	}

	//Computes the cross product by multiplying and subtracting the appropriate values
	public Vector crossProduct(Vector other){
		Coordinate newX = new Coordinate((_head.getY().getCoordinate() * other.getHead().getZ().getCoordinate()) - (_head.getZ().getCoordinate() * other.getHead().getY().getCoordinate()));
		Coordinate newY = new Coordinate((_head.getZ().getCoordinate() * other.getHead().getX().getCoordinate()) - (_head.getX().getCoordinate() * other.getHead().getZ().getCoordinate()));
		Coordinate newZ = new Coordinate((_head.getX().getCoordinate() * other.getHead().getY().getCoordinate()) - (_head.getY().getCoordinate() * other.getHead().getX().getCoordinate()));
		
		Point3D newHead = new Point3D(newX, newY, newZ);
		
		return new Vector(newHead);
	}

	//Uses the extended Pythagorean theorem to compute the length of the vector
	public double length(){
		return Math.sqrt((_head.getX().getCoordinate() * _head.getX().getCoordinate()) + ((_head.getY().getCoordinate() * _head.getY().getCoordinate()) + ((_head.getZ().getCoordinate() * _head.getZ().getCoordinate()))));
	}

	//Divides each component of the vector by the length to normalize it and returns a new vector with the appropriate head
	public Vector normalize(){
		double len = length();
		
		Coordinate newX = new Coordinate(_head.getX().getCoordinate()/len);
		Coordinate newY = new Coordinate(_head.getY().getCoordinate()/len);
		Coordinate newZ = new Coordinate(_head.getZ().getCoordinate()/len);
		
		Point3D newHead = new Point3D(newX, newY, newZ);
		
		return new Vector(newHead);
	}

	//Multiplies all the corresponding components of the vectors and returns a double of their sum
	public double dotProduct(Vector other){
		return (_head.getX().getCoordinate() * other.getHead().getX().getCoordinate()) + (_head.getY().getCoordinate() * other.getHead().getY().getCoordinate()) + (_head.getZ().getCoordinate() * other.getHead().getZ().getCoordinate());
	}
} 
