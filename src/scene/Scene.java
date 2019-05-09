package scene;

import primitives.*;
import geometries.*;
import elements.*;

import java.awt.Color;
import java.util.*;

//A class representing a little universe filled with light, 3d objects, and a camera through which to see it all
public class Scene {

	//The Scene's title
	String _sceneName;
	//The background color, what the camera sees if nothing is in front of it
	Color _backgroundColor;
	//The ambient light in the universe, for example a sun
	AmbientLight _ambientLight;
	//The list of all the objects in this universe
	List<Geometry> _geometryList;
	//The camera through which to see it all
	Camera _camera;
	//How far from the camera is the screen we're looking through
	double _screenDistance;

	//TODO- Write constructors, setters

	//Constructors
	//Default constructor
	public Scene() {
		//Gives the scene an empty name
		_sceneName = "";
		//Sets the background color to black
		_backgroundColor = new Color(0, 0, 0);
		//Sets the ambient light to a default AmbientLight (black color, intensity of 0)
		_ambientLight = new AmbientLight();
		//Initializes an empty list
		_geometryList = new ArrayList<Geometry>();
		//A default camera
		_camera = new Camera();
		//A default distance
		_screenDistance = 0.0;
	}

	//Parameterized constructors
	public Scene(String sceneName, Color backgroundColor, AmbientLight ambientLight, List<Geometry> geometryList, Camera camera, double screenDistance) {
		_sceneName = sceneName;
		_backgroundColor = backgroundColor;
		_ambientLight = ambientLight;
		_geometryList = geometryList;
		_camera = camera;
		_screenDistance = screenDistance;
	}

	//Since Java doesn't really do default parameters, this is how we can use a constructor without all the variables
	public Scene(String sceneName, Color backgroundColor, AmbientLight ambientLight, Camera camera, double screenDistance) {
		this(sceneName, backgroundColor, ambientLight, new ArrayList<Geometry>(), camera, screenDistance);
	}

	//Copy constructor
	public Scene(Scene other) {
		_sceneName = other._sceneName;
		_backgroundColor = other._backgroundColor;
		_ambientLight = other._ambientLight;
		_geometryList = other._geometryList;
		_camera = other._camera;
		_screenDistance = other._screenDistance;
	}


	//Getters 

	public String getSceneName() {
		//Returns new String with the same value as our _sceneName, so that changes made at the callsite wont affect our variables
		return new String(_sceneName);
	}

	public Color getBackgroundColor() {
		//Returns new Color with the same RGB values as our _backgroundColor, so that changes made at the callsite wont affect our variables
		return new Color(_backgroundColor.getRGB());
	}

	public AmbientLight getAmbientLight() {
		//Returns new AmbientLight with the same values as in our _ambientLight, so that changes made at the callsite wont affect our variables
		Color color = _ambientLight.getColor();
		double intensity = _ambientLight.getkA();

		return new AmbientLight(color, intensity);
	}

	public List<Geometry> getGeometryList() {
		//Returns new List with the same value as our _geometryList, so that changes made at the callsite wont affect our variables
		return new ArrayList(_geometryList);
	}

	public Camera getCamera() {
		//Returns new _screenDistance with the same value as our _camera, so that changes made at the callsite wont affect our variables
		return new Camera(_camera);
	}

	public double getScreenDistance() {
		return _screenDistance;
	}

	//Setters
	public void setSceneName(String sceneName) {
		//Allows for setting of protected data member _sceneName;
		_sceneName = sceneName;
	}

	public void setBackgroundColor(Color bgColor) {
		//Allows for setting of protected data member _backgroundColor
		_backgroundColor = bgColor;
	}

	public void setAmbientLight(AmbientLight ambientLight) {
		//Allows for setting of protected data member _ambientLight
		_ambientLight = ambientLight;
	}

	public void setGeometryList(List<Geometry> gList) {
		//Allows for setting of protected data member _geometryList
		_geometryList = gList;
	}

	public void setCamera(Camera camera) {
		//Allows for setting of protected data member _camera
		_camera = camera;
	}

	public void setScreenDistance(double screenDistance) {
		//Allows for setting of protected data member _screenDistance
		_screenDistance = screenDistance;
	}

	//Method to add an object to our list of things
	public void addGeometry(Geometry geometryToAdd) {
		_geometryList.add(geometryToAdd);
	}

	//Method returning an iterator so we can loop through all the objects in the scene,
	//Leverages List's built in iterator method
	public Iterator<Geometry> getGeometriesIterator(){
		return _geometryList.iterator();
	}
}