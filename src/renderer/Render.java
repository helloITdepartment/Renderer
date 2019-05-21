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
				
				Ray r = _scene.getCamera().constructRayThroughPixel(_imageWriter.getWidth(), _imageWriter.getHeight(), i, j, _scene.getScreenDistance(), _imageWriter.getNx(), _imageWriter.getNy());
				
				Map<Geometry, List<Point3D>>  pointsIntersected = getSceneRayIntersections(r);
				if (pointsIntersected.isEmpty()) {
					_imageWriter.writePixel(i, j, _scene.getBackgroundColor());
				} else {
					Map<Geometry, Point3D> closestPointMap = getClosestPoint(pointsIntersected);
					Geometry closestGeometry = (Geometry) closestPointMap.keySet().toArray()[0];
					Point3D closestPoint = (Point3D) closestPointMap.values().toArray()[0];
					_imageWriter.writePixel(i, j, getColorAtPoint(closestGeometry, closestPoint));
				}
			}
		}
		
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
					//Do nothing. If we wrote a black pixel, it would overwrite whatever image was underneath it
				}
			}
		}
	}
	
	//Returns a list of all of the Points in the scene that would be intersected by a given Ray
	private Map<Geometry, List<Point3D>> getSceneRayIntersections(Ray r) {
		//Gets an iterator so we can look at all the geometries in a scene
		Iterator<Geometry> geometriesIterator = _scene.getGeometriesIterator();
		//Initializes a HashMap to hold all the points of intersection and the geometries with which they're associated
		Map<Geometry, List<Point3D>> totalSceneIntersections = new HashMap<Geometry, List<Point3D>>();
		
		//Loops over all the geometries and for each...
		while(geometriesIterator.hasNext()) {
			Geometry geometry = geometriesIterator.next();
			//Finds the points of intersection with the given ray
			List<Point3D> geometrysIntersectionList = geometry.findIntersection(r);
			//And adds then to the master list if it's empty
			if(!geometrysIntersectionList.isEmpty()) {
				totalSceneIntersections.put(geometry, geometrysIntersectionList);
			}
		}

		return totalSceneIntersections;
	}
	
	//Loops over all of the points and checks if they are the closer than the current record holder for closest point,
	//And declares them the new closest point if they are
	private Map<Geometry, Point3D> getClosestPoint(Map<Geometry, List<Point3D>> map) {
		//initializes the smallest distance at infinity so that any point in the list will be closer
		Double smallestDistance = Double.POSITIVE_INFINITY;
		Map<Geometry, Point3D> closestPoint = new HashMap<Geometry, Point3D>();
		
		//Loops through all the geometries that the ray intersected
		for(Geometry geo : map.keySet()) {
			//Loops through every point in that geometry that the ray intersected
			for(Point3D point : map.get(geo)) {
				//Checks if given point is closest, as mentioned above
				if(_scene.getCamera().getP0().distanceTo(point) < smallestDistance) {
					smallestDistance = _scene.getCamera().getP0().distanceTo(point);
					closestPoint.clear();
					closestPoint.put(geo, new Point3D(point));
				}
			}
		}
		
		return closestPoint;
	}
	
	//Returns the color that the camera would see when looking at this point if it intercepted and object
	private Color getColorAtPoint(Geometry geometry, Point3D point) {
		//Grabs the ambient light from the scene
		Color ambientLight = _scene.getAmbientLight().getIntensity();
		//Grabs the light coming out of the geometry itself
		Color emissionLight = geometry.getEmission();
		//A value to hold the light that's been diffused
		Color diffusedLight = new Color(0,0,0);
		//A value to hold the specular light
		Color specularLight = new Color(0,0,0);

		//Gets a list of all the lights in the place
		Iterator<LightSource> lights = _scene.getLightsIterator();
		
		while(lights.hasNext()) {
			LightSource light = lights.next();
			
			//Calculates the diffused light generated by this light source
			Color calculatedDiffusedLight = calcDiffuseComp();
			//Adds the new diffused light to the Color holding all the light so far, but scales it down so as not to go past the bounds of color values
			diffusedLight = new Color(diffusedLight.getRed() + calculatedDiffusedLight.getRed()/_scene.numberOfLights(),
									diffusedLight.getGreen() + calculatedDiffusedLight.getGreen()/_scene.numberOfLights(),
									diffusedLight.getBlue() + calculatedDiffusedLight.getBlue()/_scene.numberOfLights());
			
			//Calculates the diffused light generated by this light source
			Color calculatedSpecularLight = calcSpecularComp();
			//Adds the new diffused light to the Color holding all the light so far, but scales it down so as not to go past the bounds of color values
			specularLight = new Color(specularLight.getRed() + calculatedSpecularLight.getRed()/_scene.numberOfLights(),
									specularLight.getGreen() + calculatedSpecularLight.getGreen()/_scene.numberOfLights(),
									specularLight.getBlue() + calculatedSpecularLight.getBlue()/_scene.numberOfLights());
			
			
		}
		
		//Combines them into a new color
		Color combinedColor = new Color((ambientLight.getRed() + emissionLight.getRed() + diffusedLight.getRed() + specularLight.getRed())/4,
										(ambientLight.getGreen() + emissionLight.getGreen() + diffusedLight.getGreen() + specularLight.getGreen())/4,
										(ambientLight.getBlue() + emissionLight.getBlue() + diffusedLight.getBlue() + specularLight.getBlue())/4);
		
		return combinedColor;
	}
	
	private Color calcDiffuseComp() {
		return new Color(0,0,0);
	}
	
	private Color calcSpecularComp() {
		return new Color(0,0,0);
	}
}
