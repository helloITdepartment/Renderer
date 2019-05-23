package primitives;

public class Point3D extends Point2D{
	//only need to add Z coordinate to this class as super has X and Y
	Coordinate _z;

	//Constructors
	//Empty constructor
	public Point3D(){
		//default constructor, calling super for points x,y and a default set for Z
		super();
		_z = new Coordinate();
	}

	//Parameterized constructors
	public Point3D(Coordinate z){
		//call constructor of super class and set new Z value
		super();
		_z = z;
	}

	public Point3D(Coordinate x, Coordinate y, Coordinate z){
		//call constructor of super class passing it two coordinates and setting Z value
		super(x, y);
		_z = z;
	}

	public Point3D(double x, double y, double z) {
		//Constructor that takes in doubles and converts them to coordinates before setting them
		super(new Coordinate(x), new Coordinate(y));
		_z = new Coordinate(z);
	}

	//Copy constructor
	//Sets the data members of our Point3D to be the same as another passed in
	public Point3D(Point3D other){
		super(other.getX(), other.getY());
		_z = other._z;
	}

	//Getters
	//getX and getY are unnecessary because they are inherited from Point2D
	public Coordinate getZ(){
		//Returns new coordinate with the same value as our _z, so that changes made at the callsite wont affect our variables
		return new Coordinate(_z);
	}

	//Setters
	//setX and setY are unnecessary because they are inherited from Point2D
	public void setZ(Coordinate z){
		//allows for setting of protected data member _z
		_z = z;
	}

	//Checks to make sure corresponding values match and returns 1 if they all do, returns 0 if any of them mismatch
	public int compareTo(Point3D other){
		//Checking to make sure the x's match
		if(_x.getCoordinate() == other.getX().getCoordinate()){
			//And now the y's
			if(_y.getCoordinate() == other.getY().getCoordinate()){
				//And finally the z's
				return (_z.getCoordinate() == other.getZ().getCoordinate()) ? 1 : 0;
			}else{
				return 0;
			}
		}else{
			return 0;
		}
	}

	//Vector addition. Returns the point that a vector would reach if started at this point
	public Point3D add(Vector v){
		//Adds corresponding x, y, and z values and returns a new Point3D
		Coordinate newX = super.getX().add(v.getHead().getX());
		Coordinate newY = super.getY().add(v.getHead().getY());
		Coordinate newZ = _z.add(v.getHead().getZ());

		return new Point3D(newX, newY, newZ);
	}

	//Point addition. Adds corresponding x, y, and z values and returns a new Point3D
	public Point3D add(Point3D other){
		Coordinate newX = getX().add(other.getX());
		Coordinate newY = getY().add(other.getY());
		Coordinate newZ = getZ().add(other.getZ());

		return new Point3D(newX, newY, newZ);
	}

	//Vector subtraction. Returns the point that a vector would reach if flipped around and started at this point
	public Point3D subtract(Vector v){
		//Subtracts corresponding x, y, and z values and returns a new Point3D	
		Coordinate newX = super.getX().subtract(v.getHead().getX());
		Coordinate newY = super.getY().subtract(v.getHead().getY());
		Coordinate newZ = _z.subtract(v.getHead().getZ());

		return new Point3D(newX, newY, newZ);
	}

	//Point subtraction. Subtracts corresponding x, y, and z values and returns a new Point3D
	public Point3D subtract(Point3D other){
		Coordinate newX = getX().subtract(other.getX());
		Coordinate newY = getY().subtract(other.getY());
		Coordinate newZ = getZ().subtract(other.getZ());

		return new Point3D(newX, newY, newZ);
	}

	//Calculates the distance between two points by using the extended pythagorean formula
	public double distanceTo(Point3D other) {
		//Finds the difference between, corresponding x, y, and z values
		double xDifference = _x.subtract(other.getX()).getCoordinate();
		double yDifference = _y.subtract(other.getY()).getCoordinate();
		double zDifference = _z.subtract(other.getZ()).getCoordinate();
		//Squares them, sums them, and returns the square root
		return Math.sqrt(xDifference*xDifference + yDifference*yDifference + zDifference*zDifference);
	}
	
	public String toString() {
		//A simple toString method to help with debugging
		return ("X: " + _x + " Y: " + _y + " Z: " + _z);
	}
}
