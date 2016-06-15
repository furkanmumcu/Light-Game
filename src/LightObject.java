import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.*;
import java.io.*;
/**
 * @(#)LightObject.java
 *
 * LightObject application
 *
 * @author furkan mumcu
 * @version 1.00 2014/4/29
 */

abstract class LightObject implements Serializable {

    protected int x, y, angle;
    protected boolean iconFlag = false;



    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getAngle(){
        return angle;
    }

    public void setAngle(int a){
        angle = a;
    }

    abstract boolean isClicked( Point p);

    abstract LightSource event(LightSource ls);

    abstract void draw(Graphics g);

    public void setIconFlag(boolean a)
    {
        iconFlag = a;
    }

    public abstract Mirror getBlocked();


    public double argument(double degree){
        while(degree>360)
        {
            degree = degree - 360 ;
        }
        while(degree < 0)
        {
            degree = degree + 360 ;
        }
        return degree;
    }

}



