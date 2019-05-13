package unitTests;

import geometries.*;
import primitives.*;
import elements.*;
import static org.junit.Assert.*;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlaneTests {

	@Test
	public void testFindIntersection() {
		
		//Testing a ray hitting the plane orthogonally
		Point3D p1 = new Point3D(1, 1, -5);
		Point3D p2 = new Point3D(-2, 2, -5);
		Point3D p3 = new Point3D(-3, -3, -5);
		Material material = new Material();
		Plane testPlane = new Plane(p1, p2, p3, material);
		
		Point3D source = new Point3D();
		Vector direction = new Vector(0,0,-1);
		Ray testRay = new Ray(source, direction);
		Point3D expectedResult = new Point3D(0, 0, -5);
		Point3D intersection = testPlane.findIntersection(testRay).get(0);
		assertTrue("Failed under normal intersection", intersection.compareTo(expectedResult) == 1);
		
		//Testing the ray hitting the plane at an angle
		
		testRay.setDirection(new Vector(4, -2, -1));
		expectedResult = new Point3D(20, -10, -5);
		intersection = testPlane.findIntersection(testRay).get(0);
		assertTrue("Failed under intersection at an angle", intersection.compareTo(expectedResult) == 1);
		
		//Testing the ray missing the plane (plane is parallel to ray)
		testRay.setDirection(new Vector(0, 0, -1));
		p1 = new Point3D(-1, 0, -4);
		p2 = new Point3D(3, 0, -5);
		p3 = new Point3D(0, 0, 0);
		testPlane = new Plane(p1, p2, p3, material);
		assertTrue("Failed where ray misses the plane (plane is parallel to ray)", testPlane.findIntersection(testRay).isEmpty());
		
		//Testing the ray missing the plane (plane is behind ray)
		p1 = new Point3D(1, 1, 5);
		p2 = new Point3D(-2, 2, 5);
		p3 = new Point3D(-3, -3, 5);
		assertTrue("Failed where ray misses the plane (plane is behind ray)", testPlane.findIntersection(testRay).isEmpty());
	}
	
	@Test
	public void testGetNormal() {
		
		//Testing when plane is perpendicular to the viewplane
		Point3D p1 = new Point3D(1, 1, -5);
		Point3D p2 = new Point3D(-2, 2, -5);
		Point3D p3 = new Point3D(-3, -3, -5);
		Material material = new Material();
		Plane testPlane = new Plane(p1, p2, p3, material);
		//Expected result is a vector pointing from the plane straight back in the direction of the camera
		Vector expectedResult = new Vector(0, 0, 1);
		assertTrue("Failed where plane is perpendicular to camera (normal should be (0,0,1))", testPlane.getNormal(p1).compareTo(expectedResult) == 1);		
		
		p1 = new Point3D(-1, 1, -1);
		p2 = new Point3D(1, 0, -2);
		p3 = new Point3D(-1, -1, -3);
		testPlane = new Plane(p1, p2, p3, material);
		expectedResult = new Vector(0, 4, -4).scale(1/Math.sqrt(32.0));
		assertTrue("Failed where plane is tilted (normal should be (0, ~0.7071. ~-0.7071))", testPlane.getNormal(p1).compareTo(expectedResult) == 1);
	}

}
