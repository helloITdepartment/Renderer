package primitives;

public class Coordinate {
	double _coordinate;
	
	//Constructors
	//Empty constructor
	public Coordinate(){
		_coordinate = 0.0;
	}
	
	//Parameterized constructor
	public Coordinate(double coord){
		_coordinate = coord;
	}
	
	//Copy constructor
	public Coordinate(Coordinate other){
		_coordinate = other._coordinate;
	}
	
	//Getters
	public double getCoordinate(){
		return _coordinate;
	}
	
	//Setters
	public void setCoordinate(double coord){
		_coordinate = coord;
	}
}
