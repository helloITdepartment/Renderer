package elements;

import primitives.*;

public class Camera {

	//Point representing where the camera is
	Point3D _p0;
	//Vectors describing where the camera is facing by saying which direction is it's up, forward, and right respectively
	Vector _vUp;
	Vector _vTo;
	Vector _vRight;
	//Size of the aperture
	double _aperture;
	
	//Constructors
	//Default Constructor
	public Camera() {
		_p0 = new Point3D();
		_vUp = new Vector(0.0, 1.0, 0.0);
		_vTo = new Vector(0.0, 0.0, -1.0);
		_vRight = new Vector(1.0, 0.0, 0.0);
		_aperture = 1.0;
		
	}
	
	//Parameterized Constructor
	public Camera(Point3D p0, Vector vUp, Vector vTo, Vector vRight) {
		_p0 = p0;
		_vUp = vUp;
		_vTo = vTo;
		_vRight = vRight;
	}
	
	//Parameterized Constructor
	public Camera(Point3D p0, Vector vUp, Vector vTo, Vector vRight, double aperture) {
		_p0 = p0;
		_vUp = vUp;
		_vTo = vTo;
		_vRight = vRight;
		_aperture = aperture;
	}

	//Copy Constructor
	public Camera(Camera other) {
		_p0 = other._p0;
		_vUp = other._vUp;
		_vTo = other._vTo;
		_vRight = other._vRight;
		_aperture = other.getAperture();
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
	
	//Method that will return the rays that would pass from a given pixel through the focal plane
	//pixelsInXDirection and pixelsInYDirection are the number of pixels in the horizontal and vertical directions respectively
	public Ray[] constructFocusRaysThroughPixel(int pixelsInXDirection, int pixelsInYDirection, double xCoordinate, double yCoordinate, double screenDistance, double screenWidth, double screenHeight, double focalPlaneDistance, double aperture) {

		Point3D centerPoint = _p0.add(_vTo.scale(screenDistance));
//		Point3D focalPoint = _p0.add(_vTo.scale(focalPlaneDistance));
		//Horizontal size per pixel
		double pixelWidth = (screenWidth/pixelsInXDirection);
		//Vertical size per pixel
		double pixelHeight = (screenHeight/pixelsInYDirection);
		
		//Calculate the point on the focal plane through this pixel
		Vector leftHandSide = _vRight.scale((( xCoordinate-(pixelsInXDirection/2.0) )*pixelWidth)  - (pixelWidth/2.0));
		//System.out.println(leftHandSide.toString());
		Vector rightHandSide =   _vUp.scale((( yCoordinate-(pixelsInYDirection/2.0) )*pixelHeight) - (pixelHeight/2.0));
		//System.out.println(rightHandSide.toString());
		//P = centerPixel + (leftHandSide - rightHandSide)	
		Vector v = new Vector(centerPoint.add(leftHandSide.subtract(rightHandSide)));
		//Vector that stretches from the camera to the focal plane
		Vector centerVector = v.scale(focalPlaneDistance/v.getHead().getZ().getCoordinate()*(-1));
		Point3D focalPoint = centerVector.getHead();

		//Calculate top left ray from the view screen to the focal plane
		leftHandSide = _vRight.scale((( xCoordinate-(pixelsInXDirection/2.0) )*pixelWidth)  - (pixelWidth/2.0)  - (aperture/2));
		//System.out.println(leftHandSide.toString());
		rightHandSide =   _vUp.scale((( yCoordinate-(pixelsInYDirection/2.0) )*pixelHeight) - (pixelHeight/2.0) + (aperture/2));
		//System.out.println(rightHandSide.toString());
		//P = centerPixel + (leftHandSide - rightHandSide)	
		v = new Vector(centerPoint.add(leftHandSide.subtract(rightHandSide)));
		Point3D topLeftPoint = v.getHead();
		Ray topLeftRay = new Ray(topLeftPoint, new Vector(focalPoint.subtract(topLeftPoint)));

		//Calculate top right ray from the view screen to the focal plane
		leftHandSide = _vRight.scale((( xCoordinate-(pixelsInXDirection/2.0) )*pixelWidth)  - (pixelWidth/2.0)  + (aperture/2));
		//System.out.println(leftHandSide.toString());
		rightHandSide =   _vUp.scale((( yCoordinate-(pixelsInYDirection/2.0) )*pixelHeight) - (pixelHeight/2.0) + (aperture/2));
		//System.out.println(rightHandSide.toString());
		//P = centerPixel + (leftHandSide - rightHandSide)	
		v = new Vector(centerPoint.add(leftHandSide.subtract(rightHandSide)));
		Point3D topRightPoint = v.getHead();
		Ray topRightRay = new Ray(topRightPoint, new Vector(focalPoint.subtract(topRightPoint)));

		//Calculate bottom left ray from the view screen to the focal plane
		leftHandSide = _vRight.scale((( xCoordinate-(pixelsInXDirection/2.0) )*pixelWidth)  - (pixelWidth/2.0)  - (aperture/2));
		//System.out.println(leftHandSide.toString());
		rightHandSide =   _vUp.scale((( yCoordinate-(pixelsInYDirection/2.0) )*pixelHeight) - (pixelHeight/2.0) - (aperture/2));
		//System.out.println(rightHandSide.toString());
		//P = centerPixel + (leftHandSide - rightHandSide)	
		v = new Vector(centerPoint.add(leftHandSide.subtract(rightHandSide)));
		Point3D bottomLeftPoint = v.getHead();
		Ray bottomLeftRay = new Ray(bottomLeftPoint, new Vector(focalPoint.subtract(bottomLeftPoint)));

		//Calculate bottom right ray from the view screen to the focal plane
		leftHandSide = _vRight.scale((( xCoordinate-(pixelsInXDirection/2.0) )*pixelWidth)  - (pixelWidth/2.0)  + (aperture/2));
		//System.out.println(leftHandSide.toString());
		rightHandSide =   _vUp.scale((( yCoordinate-(pixelsInYDirection/2.0) )*pixelHeight) - (pixelHeight/2.0) - (aperture/2));
		//System.out.println(rightHandSide.toString());
		//P = centerPixel + (leftHandSide - rightHandSide)	
		v = new Vector(centerPoint.add(leftHandSide.subtract(rightHandSide)));
		Point3D bottomRightPoint = v.getHead();
		Ray bottomRightRay = new Ray(bottomRightPoint, new Vector(focalPoint.subtract(bottomRightPoint)));

		Ray[] result = {topLeftRay, topRightRay, bottomLeftRay, bottomRightRay};
		return result;
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
	
	public double getAperture() {
		return _aperture;
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
	public void setAperture(double aperture) {
		if(aperture <= 0) {
			throw new IllegalArgumentException("Aperture must be positive");
		}
		_aperture = aperture;
	}
}
