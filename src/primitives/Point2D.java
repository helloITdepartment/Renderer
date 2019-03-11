package primitives;

public class Point2D{
	Coordinate _x;
	Coordinate _y;
	
	//Constructors
	//Empty constructor
	public Point2D(){
		_x = new Coordinate();
		_y = new Coordinate();
	}
		
	//Parameterized constructor
	public Point2D(Coordinate x, Coordinate y){
		_x = x;
		_y = y;
	}
	
	//Copy constructor
	public Point2D(Point2D other){
		_x = other._x;
		_y = other._y;
	}
	
	//Getters
	public Coordinate getX(){
		return new Coordinate(_x);
	}
	
	public Coordinate getY(){
		return new Coordinate(_y);
	}
	//Setters
	public void setX(Coordinate x){
		_x = x;
	}

	public void setY(Coordinate y){
		_y = y;
	}
}
