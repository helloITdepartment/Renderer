package primitives;

public class Material {

	//Diffusion coefficient (Kd)
	double _kD;
	//Specular coefficient (Ks)
	double _kS;
	//How shiny the object is (n)
	int _nShininess;
	
	
	//Constructors
	//Default constructor
	public Material() {
		//Sets _kD, _kS, and _nShininess to arbitrary values that seem to work
		_kD = 0.8;
		_kS = 0.99;
		_nShininess = 5;
	}
	
	//Parameterized constructor
	public Material(double kd, double ks, int shininess) {
		//Takes in values for _kD, _kS, and _nShininess and sets them accordingly
		_kD = kd;
		_kS = ks;
		_nShininess = shininess;
	}
	
	//Copy constructor
	public Material(Material other) {
		//Sets each data member to a copy of other's
		_kD = other.getKd();
		_kS = other.getKs();
		_nShininess = other.getShininess();
	}
	
	//Getters
	public double getKd() {
		return _kD;
	}
	
	public double getKs() {
		return _kS;
	}
	
	public int getShininess() {
		return _nShininess;
	}
	
	//Setters
	public void setKd(double kD) {
		//Allows for setting of protected data member _kD
		_kD = kD;
	}
	
	public void setKs(double kS) {
		//Allows for setting of protected data member _kS
		_kS = kS;
	}

	public void setShininess(int shininess) {
		//Allows for setting of protected data member _nShininess
		_nShininess = shininess;
	}
}
