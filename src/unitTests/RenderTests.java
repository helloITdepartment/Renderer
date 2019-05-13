package unitTests;

import geometries.*;
import primitives.*;
import elements.*;
import renderer.*;
import scene.*;
import static org.junit.Assert.*;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Color;


import java.awt.*;

import static org.junit.Assert.*;

public class RenderTests {
	
	@Test
	public void renderTest() {
		//Sets up a "sun" with white light
		AmbientLight ambientLight = new AmbientLight(new Color(255, 255, 255), 1.0);
		//Creates a sphere and some triangles around it 
		Material material = new Material();
		Sphere sphere = new Sphere(3.0, new Point3D(0.0, 0.0, -10.0), material);
		
		Material material1 = new Material();
		Material material2 = new Material();
		Material material3 = new Material();		
		Material material4 = new Material();
		
		Triangle triangle1 = new Triangle(new Point3D(-6.75, 6.75, -10), new Point3D(-6.75, 0, -10), new Point3D(0, 6.75, -10), material1);
		Triangle triangle2 = new Triangle(new Point3D(0, 6.75, -10), new Point3D(6.75, 6.75, -10), new Point3D(6.75, 0, -10), material2);
		Triangle triangle3 = new Triangle(new Point3D(6.75, 0, -10), new Point3D(6.75, -6.75, -10), new Point3D(0, -6.75, -10), material3);
		Triangle triangle4 = new Triangle(new Point3D(0, -6.75, -10), new Point3D(-6.75, -6.75, -10), new Point3D(-6.75, 0, -10), material4);
		//Creates a list of geometries to feed into our Scene instance
		List<Geometry> list = new ArrayList<Geometry>();
		//Adds the sphere and triangles to the list
		list.add(sphere);
		list.add(triangle1);
		list.add(triangle2);
		list.add(triangle3);
		list.add(triangle4);
		//Instantiates a new Camera with default values (point at the origin, facing down the negative z axis)
		Camera camera = new Camera();
		//Creates a scene to hold our universe
		Scene scene = new Scene("TestScene", new Color(0, 0, 0), ambientLight, list, camera, 30.0);
		//Creates an ImageWriter instance to help write down what our camera sees
		ImageWriter imageWriter = new ImageWriter("RenderTest", 500, 500, 100, 100);
		//Creates a Render instance to pull it all together
		Render render = new Render(scene, imageWriter);
		
		
		//Records what the camera sees
		render.renderImage();
		//Overlays a grid to help visualization
		render.printGrid(50);
		//Prints it all to a file
		imageWriter.writeToImage();
	}
}
