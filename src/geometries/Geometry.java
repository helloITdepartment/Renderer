package geometries;
import java.util.*;
import primitives.*;
import primitives.Vector;

public abstract class Geometry {
	
	Material _material;
	
	public abstract List<Point3D> findIntersection(Ray r);
	
	public abstract Vector getNormal(Point3D p);
	
}
