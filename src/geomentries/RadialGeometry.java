package geomentries;

public class RadialGeometry {
	double _radius;
	
	//Constructors
	//Empty constructor
	public RadialGeometry(){
		
	}
			
	//Parameterized constructor
	public RadialGeometry(double radius){
		_radius = radius;
	}
		
	//Copy constructor
	public RadialGeometry(RadialGeometry other){
		_radius = other._radius;
	}
		
	//Getters
	public double getRadius(){
		return _radius;
	}
		
	//Setters
	public void setRadius(double radius){
		_radius = radius;
	}

}
