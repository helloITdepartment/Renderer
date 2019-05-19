package geometries;
import java.awt.Color;
import java.util.*;
import primitives.*;
import primitives.Vector;

public abstract class Geometry {
	
	Material _material;
	
	Color _emission;
	
	public abstract List<Point3D> findIntersection(Ray r);
	
	public abstract Vector getNormal(Point3D p);
	
	public abstract Color getEmission();
	
}
