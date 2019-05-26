package unitTests;

import geometries.*;
import primitives.*;
import elements.*;
import static org.junit.Assert.*;
import org.junit.*;

import java.awt.Color;
import java.util.Random;

public class TriangleTests {

	@Test
	public void testFindIntersection() {
		//Testing a ray hitting the Triangle orthogonally
		Point3D p1 = new Point3D(1, 1, -5);
		Point3D p2 = new Point3D(-2, 2, -5);
		Point3D p3 = new Point3D(0, -4, -5);
		Material material = new Material();
		Color emission = new Color(0,0,0);
		//set test triangle with these points, color, and material
		Triangle testTriangle = new Triangle(p1, p2, p3, material, emission);

		//now construct the ray and its direction which will be tested
		Point3D source = new Point3D();
		Vector direction = new Vector(0,0,-1);
		Ray testRay = new Ray(source, direction);
		//set expectedValue of the intersection point and compare, if the test fails throw the failure
		Point3D expectedValue = new Point3D(0,0,-5);
		assertTrue("failed when triangle was sitting directly in front of camera", (testTriangle.findIntersection(testRay).get(0).compareTo(expectedValue) == 1));

		//Testing the ray missing Triangle entirely (Triangle is off to the side, but plane is in front)

		testRay.setDirection(new Vector(4, -2, -1));
		assertTrue("Failed under ray missing Triangle entirely (Triangle is off to the side, but plane is in front)",testTriangle.findIntersection(testRay).isEmpty());

		//Testing the ray missing the Triangle (Triangle is parallel to ray)
		testRay.setDirection(new Vector(0, 0, -1));
		p1 = new Point3D(-1, 0, -4);
		p2 = new Point3D(3, 0, -5);
		p3 = new Point3D(0, 0, 0);
		testTriangle = new Triangle(p1, p2, p3, material, emission);
		assertTrue("Failed under ray missing Triangle entirely (Triangle is parallel to ray)",testTriangle.findIntersection(testRay).isEmpty());

		//Testing the ray missing the Triangle (Triangle is behind ray)
		p1 = new Point3D(1, 1, 5);
		p2 = new Point3D(-2, 2, 5);
		p3 = new Point3D(-3, -3, 5);
		testTriangle = new Triangle(p1, p2, p3, material, emission);
		assertTrue("Failed under ray missing Triangle entirely (Triangle is behind ray)",testTriangle.findIntersection(testRay).isEmpty());
		
	}
	
	@Test
	public void testGetNormal() {
		
		//Testing when triangle is perpendicular to the viewplane
		Point3D p1 = new Point3D(1, 1, -5);
		Point3D p2 = new Point3D(-2, 2, -5);
		Point3D p3 = new Point3D(-3, -3, -5);
		Material material = new Material();
		Color emission = new Color(0,0,0);
		// create a new triangle with no color and with the points from above
		Triangle triangle = new Triangle(p1, p2, p3, material, emission);
		//Expected result is a vector pointing from the triangle straight back in the direction of the camera
		Vector expectedResult = new Vector(0, 0, 1);
		assertTrue("Failed where plane is perpendicular to camera (normal should be (0,0,1))", triangle.getNormal(p1).compareTo(expectedResult) == 1);		
		
		// try the test with a different set of points
		p1 = new Point3D(-1, 1, -1);
		p2 = new Point3D(1, 0, -2);
		p3 = new Point3D(-1, -1, -3);
		triangle = new Triangle(p1, p2, p3, material, emission);
		expectedResult = new Vector(0, 4, -4).scale(1/Math.sqrt(32.0));
		assertTrue("Failed where plane is tilted (normal should be (0, ~0.7071. ~-0.7071))", triangle.getNormal(p1).compareTo(expectedResult) == 1);
	}

}
