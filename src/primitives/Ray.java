package primitives;

public class Ray{
	Point3D _source;
	Vector _direction;
	
	//Constructors
	//Empty constructor
	public Ray(){
		
	}
			
	//Parameterized constructor
	public Ray(Point3D source, Vector direction){
		_source = source;
		_direction = direction;
	}	
	
	//Copy constructor
	public Ray(Ray other){
		_source = other._source;
		_direction = other._direction;
	}
		
	//Getters
	public Point3D getSource(){
		return _source;
	}
	
	public Vector getDirection(){
		return _direction;
	}
	//Setters
	public void setSource(Point3D source){
		_source = source;
	}
	
	public void setDirection(Vector direction){
		_direction = direction;
	}

}
