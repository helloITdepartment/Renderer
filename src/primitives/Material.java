package primitives;

public class Material {

	//Diffusion coefficient (Kd)
	double _kD;
	//Specular coefficient (Ks)
	double _kS;
	//How shiny the object is (n)
	int _nShininess;
	//Coefficient of Reflectivity (how much like a mirror is it. 1 is a perfect mirror, 0 is a matte surface)
	double _kR;
	//Coefficient of Transparency (How transparent the material is. 1 is completely see through and 0 is completely opaque)
	double _kT;
	
	//Constructors
	//Default constructor
	public Material() {
		//Sets members to arbitrary values that seem to work
//		_kD = 0.8;
//		_kS = 0.99;
//		_nShininess = 5;
//		_kR = 0.5;
//		_kT = 0.5;
		this(0.8, 0.99, 5, 0.5, 0.5);
	}
	
	//Parameterized constructor
	public Material(double kd, double ks, int shininess) {
		//Takes in values for _kD, _kS, and _nShininess and sets them accordingly
//		_kD = kd;
//		_kS = ks;
//		_nShininess = shininess;
		this(kd, ks, shininess, 0.5, 0.5);
	}
	
	//Parameterized constructor
	public Material(double kd, double ks, int shininess, double kr, double kt) {
		//Takes in values for _kD, _kS, and _nShininess and sets them accordingly
		_kD = kd;
		_kS = ks;
		_nShininess = shininess;
		_kR = kr;
		_kT = kt;
	}

	//Copy constructor
	public Material(Material other) {
		//Sets each data member to a copy of other's
		_kD = other.getKd();
		_kS = other.getKs();
		_nShininess = other.getShininess();
		_kR = other.getKr();
		_kT = other.getKt();
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
	
	public double getKr() {
		return _kR;
	}
	
	public double getKt() {
		return _kT;
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
	
	public void setKr(double kR) {
		//Allows for setting of protected data member _kS
		_kR = kR;
	}
	
	public void setKt(double kT) {
		//Allows for setting of protected data member _kS
		_kT = kT;
	}
}
