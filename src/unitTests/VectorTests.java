package unitTests;

import primitives.*;
import static org.junit.Assert.*;
import org.junit.*;
import java.util.Random;

public class VectorTests {

	@Test
	public void testAdd() {
		Vector v = new Vector(2.5, 3.6, 1.0);
		
		Vector newV = v.add(new Vector(1.0, 1.0, 1.0));
		assertEquals("Failed under positive addition", 1, newV.compareTo(new Vector(3.5, 4.6, 2.0)));

		newV = v.add(new Vector(-1.0, -1.0, -1.0));
		assertEquals("Failed under negative addition", 1, newV.compareTo(new Vector(1.5, 2.6, 0.0)));

		newV = v.add(new Vector(0.0, 0.0, 0.0));
		assertEquals("Failed under zero addition", 1, newV.compareTo(new Vector(2.5, 3.6, 1.0)));

	}

	@Test
	public void testSubtract() {
		Vector v = new Vector(2.5, 3.6, 1.0);
		
		Vector newV = v.subtract(new Vector(1.0, 1.0, 1.0));
		assertEquals("Failed under positive subtraction", 1, newV.compareTo(new Vector(1.5, 2.6, 0.0)));

		newV = v.subtract(new Vector(-1.0, -1.0, -1.0));
		assertEquals("Failed under negative subtraction", 1, newV.compareTo(new Vector(3.5, 4.6, 2.0)));

		newV = v.subtract(new Vector(0.0, 0.0, 0.0));
		assertEquals("Failed under zero subtraction", 1, newV.compareTo(new Vector(2.5, 3.6, 1.0)));
	}

	@Test
	public void testScaling() {
		Vector v = new Vector(7.7, 0.0, 0.032);
		
		Vector newV = v.scale(0.0);
		assertEquals("Failed under scaling by zero", 0.0, newV.length(), 1e-10);

		newV = v.scale(-1.0);
		assertEquals("Failed under vector reversal", 1, newV.compareTo(new Vector(-7.7, 0.0, -0.032)));

		newV = v.scale(2.0);
		assertEquals("Failed under stretching of vector", 1, newV.compareTo(new Vector(7.7 * 2.0, 0.0, 0.032 * 2.0)));

		newV = v.scale(0.5);
		assertEquals("Failed under shrinking of vector", 1, newV.compareTo(new Vector(7.7 / 2.0, 0.0, 0.032 / 2.0)));
	}

	@Test
	public void testDotProduct() {
		Vector v = new Vector(3.4, 5.6, 7.8);
		
		Vector newV = new Vector(0.9, 4.3, 2.1);
		assertEquals("Failed under normal dot product", 43.52, v.dotProduct(newV), 1e-10);

		newV = new Vector(-0.9, -4.3, -2.1);
		assertEquals("Failed under dot product with vector of opposing sign", -43.52, v.dotProduct(newV), 1e-10);

		newV = new Vector(0.0, 0.0, 0.0);
		assertEquals("Failed under dot product with zero vector", 0.0, v.dotProduct(newV), 1e-10);

		newV = new Vector(-11.9, 4.3, 2.1);
		assertEquals("Failed under dot product with orthogonal vector", 0, v.dotProduct(newV), 1e-10);
	}

	@Test
	public void testLength() {
		Vector v = new Vector(0.0, 0.0, 0.0);
		assertEquals("Zero vector length failed", 0.0, v.length(), 1e-10);

		v = new Vector(1.0, 0.0, -1.0);
		assertFalse("Assigned zero length to non-zero vector", v.length() == 0.0);

		Random r = new Random();
		double randomX = (r.nextDouble() * 200) - 100;
		double randomY = (r.nextDouble() * 200) - 100;
		double randomZ = (r.nextDouble() * 200) - 100;

		v = new Vector(randomX, randomY, randomZ);
		assertEquals("Length function failed on random vector",
				Math.sqrt(randomX * randomX + randomY * randomY + randomZ * randomZ), v.length(), 1e-10);

	}

	@Test
	 public void testNormalize() {
		Vector v = new Vector(7.7, 0.0, 0.032);
		Vector newV = v.normalize();

		assertEquals("Normalize failed", 1.0, newV.length(), 1e-10);

		v = new Vector(0.0, 0.0, 0.0);

		try {
			v.normalize();
			fail("If it got to here then it didn't throw an error, which it should have.");
		} catch (IllegalArgumentException e) {
			assertEquals("Normalize threw the wrong error", e.getMessage(),
					"Cannot normalize the zero vector. Will result in divide by zero");
		}

	}

	@Test
	public void testCrossProduct() {
		Vector v = new Vector(7.7, 0.0, 0.032);
		Vector zeroVector = new Vector(0.0, 0.0, 0.0);

		Vector newV = new Vector(3.85, 0.0, 0.016);
		assertTrue("Failed under cross product with parallel vector (scaled by 1/2)", v.crossProduct(newV).compareTo(zeroVector) == 1);

		newV = new Vector(-7.7, 0.0, -0.032);
		assertTrue("Failed under cross product with antiparallel vector (scaled by -1)", v.crossProduct(newV).compareTo(zeroVector) == 1);

		newV = new Vector(-0.032, 0.0, 7.7);
		assertEquals("X coordinate failed under cross product with orthogonal vector", 0.0, v.crossProduct(newV).getHead().getX().getCoordinate(), 1e-10);
		assertEquals("Y coordinate failed under cross product with orthogonal vector", -59.291024, v.crossProduct(newV).getHead().getY().getCoordinate(), 1e-10);
		assertEquals("Z coordinate failed under cross product with orthogonal vector", 0.0, v.crossProduct(newV).getHead().getZ().getCoordinate(), 1e-10);

		 newV = new Vector(-4.4, 5.6, 6.6);
		 assertEquals("X coordinate failed under cross product with random vector", -0.1792, v.crossProduct(newV).getHead().getX().getCoordinate(), 1e-10);
		 assertEquals("Y coordinate failed under cross product with random vector", -50.9608, v.crossProduct(newV).getHead().getY().getCoordinate(), 1e-10);
		 assertEquals("Z coordinate failed under cross product with random vector", 43.12, v.crossProduct(newV).getHead().getZ().getCoordinate(), 1e-10);
	}
}
