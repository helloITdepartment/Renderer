package unitTests;

import primitives.*;
import elements.*;
import static org.junit.Assert.*;
import org.junit.*;
import java.util.Random;

public class CameraTest {
	
	@Test
	public void testRayConstruction() {
		Point3D zeroPoint = new Point3D(new Coordinate(0.0), new Coordinate(0.0), new Coordinate(0.0));
		
		Camera c = new Camera();
		
		//Construct ray through center pixel
		Ray r = c.constructRayThroughPixel(3, 3, 2.0, 2.0, 1.0, 9.0, 9.0);
		Ray knownValue = new Ray();
		knownValue.setSource(zeroPoint);
		knownValue.setDirection(new Vector(0.0, 0.0, -1.0));
		System.out.println(r.getDirection());
		
		//Checks to make sure the ray constructed is equal to the expected value
		assertTrue("Failed under ray construction through center pixel", knownValue.compareTo(r) == 1);
		
		//Construct ray through corner pixel
		r = c.constructRayThroughPixel(9, 9, 1.0, 1.0, 1.0, 81.0, 81.0);
		knownValue = new Ray();
		knownValue.setSource(zeroPoint);
		knownValue.setDirection(new Vector(-36.0, 36.0, -1.0));
		
		//Checks to make sure the ray constructed is equal to the expected value
		assertTrue("Failed under ray construction through corner pixel", knownValue.compareTo(r) == 1);
		
				
		//Construct ray through arbitrary pixel
		r = c.constructRayThroughPixel(1920, 1080, 1642.0, 753.0, 215.0, 1920.0, 1080.0);
		knownValue = new Ray();
		knownValue.setSource(zeroPoint);
		knownValue.setDirection(new Vector(681.5, -212.5, -215.0));
		
		//Checks to make sure the ray constructed is equal to the expected value
		assertTrue("Failed under ray construction through arbitrary pixel", knownValue.compareTo(r) == 1);
	}

}
