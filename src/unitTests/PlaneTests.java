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
		Plane testPlane = new Plane(p1, p2, p3);
		
		Point3D source = new Point3D();
		System.out.println(source);
		Vector direction = new Vector(0,0,-1);
		Ray testRay = new Ray(source, direction);
		Point3D intersection = testPlane.findIntersection(testRay).get(0);
		System.out.println(intersection);
		
		//Testing the ray hitting the plane at an angle
		
		testRay.setDirection(new Vector(4, -2, -1));
		intersection = testPlane.findIntersection(testRay).get(0);
		System.out.println(intersection);
		
		//Testing the ray missing the plane (plane is parallel to ray)
		testRay.setDirection(new Vector(0, 0, -1));
		p1 = new Point3D(-1, 0, -4);
		p2 = new Point3D(3, 0, -5);
		p3 = new Point3D(0, 0, 0);
		testPlane = new Plane(p1, p2, p3);
		System.out.println(testPlane.findIntersection(testRay).isEmpty());
		
		//Testing the ray missing the plane (plane is behind ray)
		p1 = new Point3D(1, 1, 5);
		p2 = new Point3D(-2, 2, 5);
		p3 = new Point3D(-3, -3, 5);
		System.out.println(testPlane.findIntersection(testRay).isEmpty());
	}

}
