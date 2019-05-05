package primitives;

public class Ray{
	Point3D _source;
	Vector _direction;
	
	//Constructors
	//Empty constructor
	public Ray(){
		_source = new Point3D();
		_direction = new Vector();
	}
			
	//Parameterized constructor
	public Ray(Point3D source, Vector direction){
		_source = source;
		_direction = direction.normalize();
	}	
	
	//Copy constructor
	public Ray(Ray other){
		_source = other._source;
		_direction = other._direction;
	}
		
	//Getters
	public Point3D getSource(){
		return new Point3D(_source);
	}
	
	public Vector getDirection(){
		return new Vector(_direction);
	}
	//Setters
	public void setSource(Point3D source){
		_source = source;
	}
	
	public void setDirection(Vector direction){
		_direction = direction;
	}
	
	public int compareTo(Ray other){
		//A boolean checking to see whether the sources match
		boolean sourceMatches = (_source.compareTo(other.getSource()) == 1);
		//A boolean checking to see whether the directions match
		boolean directionMatches = (_direction.compareTo(other.getDirection()) == 1);
		//returns 1 if both source and direction match, 0 otherwise
		return ((sourceMatches && directionMatches) ? 1 : 0);
	}

}
