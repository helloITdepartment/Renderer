package unitTests;

import geometries.*;
import primitives.*;
import elements.*;
import static org.junit.Assert.*;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Color;


public class AmbientLightTests {

	@Test
	public void testGetIntensity() {
		//Testing normal case
		//A random color for testing (Whatsapp Green)
		Color color = new Color(90, 202, 102);
		//A random intensity value
		double intensity = .45;
		//What the getIntensity should return if it works correctly
		Color expectedValue = new Color(40, 90, 45);

		//An ambient light with the given color and intensity
		AmbientLight al = new AmbientLight(color, intensity);
		assertTrue("getIntensity method failed under regular case", al.getIntensity().equals(expectedValue));

		//Testing full intensity
		//An intensity of zero 
		intensity = 1.0;
		//What the getIntensity should return if it works correctly
		expectedValue = new Color(90, 202, 102);

		//An ambient light with the given color and intensity
		al = new AmbientLight(color, intensity);
		assertTrue("getIntensity method failed under full intensity case", al.getIntensity().equals(expectedValue));

		//Testing complete darkness
		//An intensity of zero ("turning the lights off")
		intensity = 0.0;
		//What the getIntensity should return if it works correctly
		expectedValue = new Color(0, 0, 0);

		//An ambient light with the given color and intensity
		al = new AmbientLight(color, intensity);
		assertTrue("getIntensity method failed under darkness case", al.getIntensity().equals(expectedValue));


		//Testing black light
		//Setting the light to black
		color = new Color(0, 0, 0);
		//A random intensity value
		intensity = .37;
		//What the getIntensity should return if it works correctly
		expectedValue = new Color(0, 0, 0);

		//An ambient light with the given color and intensity
		al = new AmbientLight(color, intensity);
		assertTrue("getIntensity method failed under black light case", al.getIntensity().equals(expectedValue));
	}
}