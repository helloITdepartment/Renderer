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
	
	//Getter
	public double getCoordinate(){
		return _coordinate;
	}
	
	//Setter
	public void setCoordinate(double coord){
		_coordinate = coord;
	}

	public int compareTo(Coordinate other){
		return _coordinate == other._coordinate ? 1 : 0; //Checks that the coordinate values are equal and returns 1 if true, 0 if not
	}
	
	public Coordinate add(Coordinate other){
		return new Coordinate(_coordinate + other._coordinate);
	}

	public Coordinate subtract(Coordinate other){
		return new Coordinate(_coordinate - other._coordinate);
	}
	
	public String toString() {
		return "" + _coordinate;
	}
	
}
