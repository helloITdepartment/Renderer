package unitTests;

import geometries.*;
import primitives.*;
import elements.*;
import static org.junit.Assert.*;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SphereTests {

	@Test
	public void testFindIntersection() {
		Point3D center = new Point3D(0.0, 0.0, -2.0);
		double radius = 1.0;
		Sphere s = new Sphere(radius, center);
		
		Ray r = new Ray(new Point3D(0.0, 0.0, 0.0), new Vector(0.0, 0.0, -1.0));
		
		List<Point3D> expectedResult = new ArrayList<>();
		expectedResult.add(new Point3D (0.0, 0.0, -1.0));
		expectedResult.add(new Point3D (0.0, 0.0, -3.0));
		//assertTrue("Failed under ray through center of sphere", s.findIntersection(r) == expectedResult);
		
		r = new Ray(new Point3D(0.0, 0.0, 0.0), new Vector(2.0, 0.0, -1.0));
		
		expectedResult = new ArrayList<>();
		assertTrue("Failed under ray through center of sphere", s.findIntersection(r) == expectedResult);
		
	}

}
