package primitives;

public class Vector{
	//A data member to represent the entire vector, assumed to start at the origin and end at _head
	Point3D _head;

	//Constructors
	//Empty constructor
	public Vector(){
		_head = new Point3D();
	}

	//Constructor straight from doubles to Vector
	public Vector(double x, double y, double z){
		_head = new Point3D(new Coordinate(x), new Coordinate(y), new Coordinate(z));
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

	//Takes in a double and scales the vector up or down accordingly
	public Vector scale(double d){
		return new Vector(_head.getX().getCoordinate()*d, _head.getY().getCoordinate()*d, _head.getZ().getCoordinate()*d);
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

		//Throws an error if trying to normalize the zero vector as it will result in a divide-by-zero situation
		if(len == 0.0){
			throw new IllegalArgumentException("Cannot normalize the zero vector. Will result in divide by zero");
		}

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

	//A toString method to represent the head of the vector as a string that's easy for humans to understand
	public String toString(){
		String ret = "X: ";
		ret += _head.getX().getCoordinate();
		ret += " Y: ";
		ret += _head.getY().getCoordinate();
		ret += " Z: ";
		ret += _head.getZ().getCoordinate();
		return ret;
	}
} 
