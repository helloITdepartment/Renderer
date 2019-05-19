package unitTests;

import geometries.*;
import primitives.*;
import elements.*;
import scene.*;
import static org.junit.Assert.*;
import org.junit.*;

import java.util.*;
import java.awt.Color;

public class SceneTests {

	@Test
	public void testAddGeomerty() {
		
		//Testing the normal case
		//Creates a default scene for testing
		Scene scene = new Scene();
		//Creating a simple object for testing
		Sphere testSphere = new Sphere(2, new Point3D(0,0,0), new Material(), new Color(0,0,0));
		//Adding it to our scene
		scene.addGeometry(testSphere);
		//The List we're expecting to get back if all goes correctly
		List<Geometry> expectedResult = new ArrayList<Geometry>();
		expectedResult.add(testSphere);
		
		assertTrue("Failed under addition of single simple geometry", scene.getGeometryList().equals(expectedResult));
		
		//Testing case where list is empty
		scene = new Scene();
		assertTrue("Failed under empty list", scene.getGeometryList().equals(new ArrayList<Geometry>()));
		
		//Testing case where list is very very large
		for(int i = 0; i<250; i++) {
			//Adds a geometry to the list 250 times
			scene.addGeometry(testSphere);
		}
		//Checks to make sure all 250 are returned
		assertTrue("Failed under case where list is very large", scene.getGeometryList().size() == 250);
	}

}
