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
		_kD = 0.8;
		_kS = 0.99;
		_nShininess = 50;
	}
	
	public Material(double kd, double ks, int shininess) {
		_kD = kd;
		_kS = ks;
		_nShininess = shininess;
	}
	
	public Material(Material other) {
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
		_kD = kD;
	}
	
	public void setKs(double kS) {
		_kS = kS;
	}

	public void setShininess(int shininess) {
		_nShininess = shininess;
	}
}
