package primitives;

public class Ray{
	//Point showing where the Ray originates
	Point3D _source;
	//Vector describing which direction the Ray is heading
	Vector _direction;
	
	//Constructors
	//Empty constructor
	public Ray(){
		//sets _source to default point for origin and _direction for default vector for direction
		_source = new Point3D();
		_direction = new Vector();
	}
			
	//Parameterized constructor
	public Ray(Point3D source, Vector direction){
		//sets source and direction to given source and direction
		_source = source;
		_direction = direction;
//		_direction = direction.normalize();
	}	
	
	//Copy constructor
	public Ray(Ray other){
		//accepts another ray as a parameter and copies its data members as its own
		_source = other._source;
		_direction = other._direction;
	}
		
	//Getters
	public Point3D getSource(){
		//Returns new Point3D with the same value as our _source, so that changes made at the callsite wont affect our variables
		return new Point3D(_source);
	}
	
	public Vector getDirection(){
		//Returns new Vector with the same value as our _direction, so that changes made at the callsite wont affect our variables
		return new Vector(_direction);
	}
	
	//Setters
	public void setSource(Point3D source){
		//allows for the setting of protected data member source passed as a parameter
		_source = source;
	}
	
	public void setDirection(Vector direction){
		//allows for the setting of private data member direction passed as a parameter
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
	
	public String toString() {
		String result = "";
		result += "Source: " + _source;
		result += ", Direction: " + _direction;
		return result;
	}

}
