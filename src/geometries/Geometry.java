package geometries;
import java.util.*;
import primitives.*;

public interface Geometry {
	//method that will allow us to find the intersections of the rays with the geometric forms which will give us our rendering
	public List<Point3D> findIntersection(Ray r);
}
