package renderer;

import elements.*;
import geometries.*;
import primitives.*;
import scene.*;

import java.awt.Color;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

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
		//Creates a map to hold the closest point
		Map<Geometry, Point3D> closestPoint = new HashMap<Geometry, Point3D>();
		
		//Loops through all the geometries that the ray intersected
		for(Geometry geo : map.keySet()) {
			//Loops through every point in that geometry that the ray intersected
			for(Point3D point : map.get(geo)) {
				//Checks if given point is closest, as mentioned above
				if(_scene.getCamera().getP0().distanceTo(point) < smallestDistance) {
					//If so, updates the shortest distance
					smallestDistance = _scene.getCamera().getP0().distanceTo(point);
					//Clears the map so there isn't more than one point inside
					closestPoint.clear();
					//Updates the closest point
					closestPoint.put(geo, new Point3D(point));
				}
			}
		}
		
		return closestPoint;
	}
	
	//Returns the color that the camera would see when looking at this point if it intercepted and object
	private Color getColorAtPoint(Geometry geometry, Point3D point) {
		
		//Stores the number of lights in the scene for calculation later
		int numberOfLights = _scene.getLightsList().size();
		//Grabs the ambient light from the scene
		Color ambientLight = _scene.getAmbientLight().getIntensity();
		//Grabs the light coming out of the geometry itself
		Color emissionLight = geometry.getEmission();
		
		int totalRed = ambientLight.getRed() + emissionLight.getRed();
		int totalGreen = ambientLight.getGreen() + emissionLight.getGreen();
		int totalBlue = ambientLight.getBlue() + emissionLight.getBlue();
//		if(numberOfLights != 0) {
			
			//Values to hold the light that's been diffused
			int diffusedLightRed = 0;
			int diffusedLightBlue = 0;
			int diffusedLightGreen = 0;
			
			//Values to hold the specular light
			int specularLightRed = 0;
			int specularLightBlue = 0;
			int specularLightGreen = 0;


			//Gets a list of all the lights in the place
			Iterator<LightSource> lights = _scene.getLightsIterator();

			while(lights.hasNext()) {
				LightSource light = lights.next();

				//Check to make sure the reflected light would actually make it to the camera (It's not on the other side of the Geometry)
				if(Math.signum(light.getL(point).dotProduct(geometry.getNormal(point))) == Math.signum(new Vector(_scene.getCamera().getP0().subtract(point)).dotProduct(geometry.getNormal(point)))) {
					//Calculates the diffused light generated by this light source
					Color calculatedDiffusedLight = calcDiffuseComp(geometry.getMaterial().getKd(), geometry.getNormal(point).normalize(), light.getL(point).normalize(), light.getIntensity(point));
					//Adds the new diffused light to the Color holding all the light so far, but scales it down so as not to go past the bounds of color values
					totalRed += calculatedDiffusedLight.getRed()/2;
					totalGreen += calculatedDiffusedLight.getGreen()/2;
					totalBlue += calculatedDiffusedLight.getBlue()/2;

					//Calculates the diffused light generated by this light source
					Color calculatedSpecularLight = calcSpecularComp(geometry.getMaterial().getKs(), new Vector(_scene.getCamera().getP0().subtract(point)).normalize(), geometry.getNormal(point).normalize(), light.getL(point).normalize(), geometry.getMaterial().getShininess(), light.getIntensity(point));
					//Adds the new diffused light to the Color holding all the light so far, but scales it down so as not to go past the bounds of color values
					totalRed += calculatedSpecularLight.getRed()/2;
					totalGreen += calculatedSpecularLight.getGreen()/2;
					totalBlue += calculatedSpecularLight.getBlue()/2;
				}

			}
			//Finds the color of the components of the light thats been diffused and bounces off specularly
//			Color diffusedLight = new Color(diffusedLightRed/numberOfLights, diffusedLightBlue/numberOfLights, diffusedLightGreen/numberOfLights);
//			Color specularLight = new Color(specularLightRed/numberOfLights, specularLightBlue/numberOfLights, specularLightGreen/numberOfLights);

			//Combines them into a new color
			Color combinedColor = new Color((totalRed/(numberOfLights+2)), (totalGreen/(numberOfLights+2)), (totalBlue/(numberOfLights+2)));

			return combinedColor;
	}
	
	// private method to calculate the diffuse light (according to the formula from slides)
	private Color calcDiffuseComp(Double kd, Vector normal, Vector l, Color intensity) {
		double factor = kd*(normal.dotProduct(l));
		return scaleColor(intensity, factor);
	}
	
	// private method to calculate the specular light (according to the formula from slides)
	private Color calcSpecularComp(Double ks, Vector v, Vector normal, Vector l, int shininess, Color intensity) {
		Vector r = l.subtract(normal.scale(2*l.dotProduct(normal)));
		double factor = ks*Math.pow(v.normalize().dotProduct(r.normalize()), shininess);
		return scaleColor(intensity, factor);
	}
	
	//Helper method to scale colors properly without going out of range
	public static Color scaleColor(Color color, double factor) {
		int newRed = ((int)(color.getRed()*factor) <= 255 ? (int)(color.getRed()*factor) : 255);
		newRed = (newRed >= 0 ? newRed : 0);
		int newGreen = ((int)(color.getGreen()*factor) <= 255 ? (int)(color.getGreen()*factor) : 255);
		newGreen = (newGreen >= 0 ? newGreen : 0);
		int newBlue = ((int)(color.getBlue()*factor) <= 255 ? (int)(color.getBlue()*factor) : 255);
		newBlue = (newBlue >= 0 ? newBlue : 0);
		
		return new Color(newRed, newGreen, newBlue);
	}
}
