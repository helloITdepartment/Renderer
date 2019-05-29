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
		//Sets up an empty list of lights
		List<LightSource> lights = new ArrayList<LightSource>();
		//Creates a spotlight to add to our scene
		SpotLight spotLight = new SpotLight(new Point3D(10, -3, -5), new Vector(-1, 0, -1), new Color(255, 0, 0));
		lights.add(spotLight);
		//Creates a sphere and some triangles around it 
		Material material = new Material();
		Color emission = new Color(0,0,0);
		Sphere sphere = new Sphere(3.0, new Point3D(0.0, 0.0, -10.0), material, emission);
		
		Material material1 = new Material();
		Color emission1 = new Color(0, 255, 0);
		Material material2 = new Material();
		Color emission2 = new Color(0,0 ,0);
		Material material3 = new Material();
		Color emission3 = new Color(0,0,255);
		Material material4 = new Material();
		Color emission4 = new Color(255,0,0);
		
		Triangle triangle1 = new Triangle(new Point3D(-6.75, 6.75, -10), new Point3D(-6.75, 0, -10), new Point3D(0, 6.75, -10), material1, emission1);
		Triangle triangle2 = new Triangle(new Point3D(0, 6.75, -10), new Point3D(6.75, 6.75, -10), new Point3D(6.75, 0, -10), material2, emission2);
		Triangle triangle3 = new Triangle(new Point3D(6.75, 0, -10), new Point3D(6.75, -6.75, -10), new Point3D(0, -6.75, -10), material3, emission3);
		Triangle triangle4 = new Triangle(new Point3D(0, -6.75, -10), new Point3D(-6.75, -6.75, -10), new Point3D(-6.75, 0, -10), material4, emission4);
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
		Scene scene = new Scene("TestScene", new Color(0, 0, 0), ambientLight, lights, list, camera, 30.0);
		//Creates an ImageWriter instance to help write down what our camera sees
		ImageWriter imageWriter = new ImageWriter("RenderTestWithEmissionAndSpotlightBugFixTest", 500, 500, 100, 100);
		//Creates a Render instance to pull it all together
		Render render = new Render(scene, imageWriter);
		
		
		//Records what the camera sees
		render.renderImage();
		//Overlays a grid to help visualization
		render.printGrid(50);
		//Prints it all to a file
		imageWriter.writeToImage();
		
	}

	@Test
	public void spotLightRenderTest() {
		//Sets up a "sun" with white light
		AmbientLight ambientLight = new AmbientLight(new Color(255, 255, 255), 1.0);
		//Instantiates a new Camera with default values (point at the origin, facing down the negative z axis)
		Camera camera = new Camera();
		//Sets up the list of geometries in our scene
		List<Geometry> geoList = new ArrayList<Geometry>();
		//Sets up the sphere in our scene and adds it to the list
		Sphere sphere = new Sphere(6.0, new Point3D(0.0, 0.0, -10.0), new Material(), new Color(148,0,211));
		geoList.add(sphere);
		//Sets up an empty list of lights
		List<LightSource> lights = new ArrayList<LightSource>();
		//Creates a spotlight to add to our scene
		SpotLight spotLight = new SpotLight(new Point3D(25,25,-2), new Vector(-1,-1,-0.5), new Color(255,255,255));
		lights.add(spotLight);
		//Creates a scene to hold it all
		Scene scene = new Scene("SpotLightTestScene", new Color(0,0,0), ambientLight, lights, geoList, camera, 30.0);
		//Creates an ImageWriter instance to help write down what our camera sees
		ImageWriter imageWriter = new ImageWriter("SpotLightTest25", 500, 500, 100, 100);
		//Creates a Render instance to pull it all together
		Render render = new Render(scene, imageWriter);


		//Records what the camera sees
		render.renderImage();
		//Prints it all to a file
		imageWriter.writeToImage();
	}
}
