package elements;

import primitives.*;

public class Camera {

	//Point representing where the camera is
	Point3D _p0;
	//Vectors describing where the camera is facing by saying which direction is it's up, forward, and right respectively
	Vector _vUp;
	Vector _vTo;
	Vector _vRight;
	
	//Constructors
	//Default Constructor
	public Camera() {
		_p0 = new Point3D();
		_vUp = new Vector(0.0, 1.0, 0.0);
		_vTo = new Vector(0.0, 0.0, -1.0);
		_vRight = new Vector(1.0, 0.0, 0.0);
	}
	
	//Parameterized Constructor
	public Camera(Point3D p0, Vector vUp, Vector vTo, Vector vRight) {
		_p0 = p0;
		_vUp = vUp;
		_vTo = vTo;
		_vRight = vRight;
	}
	
	//Copy Constructor
	public Camera(Camera other) {
		_p0 = other._p0;
		_vUp = other._vUp;
		_vTo = other._vTo;
		_vRight = other._vRight;
	}
	
	//Method that will return the ray that would pass from the camera through a given pixel
	//pixelsInXDirection and pixelsInYDirection are the number of pixels in the horizontal and vertical directions respectively
	public Ray constructRayThroughPixel(int pixelsInXDirection, int pixelsInYDirection, double xCoordinate, double yCoordinate, double screenDistance, double screenWidth, double screenHeight) {
				
		Point3D centerPixel = _p0.add(_vTo.scale(screenDistance));
		//Horizontal size per pixel
		double pixelWidth = (screenWidth/pixelsInXDirection);
		//Vertical size per pixel
		double pixelHeight = (screenHeight/pixelsInYDirection);

		Vector leftHandSide = _vRight.scale((( xCoordinate-(pixelsInXDirection/2.0) )*pixelWidth) - (pixelWidth/2.0));
		//System.out.println(leftHandSide.toString());
		
		Vector rightHandSide =   _vUp.scale((( yCoordinate-(pixelsInYDirection/2.0) )*pixelHeight) - (pixelHeight/2.0));
		//System.out.println(rightHandSide.toString());
		
		
		//P = centerPixel + (leftHandSide - rightHandSide)	
		Ray ray = new Ray(_p0, new Vector(centerPixel.add(leftHandSide.subtract(rightHandSide))));
		
		return ray;
	}
	
	//Getters
	public Point3D getP0() {
		//Returns new Point3D with the same value as our _p0, so that changes made at the callsite wont affect our variables
		return new Point3D(_p0);
	}
	
	public Vector getvUp() {
		//Returns new Vector with the same value as our _vUp, so that changes made at the callsite wont affect our variables
		return new Vector(_vUp);
	}
	
	public Vector getvTo() {
		//Returns new Vector with the same value as our _vTo, so that changes made at the callsite wont affect our variables
		return new Vector(_vTo);
	}
	
	public Vector getvRight() {
		//Returns new Vector with the same value as our _vRight, so that changes made at the callsite wont affect our variables
		return new Vector(_vRight);
	}
	
	//Setters
	public void setP0(Point3D p0) {
		//Allows for setting of protected data member 
		_p0 = p0;
	}
	public void setVup(Vector vUp) {
		//Allows for setting of protected data member _vUp
		_vUp = vUp;
	}
	public void setVto(Vector vTo) {
		//Allows for setting of protected data member _vTo
		_vTo = vTo;
	}
	public void setVright(Vector vRight) {
		//Allows for setting of protected data member _vRight
		_vRight = vRight;
	}
}
