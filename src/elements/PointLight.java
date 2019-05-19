package elements;

import primitives.Point3D;

public class PointLight extends Light{

    //point in three dimensional space representing the position of the light
    Point3D _position;
    //What dis? plz to explain
    //the three factors for attenuation with a distance
    double _Kc;
    double _Kl;
    double _Kq;

    //default constructor
    public PointLight()
    {
        _position = new Point3D();
        _Kc = 0.0;
        _Kl= 0.0;
        _Kq = 0.0;
    }

    //constructor which receives the position of the vector and the factors of attenuation
    public PointLight(Point3D pos, double Kc, double Kl, double Kq)
    {
        _position = pos;
        _Kc = Kc;
        _Kl = Kl;
        _Kq = Kq;
    }

    //getters for the three measurements of attenuation Kc, Kl, Kq
    public double getKc()
    {
        return new Double(_Kc);
    }
    public double getKl()
    {
        return new Double(_Kl);
    }
    public double getKq()
    {
        return new Double(_Kq);
    }
    //getter for the position of the point light
    public Point3D getposition() {
        return _position;
    }

    //allows for the setting of the factors of attenuation
    public void set_Kc(double _Kc) {
        this._Kc = _Kc;
    }

    public void set_Kl(double _Kl) {
        this._Kl = _Kl;
    }

    public void set_Kq(double _Kq) {
        this._Kq = _Kq;
    }
    //allows for the setting of the position of the light
    public void set_position(Point3D _position) {
        this._position = _position;
    }
}