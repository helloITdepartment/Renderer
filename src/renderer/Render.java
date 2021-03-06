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
	//Smallest factor to scale the color by. If we're scaling any lower than this we might as well just return black
	public static final double MINIMUM_FACTOR = 0.001;
	//Known level of recursion to know how to scale the final Color at the end of calcColor 
	public static final int LEVELS_OF_RECURSION = 1;
	//Variable used to toggle between using the focus plane and rendering regular
	public static final boolean USING_FOCUS = true;
	
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
		if(USING_FOCUS) {
			renderImageWithFocus();
		}else {
			renderImageRegular();
		}
	}
	
	public void renderImageRegular() {
		for(int i = 0; i < _imageWriter.getWidth(); i++) {
			
			for(int j = 0; j < _imageWriter.getHeight(); j++) {
				//Constructs the ray shot from the camera through a particular pixel
				Ray r = _scene.getCamera().constructRayThroughPixel(_imageWriter.getWidth(), _imageWriter.getHeight(), i, j, _scene.getScreenDistance(), _imageWriter.getNx(), _imageWriter.getNy());
				
				//The list of everything intersected by that ray
				Map<Geometry, List<Point3D>>  pointsIntersected = getSceneRayIntersections(r);
				
				if (pointsIntersected.isEmpty()) {//Nothing in front of the ray, just use the background color
					
					_imageWriter.writePixel(i, j, _scene.getBackgroundColor());
					
				} else {
					//Calculates which intersected point is closest, and returns a map noting the point and which geometry it's on
					Map<Geometry, Point3D> closestPointMap = getClosestPoint(pointsIntersected, r.getSource());
					//Separates out the values to pass into getColor
					Geometry closestGeometry = (Geometry) closestPointMap.keySet().toArray()[0];
					Point3D closestPoint = (Point3D) closestPointMap.values().toArray()[0];
					//Calculate the color on the geometry at the right point, and start the recursion level off at the predetermined level, and a factor off
					//Then writes that color to pixel (i,j)
					_imageWriter.writePixel(i, j, getColorAtPoint(closestGeometry, closestPoint, r, LEVELS_OF_RECURSION, 1.0));
				}
			}
		}
		
	}

	public void renderImageWithFocus() {
		for(int i = 0; i < _imageWriter.getWidth(); i++) {

			for(int j = 0; j < _imageWriter.getHeight(); j++) {

				int totalRed = 0;
				int totalGreen = 0;
				int totalBlue = 0;

				//Constructs the rays shot from the camera through a particular pixel

				Ray[] focusRays = _scene.getCamera().constructFocusRaysThroughPixel(_imageWriter.getWidth(), _imageWriter.getHeight(), i, j, _scene.getScreenDistance(),
																					_imageWriter.getNx(), _imageWriter.getNy(), _scene.getFocalDistance(),
																					_scene.getCamera().getAperture());
				
				for (Ray ray : focusRays) {

					//The list of everything intersected by that ray
					Map<Geometry, List<Point3D>>  pointsIntersected = getSceneRayIntersections(ray);

					if (pointsIntersected.isEmpty()) {//Nothing in front of the ray, don't add anything to the color components (equivalent to adding black)
						
					} else {
						//Calculates which intersected point is closest, and returns a map noting the point and which geometry it's on
						Map<Geometry, Point3D> closestPointMap = getClosestPoint(pointsIntersected, ray.getSource());
						//Separates out the values to pass into getColor
						Geometry closestGeometry = (Geometry) closestPointMap.keySet().toArray()[0];
						Point3D closestPoint = (Point3D) closestPointMap.values().toArray()[0];
						//Calculate the color on the geometry at the right point, and start the recursion level off at the predetermined level, and a factor off
						//Then writes that color to pixel (i,j)
						Color color = getColorAtPoint(closestGeometry, closestPoint, ray, LEVELS_OF_RECURSION, 1.0);
						totalRed += color.getRed()/4;
						totalGreen += color.getGreen()/4;
						totalBlue += color.getBlue()/4;
					}

				}
				Color totalColor = new Color(totalRed, totalGreen, totalBlue);
				_imageWriter.writePixel(i, j, totalColor);
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
	private Map<Geometry, Point3D> getClosestPoint(Map<Geometry, List<Point3D>> map, Point3D sourcePoint) {
		//initializes the smallest distance at infinity so that any point in the list will be closer
		Double smallestDistance = Double.POSITIVE_INFINITY;
		//Creates a map to hold the closest point
		Map<Geometry, Point3D> closestPoint = new HashMap<Geometry, Point3D>();
		
		//Loops through all the geometries that the ray intersected
		for(Geometry geo : map.keySet()) {
			//Loops through every point in that geometry that the ray intersected
			for(Point3D point : map.get(geo)) {
				//Checks if given point is closest, as mentioned above
				if(sourcePoint.distanceTo(point) < smallestDistance) {
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
	
	/*Returns the color that the camera would see when looking at this point if it intercepted an object, taking in a level of recursion and effective brightness, 
	 * used for knowing whether to continue calculating or just return black*/
	private Color getColorAtPoint(Geometry geometry, Point3D point, Ray alongRay, int level, double factor) {
		
		//First things first, check if it's even worth calculating a color or if we've recursed too deep already
		if(level <= 0 || factor < MINIMUM_FACTOR) {
			return Color.BLACK;
		}//otherwise, go ahead and calculate the color..
		
		//Stores the number of lights in the scene for calculation later
		int numberOfLights = _scene.getLightsList().size();
		//Grabs the ambient light from the scene
		Color ambientLight = _scene.getAmbientLight().getIntensity();
		//Grabs the light coming out of the geometry itself
		Color emissionLight = geometry.getEmission();
		
		int totalRed = ambientLight.getRed() + emissionLight.getRed();
		int totalGreen = ambientLight.getGreen() + emissionLight.getGreen();
		int totalBlue = ambientLight.getBlue() + emissionLight.getBlue();

		//Gets a list of all the lights in the place
		Iterator<LightSource> lights = _scene.getLightsIterator();

		while(lights.hasNext()) {//For each light in the scene..
			LightSource light = lights.next();

			//Check to make sure the reflected light would actually make it to the camera (It's not on the other side of the Geometry)
			if(Math.signum(light.getL(point).dotProduct(geometry.getNormal(point))) != Math.signum(new Vector(_scene.getCamera().getP0().subtract(point)).dotProduct(geometry.getNormal(point)))) {
				//Checks to see if the point would even get hit by the LightSource (ie that it isn't in the shadow of something else):
				//Creates a new Ray starting at the point in question with a direction opposite the L from the light to the point (going back to the light)
				Ray rayBackToLight = new Ray(point, new Vector(light.getL(point)).scale(-1).normalize());
				//Adjusts its source a little bit so a geometry doesn't block itself (sliding it up the vector a little closer to the light)
				rayBackToLight.setSource(rayBackToLight.getSource().add(rayBackToLight.getDirection().scale(.0000001)));
				//Checks to see if there's any other geometry along the path
				Map<Geometry, List<Point3D>> geometriesBlockingLight = getSceneRayIntersections(rayBackToLight);

				if(geometriesBlockingLight.isEmpty()) {//If there's nothing in the way...
					//Calculates the diffused light generated by this light source
					Color calculatedDiffusedLight = calcDiffuseComp(geometry.getMaterial().getKd(), geometry.getNormal(point), light.getL(point), light.getIntensity(point));
					//Adds the new diffused light to the Color holding all the light so far, but scales it down so as not to go past the bounds of color values
					totalRed += calculatedDiffusedLight.getRed()/2;
					totalGreen += calculatedDiffusedLight.getGreen()/2;
					totalBlue += calculatedDiffusedLight.getBlue()/2;

					//Calculates the diffused light generated by this light source
					Color calculatedSpecularLight = calcSpecularComp(geometry.getMaterial().getKs(), new Vector(_scene.getCamera().getP0().subtract(point)), geometry.getNormal(point), light.getL(point), geometry.getMaterial().getShininess(), light.getIntensity(point));
					//Adds the new diffused light to the Color holding all the light so far, but scales it down so as not to go past the bounds of color values
					totalRed += calculatedSpecularLight.getRed()/2;
					totalGreen += calculatedSpecularLight.getGreen()/2;
					totalBlue += calculatedSpecularLight.getBlue()/2;

				}
			}

		}

		//Calculates the color at the point produced by reflection:
		//First gets the ray along which to evaluate the color
		Ray reflectedRay = constructReflectedRay(geometry.getNormal(point), point, alongRay);
		//Calculates which geometry get intersected by that ray first, if any
		Map<Geometry, List<Point3D>>  pointsIntersected = getSceneRayIntersections(reflectedRay);
		//Sets the color gained by reflection to be the background by default if it doesn't hit anything
		Color colorFromReflection = _scene.getBackgroundColor();
		if (!pointsIntersected.isEmpty()) {//But if it does...
			//Calculates the geometry and the point on that geometry that get hit
			Map<Geometry, Point3D> closestPointMap = getClosestPoint(pointsIntersected, reflectedRay.getSource());
			Geometry closestGeometry = (Geometry) closestPointMap.keySet().toArray()[0];
			Point3D closestPoint = (Point3D) closestPointMap.values().toArray()[0];
			//And updates the color to be the color of the intersected point
			colorFromReflection = getColorAtPoint(closestGeometry, closestPoint, reflectedRay, level-1, factor*geometry.getMaterial().getKr());
		}
		//And adds its elements to the running total
		totalRed += colorFromReflection.getRed();
		totalGreen += colorFromReflection.getGreen();
		totalBlue += colorFromReflection.getBlue();
		
		//Calculates the color at the point produced by transparency:
		//First gets the ray along which to evaluate the color
		Ray refractedRay = constructRefractedRay(point, alongRay);
		//Calculates which geometry get intersected by that ray first, if any
		pointsIntersected = getSceneRayIntersections(refractedRay);
		//Sets the color gained by refraction to be the background by default if it doesn't hit anything
		Color colorFromRefraction = _scene.getBackgroundColor();
		if (!pointsIntersected.isEmpty()) {//But if it does...
			//Calculates the geometry and the point on that geometry that get hit
			Map<Geometry, Point3D> closestPointMap = getClosestPoint(pointsIntersected, refractedRay.getSource());
			Geometry closestGeometry = (Geometry) closestPointMap.keySet().toArray()[0];
			Point3D closestPoint = (Point3D) closestPointMap.values().toArray()[0];
			//And updates the color to be the color of the intersected point
			colorFromRefraction = getColorAtPoint(closestGeometry, closestPoint, refractedRay, level-1, factor*geometry.getMaterial().getKt());
		}
		//And adds its elements to the running total
		totalRed += colorFromRefraction.getRed();
		totalGreen += colorFromRefraction.getGreen();
		totalBlue += colorFromRefraction.getBlue();

		//Combines them into a new color
		//Color combinedColor = new Color((totalRed/(numberOfLights+3)), (totalGreen/(numberOfLights+3)), (totalBlue/(numberOfLights+3)));
		//+2 for ambient and emission, then +2 for reflective and transparency
		Color combinedColor = new Color((totalRed/(numberOfLights+2+2)), (totalGreen/(numberOfLights+2+2)), (totalBlue/(numberOfLights+2+2)));

		return combinedColor;
	}

	// private method to calculate the diffuse light (according to the formula from slides)
	private Color calcDiffuseComp(Double kd, Vector normal, Vector l, Color intensity) {
		double factor = Math.abs(kd*(normal.dotProduct(l)));
		return scaleColor(intensity, factor);
	}

	// private method to calculate the specular light (according to the formula from slides)
	private Color calcSpecularComp(Double ks, Vector v, Vector normal, Vector l, int shininess, Color intensity) {
		Vector r = l.subtract(normal.scale(2*l.dotProduct(normal)));
		double factor = ks*Math.abs(Math.pow(v.normalize().dotProduct(r.normalize()), shininess));
		return scaleColor(intensity, factor);
	}
	
	//Private helper method to construct the ray bounced off an objects
	private Ray constructReflectedRay(Vector normal, Point3D point, Ray originalRay) {
		Vector originalVector = originalRay.getDirection();
		//Takes the original vector and uses it to calculate the reflected vector
		Vector outgoingVector = originalVector.subtract(normal.scale(2*originalVector.dotProduct(normal)));
		//Vector outgoingVector = normal.scale(2*normal.dotProduct(originalVector)).subtract(originalVector);
		//Creates a new Ray to return
		Ray reflectedRay = new Ray(point, outgoingVector);
		//Slides the point that the ray emanates (is that how you spell it?) from up the vector a little bit so that when we check the next intersection, we don't intersect ourselves
		reflectedRay.setSource(point.add(reflectedRay.getDirection().scale(.0001)));
		return reflectedRay;
	}
	
	//Private helper method to construct the ray that passes through a transparent object
	private Ray constructRefractedRay(Point3D point, Ray ray) {
		//The direction that the ray is traveling in and that the new one will travel in, because we are assuming a refractive index of 1 for all geometries
		Vector direction = ray.getDirection().normalize();
		//Takes the point of intersection and moves a little bit up the ray so we don't intersect with ourselves
		Point3D source = point.add(direction.scale(0.0001));
		
		return new Ray(source, direction);
		
	}
	
	//Helper method to scale colors properly without going out of range
	public static Color scaleColor(Color color, double factor) {
		//Checks each component. If it's above 255, snaps it back to 255. If it's below 0, snaps it back to 0.
		int newRed = ((int)(color.getRed()*factor) <= 255 ? (int)(color.getRed()*factor) : 255);
		newRed = (newRed >= 0 ? newRed : 0);
		
		int newGreen = ((int)(color.getGreen()*factor) <= 255 ? (int)(color.getGreen()*factor) : 255);
		newGreen = (newGreen >= 0 ? newGreen : 0);
		
		int newBlue = ((int)(color.getBlue()*factor) <= 255 ? (int)(color.getBlue()*factor) : 255);
		newBlue = (newBlue >= 0 ? newBlue : 0);
		
		return new Color(newRed, newGreen, newBlue);
	}

	//I just keep writing print() out of habit instead of System.out.println() 
	public static void print(String s) {
		System.out.println(s);
	}

	private Color enhance(Color color) {
		int max = Math.max(color.getRed(), color.getGreen());
		max = Math.max(max, color.getBlue());
		double factor = 255/(max*1.0);
		return scaleColor(color, factor);
	}
}
