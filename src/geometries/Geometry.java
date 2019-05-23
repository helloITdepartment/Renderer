package geometries;
import java.awt.Color;
import java.util.*;
import primitives.*;
import primitives.Vector;

//An abstract class representing a general shape. Anything that wants to call itself a Geometry must conform to the following: 
public abstract class Geometry {
	
	//A material that the Geometry is made of, indicating how light is bounced off it
	Material _material;
	
	//A Color that the Geometry gives off by itself
	Color _emission;
	
	//Each Geometry must implement a method that:
	//Returns a list of points where a given Ray would intersect that Geometry
	public abstract List<Point3D> findIntersection(Ray r);
	
	//Returns a vector normal to the Geometry at a given Point
	public abstract Vector getNormal(Point3D p);
	
	//Returns the Color emitted by the Geometry
	public abstract Color getEmission();
	
	//Returns the material that the Geometry is made of
	public abstract Material getMaterial();
	
}
