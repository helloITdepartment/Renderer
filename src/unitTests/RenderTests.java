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
		AmbientLight ambientLight = new AmbientLight(new Color(202, 22, 152), 1.0);
//		Sphere sphere = new Sphere(20.0, new Point3D(0.0, 0.0, -30.0));
		Triangle triangle = new Triangle(new Point3D(0, 1, -1), new Point3D(-1, -1, -1), new Point3D(1, -1, -1));
		List<Geometry> list = new ArrayList<Geometry>();
		list.add(triangle);
//		Ray r = new Ray(new Point3D(0.0, 0.0, 0.0), new Vector(0.0, 0.0, -1.0));
//		System.out.println(sphere.findIntersection(r));
		Camera camera = new Camera();
		Scene scene = new Scene("TestScene", new Color(0, 0, 0), ambientLight, list, camera, 1.0);
		//Width, height, nx, ny
		//Width and height of file, 
		ImageWriter imageWriter = new ImageWriter("RenderTest", 100, 100, 100, 100);
		
		Render render = new Render(scene, imageWriter);
		
		render.renderImage();
		render.printGrid(50);
		imageWriter.writeToImage();
	}
}
