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
		
	//Copy constructor
	public Point3D(Point3D other){
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
}
