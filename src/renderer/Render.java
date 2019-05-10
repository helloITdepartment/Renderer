package renderer;

import elements.*;
import geometries.*;
import primitives.*;
import scene.*;

import java.awt.Color;
import java.util.*;

//Complete class for rendering out a scene
public class Render {
	//Scene to be rendered out
	Scene _scene;
	//Image writer to handle all the image writing bits
	ImageWriter _imageWriter;
	
	
	//Constructors
	//Default constructor
	public Render() {
		_scene = new Scene();
		_imageWriter = new ImageWriter("", 0, 0, 0, 0);
	}
	
	//Parameterized constructor
	public Render(Scene scene, ImageWriter imageWriter) {
		_scene = scene;
		_imageWriter = imageWriter;
	}
	
	//Copy constructor
	public Render(Render other) {
		_scene = other.getScene();
		_imageWriter = other.getImageWriter();
	}
	
	
	//Getters
	public Scene getScene() {
		return new Scene(_scene);
	}
	
	public ImageWriter getImageWriter() {
		return new ImageWriter(_imageWriter);
	}
	
	//Setters
	public void setScene(Scene scene) {
		_scene = scene;
	}
	
	public void setImageWriter(ImageWriter imageWriter) {
		_imageWriter = imageWriter;
	}
	
	public void renderImage() {
		for(int i = 0; i < _imageWriter.getWidth(); i++) {
			for(int j = 0; j < _imageWriter.getHeight(); j++) {
				
//				Ray r = _scene.getCamera().constructRayThroughPixel(_imageWriter.getNx(), _imageWriter.getNy(), i, j, _scene.getScreenDistance(), _imageWriter.getWidth(), _imageWriter.getHeight());
				Ray r = _scene.getCamera().constructRayThroughPixel(_imageWriter.getWidth(), _imageWriter.getHeight(), i, j, _scene.getScreenDistance(), _imageWriter.getNx(), _imageWriter.getNy());
//				System.out.println(r.getDirection().getHead());
				
				List<Point3D> pointsIntersected = getSceneRayIntersections(r);
				if (pointsIntersected.isEmpty()) {
					_imageWriter.writePixel(i, j, _scene.getBackgroundColor());
				} else {
//					System.out.println("foo");
					Point3D closestPoint = getClosestPoint(pointsIntersected);
//					System.out.println(closestPoint);
					_imageWriter.writePixel(i, j, getColorAtPoint(closestPoint));
				}
			}
		}
		
//		_imageWriter.writeToImage();
	}
	
	//Prints a grid onto the image, with squares of (interval) across and up, to help visualize things
	public void printGrid(int interval) {
		for(int i = 0; i < _imageWriter.getWidth(); i++) {
			for(int j = 0; j < _imageWriter.getHeight(); j++) {
				// if i or j are multiples of the interval, make the pixel at the x,y coordinate white, if not, make the pixel black
				if(i % interval == 0 || j % interval == 0) {
					// 255 is white, make the current pixel white
					_imageWriter.writePixel(i, j, 255, 255, 255);
				}
				else{
					// 0 is black, make the current pixel black
//					_imageWriter.writePixel(i, j, 0, 0, 0);
				}
			}
		}
	}
	
	//Returns a list of all of the Points in the scene that would be intersected by a given Ray
	private List<Point3D> getSceneRayIntersections(Ray r) {
		//Gets an iterator so we can look at all the geometries in a scene
		Iterator<Geometry> geometriesIterator = _scene.getGeometriesIterator();
		//Initializes a list to hold all the points of intersection
		List<Point3D> totalSceneIntersections = new ArrayList<Point3D>();
		
		//Loops over all the geometries and for each...
		while(geometriesIterator.hasNext()) {
			Geometry geometry = geometriesIterator.next();
			//Finds the points of intersection with the given ray
			List<Point3D> geometrysIntersectionList = geometry.findIntersection(r);
			//And adds then to the master list
			totalSceneIntersections.addAll(geometrysIntersectionList);
		}
		
		return totalSceneIntersections;
	}
	
	//Loops over all of the points and checks if they are the closer than the current record holder for closest point,
	//And declares them the new closest point if they are
	private Point3D getClosestPoint(List<Point3D> list) {
		//initializes the smallest distance at infinity so that any point in the list will be closer
		Double smallestDistance = Double.POSITIVE_INFINITY;
		Point3D closestPoint = new Point3D();
		
		for(Point3D point : list) {
			if(_scene.getCamera().getP0().distanceTo(point) < smallestDistance) {
				smallestDistance = _scene.getCamera().getP0().distanceTo(point);
				closestPoint = point;
			}
		}
		
		return closestPoint;
	}
	
	//Returns the color that the camera would see when looking at this point if it intercepted and object
	private Color getColorAtPoint(Point3D point) {
		//For now just returns the ambient light in the scene
		return _scene.getAmbientLight().getIntensity();
	}
}
