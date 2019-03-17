package geomentries;
import java.util.*;
import primitives.*;

public interface Geometry {
	public List<Point3D> findIntersection(Ray r);
}
