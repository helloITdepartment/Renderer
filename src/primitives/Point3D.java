package primitives;

public class Point3D extends Point2D{
	Coordinate _z;

	//Constructors
	//Empty constructor
	public Point3D(){
		super();
		_z = new Coordinate();
	}
			
	//Parameterized constructors
	public Point3D(Coordinate z){
		super();
		_z = z;
	}
	
	public Point3D(Coordinate x, Coordinate y, Coordinate z){
		super(x, y);
		_z = z;
	}
	
	public Point3D(double x, double y, double z) {
		super(new Coordinate(x), new Coordinate(y));
		_z = new Coordinate(z);
	}
		
	//Copy constructor
	public Point3D(Point3D other){
		super(other.getX(), other.getY());
		_z = other._z;
	}
	
	//Getters
	//getX and getY are unnecessary because they are inherited from Point2D
	public Coordinate getZ(){
		return new Coordinate(_z);
	}
	
	//Setters
	//setX and setY are unnecessary because they are inherited from Point2D
	public void setZ(Coordinate z){
		_z = z;
	}

	//Checks to make sure corresponding values match and returns 1 if they all do, returns 0 if any of them mismatch
	public int compareTo(Point3D other){
		if(_x.getCoordinate() == other.getX().getCoordinate()){
			if(_y.getCoordinate() == other.getY().getCoordinate()){
				return (_z.getCoordinate() == other.getZ().getCoordinate()) ? 1 : 0;
			}else{
				return 0;
			}
		}else{
			return 0;
		}
	}

	//Vector addition. Adds corresponding x, y, and z values and returns a new Point3D
	public Point3D add(Vector v){
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
	
	//Vector subtraction. Subtracts corresponding x, y, and z values and returns a new Point3D	
	public Point3D subtract(Vector v){
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
	
	public String toString() {
		return ("X: " + _x + " Y: " + _y + " Z: " + _z);
	}
}
