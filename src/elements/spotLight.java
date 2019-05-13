package elements;

import primitives.Vector;

public class spotLight extends pointLight{
    //direction of the spotlight lighting
    Vector _direction;

    //constructor which accepts a vector as a parameter and sets the direction of the spotlight accordingly
    public spotLight(Vector direction){
        _direction = direction;
    }

    //default constructor which creates a default vector as the direction of the spotlight
    public spotLight(){
        _direction = new Vector();
    }

    //getter for the direction of the spotlight
    public Vector getdirection() {
        return _direction;
    }

    //setter for the direction of the spotlight
    public void setdirection(Vector _direction) {
        this._direction = _direction;
    }

}
