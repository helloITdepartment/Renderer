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
		
		
		//TODO: Implement Unit tests for the Camera class
		//Construct ray through center pixel
		Ray r = c.constructRayThroughPixel(3, 3, 0.0, 0.0, 1.0, 9.0, 9.0);
		Ray knownValue = new Ray();
		knownValue.setSource(zeroPoint);
		knownValue.setDirection(new Vector(0.0, 0.0, -1.0));
		
		assertTrue("Failed under ray construction through center pixel", knownValue.compareTo(r) == 1);
		
		//Construct ray through corner pixel
		r = c.constructRayThroughPixel(3, 3, 0.0, 0.0, 1.0, 9.0, 9.0);
		knownValue = new Ray();
		knownValue.setSource(zeroPoint);
		knownValue.setDirection(new Vector(0.0, 0.0, -1.0));
		
		assertTrue("Failed under ray construction through corner pixel", knownValue.compareTo(r) == 1);
		
		//Construct ray through xy value on the edge of a pixel
		r = c.constructRayThroughPixel(3, 3, 0.0, 0.0, 1.0, 9.0, 9.0);
		knownValue = new Ray();
		knownValue.setSource(zeroPoint);
		knownValue.setDirection(new Vector(0.0, 0.0, -1.0));
				
		assertTrue("Failed under ray construction through xy value on the edge of a pixel", knownValue.compareTo(r) == 1);
				
		//Construct ray through arbitrary pixel
		r = c.constructRayThroughPixel(3, 3, 0.0, 0.0, 1.0, 9.0, 9.0);
		knownValue = new Ray();
		knownValue.setSource(zeroPoint);
		knownValue.setDirection(new Vector(0.0, 0.0, -1.0));
		
		assertTrue("Failed under ray construction through arbitrary pixel", knownValue.compareTo(r) == 1);
	}

}
