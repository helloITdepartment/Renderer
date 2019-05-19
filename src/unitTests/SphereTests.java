package unitTests;

import geometries.*;
import primitives.*;
import elements.*;
import static org.junit.Assert.*;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.*;

public class SphereTests {

	@Test
	public void testFindIntersection() {
		Point3D center = new Point3D(0.0, 0.0, -2.0);
		double radius = 1.0;
		Material material = new Material();
		Color emission = new Color(0,0,0);
		Sphere s = new Sphere(radius, center, material, emission);
		
		Ray r = new Ray(new Point3D(0.0, 0.0, 0.0), new Vector(0.0, 0.0, -1.0));
		
		List<Point3D> expectedResult = new ArrayList<>();
		expectedResult.add(new Point3D (0.0, 0.0, -1.0));
		expectedResult.add(new Point3D (0.0, 0.0, -3.0));
		assertTrue("Failed under ray through center of sphere", s.findIntersection(r).get(0).compareTo(expectedResult.get(0)) == 1 && s.findIntersection(r).get(1).compareTo(expectedResult.get(1)) == 1);
		
		r = new Ray(new Point3D(0.0, 0.0, 0.0), new Vector(2.0, 0.0, -1.0));
		
		expectedResult = new ArrayList<>();
		assertTrue("Failed under ray missing sphere entirely", s.findIntersection(r).isEmpty());
		
	}
	
	@Test
	public void testGetNormal() {
		Point3D center = new Point3D(0.0, 0.0, -2.0);
		double radius = 1.0;
		Material material = new Material();
		Color emission = new Color(0,0,0);
		Sphere s = new Sphere(radius, center, material, emission);
		
		//Expected result is a vector pointing from the sphere straight back in the direction of the camera
		Vector expectedResult = new Vector(0, 0, 1);
		assertTrue("Failed where sphere and point are directly in front of camera (normal should be (0,0,1))", s.getNormal(new Point3D(0, 0, -1.0)).compareTo(expectedResult) == 1);

		//Expected result is a vector pointing off to the right
		expectedResult = new Vector(1, 0, 0);
		assertTrue("Failed where sphere is directly in front of camera and point is on right tip (normal should be (1, 0, 0))", s.getNormal(new Point3D(1, 0, -2.0)).compareTo(expectedResult) == 1);

		//Expected result is a vector pointing up
		expectedResult = new Vector(0, 1, 0);
		assertTrue("Failed where sphere is directly in front of camera and point is on top of sphere (normal should be (0, 1, 0))", s.getNormal(new Point3D(0, 1, -2.0)).compareTo(expectedResult) == 1);
	}

}
