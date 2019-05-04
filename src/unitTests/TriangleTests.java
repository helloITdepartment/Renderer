package unitTests;

import geometries.*;
import primitives.*;
import elements.*;
import static org.junit.Assert.*;
import org.junit.*;
import java.util.Random;

public class TriangleTests {

	@Test
	public void testFindIntersection() {
		Point3D p1 = new Point3D(1.0, 0.0, -1.0);
		Point3D p2 = new Point3D(-1.0, 1.0 , -1.0);
		Point3D p3 = new Point3D(-1.0, -1.0, -1.0);
		Triangle testTriangle = new Triangle(p1, p2, p3);
		
		Ray r = new Ray(new Point3D(0.0, 0.0, 0.0), new Vector(0.0, 0.0, -1.0));
		
		System.out.println(testTriangle.findIntersection(r));
	}

}
