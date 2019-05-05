package primitives;

public class Coordinate {
	double _coordinate;

	//Constructors
	//Default constructor
	public Coordinate(){
		// set the default coordinate to 0
		_coordinate = 0.0;
	}

	//Parameterized constructor
	public Coordinate(double coord){
		// set variable _coordinate to passed coord
		_coordinate = coord;
	}

	//Copy constructor
	public Coordinate(Coordinate other){
		// sets one point of another coordinate to a different coordinate.
		_coordinate = other._coordinate;
	}

	//Getter
	public double getCoordinate(){
		return _coordinate;
	}

	//Setter
	public void setCoordinate(double coord){
		_coordinate = coord;
	}

	//Checks that the coordinate values are equal and returns 1 if true, 0 if not
	public int compareTo(Coordinate other){
		return _coordinate == other._coordinate ? 1 : 0;
	}

	// add 2 coordinates together to create a new coordinate
	public Coordinate add(Coordinate other){
		return new Coordinate(_coordinate + other._coordinate);
	}

	// subtract 2 coordinates to create a new coordinate
	public Coordinate subtract(Coordinate other){
		return new Coordinate(_coordinate - other._coordinate);
	}

	//A simple toString method so java can understand how to print a Coordinate
	public String toString() {
		return "" + _coordinate;
	}

}
