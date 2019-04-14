package elements;

import primitives.*;

public class Camera {

	Point3D _p0;
	Vector _vUp;
	Vector _vTo;
	Vector _vRight;
	
	//Method that will return the ray that would pass from the camera through a given pixel
	public Ray constructRayThroughPixel(int nx, int ny, double x, double y, double screenDistance, double screenWidth, double screenHeight) {
		
		Point3D centerPixel = _p0.add(_vTo.scale(screenDistance));
		double ratioX = (screenWidth/nx);
		double ratioY = (screenHeight/ny);

		Vector leftHandSide = _vRight.scale(((x-(nx/2) )*ratioX) + (ratioX/2));
		Vector rightHandSide = _vRight.scale(((y-(ny/2))*ratioY) + (ratioY/2));
		
		//P = centerPixel + (leftHandSide - rightHandSide)	
		Ray ray = new Ray(centerPixel, leftHandSide.subtract(rightHandSide));
		
		return ray;
	}
}
