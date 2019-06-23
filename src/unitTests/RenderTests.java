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
		ImageWriter imageWriter = new ImageWriter("RenderTestWithEmissionAndSpotlightRefectoredTest2", 500, 500, 100, 100);
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
		Sphere sphere = new Sphere(6.0, new Point3D(0.0, 0.0, -10.0), new Material(3.0, 3.0, 100), new Color(148,0,211));
		geoList.add(sphere);
		//Sets up an empty list of lights
		List<LightSource> lights = new ArrayList<LightSource>();
		//Creates a spotlight to add to our scene
		SpotLight spotLight = new SpotLight(new Point3D(-25,-25,-2), new Vector(-1,-1,-1.5), new Color(255,255,255));
		lights.add(spotLight);
		//Creates a scene to hold it all
		Scene scene = new Scene("SpotLightTestScene", new Color(0,0,0), ambientLight, lights, geoList, camera, 30.0);
		//Creates an ImageWriter instance to help write down what our camera sees
		ImageWriter imageWriter = new ImageWriter("SpotLightTest3Refactored", 500, 500, 100, 100);
		//Creates a Render instance to pull it all together
		Render render = new Render(scene, imageWriter);


		//Records what the camera sees
		render.renderImage();
		//Prints it all to a file
		imageWriter.writeToImage();
	}
	
	@Test
	public void pointLightRenderTest() {
		//Sets up a "sun" with white light
		AmbientLight ambientLight = new AmbientLight(new Color(255, 255, 255), 1.0);
		//Instantiates a new Camera with default values (point at the origin, facing down the negative z axis)
		Camera camera = new Camera();
		//Sets up the list of geometries in our scene
		List<Geometry> geoList = new ArrayList<Geometry>();
		//Sets up the sphere in our scene and adds it to the list
		Sphere sphere = new Sphere(6.0, new Point3D(0.0, 0.0, -10.0), new Material(0.999, 0.99, 5, 0, 0), new Color(148,0,211));
		geoList.add(sphere);
		//Sets up an empty list of lights
		List<LightSource> lights = new ArrayList<LightSource>();
		//Creates a spotlight to add to our scene
		PointLight pointLight =new PointLight(new Point3D(2, 2, -3.5), 0.15, 0.15, 0.15, new Color(255, 138, 119));
		lights.add(pointLight);
		//Creates a scene to hold it all
		Scene scene = new Scene("PointLightTestScene", new Color(0,0,0), ambientLight, lights, geoList, camera, 30.0);
		//Creates an ImageWriter instance to help write down what our camera sees
		ImageWriter imageWriter = new ImageWriter("PointLightTest2", 1000, 1000, 100, 100);
		//Creates a Render instance to pull it all together
		Render render = new Render(scene, imageWriter);


		//Records what the camera sees
		render.renderImage();
		//Prints it all to a file
		imageWriter.writeToImage();
	}

	@Test
	public void shadowRaysTest() {
		AmbientLight ambientLight = new AmbientLight(new Color(255, 255, 255), 1.0);
		Camera camera = new Camera();
		List<Geometry> geoList = new ArrayList<Geometry>();
		
		Triangle floor1 = new Triangle(new Point3D(-18, -18, -4), new Point3D(18, 18, -5), new Point3D(-18, 18, -5), new Material(0.999, 0.99, 5, 0, 0), new Color(200, 200, 200));
		Triangle floor2 = new Triangle(new Point3D(-18, -18, -4), new Point3D(18, 18, -5), new Point3D(18, -18, -4), new Material(0.999, 0.99, 5, 0, 0), new Color(200, 200, 200));
		
		geoList.add(floor1);
		geoList.add(floor2);

		Sphere ball = new Sphere(1.4985, new Point3D(0, 0, -2.5), new Material(0.999, 0.99, 5, 0, 0), new Color(0, 0, 255));
		geoList.add(ball);
		
		List<LightSource> lightList = new ArrayList<LightSource>();
		
		PointLight pointlight = new PointLight(new Point3D(1.0, 0, -0.4), 0.15, 0.15, 0.15, new Color(255, 138, 119));
		lightList.add(pointlight);
		
		Scene scene = new Scene("Shadow rays test", new Color(50, 50, 50), ambientLight, lightList, geoList, camera, 10.0);
		ImageWriter imageWriter = new ImageWriter("ShadowRaysTest9", 1000, 1000, 100, 100);
		
		//Creates a Render instance to pull it all together
		Render render = new Render(scene, imageWriter);

		//Records what the camera sees
		render.renderImage();
		//Prints it all to a file
		imageWriter.writeToImage();
	}
	
	@Test
	public void reflectionsTest() {

		//Sets up a "sun" with white light
		AmbientLight ambientLight = new AmbientLight(new Color(255, 255, 255), 1.0);
		
		//Instantiates a new Camera with default values (point at the origin, facing down the negative z axis)
		Camera camera = new Camera();
		
		//Sets up the list of geometries in our scene
		List<Geometry> geoList = new ArrayList<Geometry>();
		
		//Sets up the "floor" in our scene and adds it to the list
		Triangle floor1 = new Triangle(new Point3D(-18, -18, -3), new Point3D(18, 18, -5), new Point3D(-18, 18, -5), new Material(0.8, 0.99, 5, 0.99, 0), new Color(200, 200, 200));
		Triangle floor2 = new Triangle(new Point3D(-18, -18, -3), new Point3D(18, 18, -5), new Point3D(18, -18, -3), new Material(0.8, 0.99, 5, 0.99, 0), new Color(200, 200, 200));
		
		geoList.add(floor1);
		geoList.add(floor2);

		//Creates a mirror to test reflections
		Triangle mirror = new Triangle(new Point3D(-8.15, 0.33, -4.51), new Point3D(2.3, 10.05, -4.78), new Point3D(-4, 4, -2), new Material(0.8, 0.99, 5, 0.99, 0), new Color(200, 200, 200));
		geoList.add(mirror);
		
		//Creates a very reflective sphere in the middle of our scene and drops it in
		Sphere ball = new Sphere(1.4985, new Point3D(0, 1, -2.9), new Material(0.8, 0.99, 5, 0.99, 0.99), new Color(0, 0, 255));
		geoList.add(ball);
		
		Sphere ball2 = new Sphere(1, new Point3D(0, -3, -2.5), new Material(0.8, 0.99, 5, 0.99, 0.99), new Color(0, 0, 255));
//		geoList.add(ball2);
		
		//Sets up an empty list of lights
		List<LightSource> lights = new ArrayList<LightSource>();
		//Creates a spotlight to add to our scene
		PointLight pointLight = new PointLight(new Point3D(1, -2, -1), 0.9, 0.9, 0.9, new Color(214,60,111));
		lights.add(pointLight);
		
		
		//Creates a scene to hold it all
		Scene scene = new Scene("ReflectionsTestScene", new Color(0,0,0), ambientLight, lights, geoList, camera, 10.0);
		//Creates an ImageWriter instance to help write down what our camera sees
		ImageWriter imageWriter = new ImageWriter("ReflectionsTest8", 1000, 1000, 100, 100);
		//Creates a Render instance to pull it all together
		Render render = new Render(scene, imageWriter);


		//Records what the camera sees
		render.renderImage();
		//Prints it all to a file
		imageWriter.writeToImage();
	}

	@Test
	public void ballOnTheFloorTest() {
		AmbientLight ambientLight = new AmbientLight(new Color(255, 255, 255), 1.0);
		Camera camera = new Camera();
		List<Geometry> geoList = new ArrayList<Geometry>();
//		Material material = new Material(0.8, 0.99, 5, 0.99, 0.5);
		Plane floor = new Plane(new Point3D(-10, -3.3, -1), new Point3D(10, -3.3, -1), new Point3D(0, -3.3, -12), new Material(), new Color(200, 200, 200));
		geoList.add(floor);

		Sphere ball = new Sphere(1.4985, new Point3D(0, -0.3, -1.75), new Material(), new Color(0, 0, 255));
		geoList.add(ball);
		
		Sphere ballInBall = new Sphere(1.0, new Point3D(0, -0.3, -1.75), new Material(), new Color(255,255, 0));
		geoList.add(ballInBall);
		
		List<LightSource> lightList = new ArrayList<LightSource>();
		
		PointLight pointlight = new PointLight(new Point3D(-4, 4, -1.75), 0.9, 0.9, 0.9, new Color(255, 0, 0));
//		lightList.add(pointlight);
		
		PointLight greenlight = new PointLight(new Point3D(4, 4, -1.75), 0.5, 0.5, 0.5, new Color(0, 255, 0));
//		lightList.add(greenlight);
		
		Scene scene = new Scene("Ball on floor test", new Color(50, 50, 50), ambientLight, lightList, geoList, camera, 10.0);
		ImageWriter imageWriter = new ImageWriter("BallOnTheFloorTestWithR&RBackground", 1000, 1000, 100, 100);
		
		//Creates a Render instance to pull it all together
		Render render = new Render(scene, imageWriter);

		//Records what the camera sees
		render.renderImage();
		//Prints it all to a file
		imageWriter.writeToImage();
	}
	
	@Test
	public void floorTest() {
		AmbientLight ambientLight = new AmbientLight(new Color(255, 255, 255), 1.0);
		Camera camera = new Camera();
		List<Geometry> geoList = new ArrayList<Geometry>();
		Plane floor = new Plane(new Point3D(-10, -3, -1), new Point3D(10, -3, -1), new Point3D(0, -3, -12), new Material(), new Color(200, 200, 200));
		geoList.add(floor);
//		Triangle floor1 = new Triangle(new Point3D(-1.5, -3, 0), new Point3D(1.5, -3, 0), new Point3D(1.5, -3, 3), new Material(), new Color(200, 200, 200));
//		geoList.add(floor1);
//		Triangle floor2 = new Triangle(new Point3D(-1.5, -3, 0), new Point3D(-1.5, -3, 3), new Point3D(1.5, -3, 3), new Material(), new Color(200, 200, 200));
//		geoList.add(floor2);
		List<LightSource> lightList = new ArrayList<LightSource>();
		PointLight pointlight = new PointLight(new Point3D(-25, 25, -20.0), 0.9, 0.9, 0.9, new Color(255, 0, 0));
		//lightList.add(pointlight);
		PointLight greenlight = new PointLight(new Point3D(0, 0, -1), 0.5, 0.5, 0.5, new Color(0, 255, 0));
		lightList.add(greenlight);
		Scene scene = new Scene("Floor test", new Color(30, 30, 30), ambientLight, lightList, geoList, camera, 10.0);
		ImageWriter imageWriter = new ImageWriter("floorTest5", 1000, 1000, 100, 100);
		//Creates a Render instance to pull it all together
		Render render = new Render(scene, imageWriter);

		//Records what the camera sees
		render.renderImage();
		//Prints it all to a file
		imageWriter.writeToImage();
	}
}
