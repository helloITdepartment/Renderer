package primitives;

public class Point2D{
	//coordinates used to represent point in 2d space
		Coordinate _x;
		Coordinate _y;
	
	//Constructors
	//Empty constructor
	public Point2D(){
		//default constructor initializing x, y coordinates to the origin
		_x = new Coordinate();
		_y = new Coordinate();
	}
		
	//Parameterized constructor
	public Point2D(Coordinate x, Coordinate y){
		//set data members to parameters passed
		_x = x;
		_y = y;
	}
	
	//Copy constructor
	public Point2D(Point2D other){
		//Takes in another Point2D and copies data members to its own
		_x = other._x;
		_y = other._y;
	}
	
	//Getters
	public Coordinate getX(){
		//Returns new coordinate with the same value as our _x, so that changes made at the callsite wont affect our variables
		return new Coordinate(_x);
	}
	
	public Coordinate getY(){
		//Returns new coordinate with the same value as our _y, so that changes made at the callsite wont affect our variables
		return new Coordinate(_y);
	}
	//Setters
	public void setX(Coordinate x){
		//allows for setting of protected data member _x
		_x = x;
	}

	public void setY(Coordinate y){
		//allows for setting of protected data member _y
		_y = y;
	}

	public int compareTo(Point2D other){
		//Checks to see if the x values are equal and then if the y values are equal. Returns 1 if it's all true, 0 otherwise
		return (_x._coordinate == other.getX()._coordinate && _y._coordinate == other.getY()._coordinate) ? 1 : 0; 
	}
}
