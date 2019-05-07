package elements;

import primitives.*;

public class Camera {

	Point3D _p0;
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
		_vRight = _vRight;
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
		
		System.out.println();
		
		Point3D centerPixel = _p0.add(_vTo.scale(screenDistance));
		//Horizontal size per pixel
		double ratioX = (screenWidth/pixelsInXDirection);
		//Vertical size per pixel
		double ratioY = (screenHeight/pixelsInYDirection);

		Vector leftHandSide = _vRight.scale((( xCoordinate-(pixelsInXDirection/2.0) )*ratioX) - (ratioX/2.0));
		System.out.println(leftHandSide.toString());
		
		Vector rightHandSide = _vUp.scale(((yCoordinate-(pixelsInYDirection/2.0))*ratioY) - (ratioY/2.0));
		System.out.println(rightHandSide.toString());
		
		
		//P = centerPixel + (leftHandSide - rightHandSide)	
		Ray ray = new Ray(_p0, new Vector(centerPixel.add(leftHandSide.subtract(rightHandSide))));
		
		return ray;
	}
}
