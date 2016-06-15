import java.awt.geom.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

public class ConvexMirror extends CurvedMirror {

    public ConvexMirror( int a, int b, int fokus)
    {
        super(a , b, fokus, true);
    }
    public LightSource event(LightSource lightBeforeEvent) {

        double focus =  this.focus;
        double dC=10000, dG=10000;
        double dCx=0, dCy=0, dGx=0, dGy=0;
        double a=0, b=0;
        double xLimit = lightBeforeEvent.xLimit;
        double yLimit = lightBeforeEvent.yLimit;
        double lightAngle = lightBeforeEvent.angle;
        double newAngle = lightAngle;
        double radius = focus*2;
        double Ox, Oy;
        double centerLight=60;

        double angleBetween = (lightAngle - angle);
        double sourceIntercept = yLimit +Math.tan(Math.toRadians(lightAngle))*xLimit;
        double centerIntercept = y+Math.tan(Math.toRadians(lightAngle))*x;
        double verticalDistance = Math.hypot(xLimit-x, yLimit-y);

        Ox = x + radius*Math.cos(Math.toRadians(angle));
        Oy = y - radius*Math.sin(Math.toRadians(angle));
        centerLight = -Math.toDegrees(atan((yLimit-Oy),(xLimit-Ox)));

        if ( (angleBetween >= 90 && angleBetween <= 270) || (angleBetween >= -270 && angleBetween <= -90) ) {
            return null;
        }
        newAngle = 180+ (2*centerLight - lightAngle);

        return new LightSource((int)xLimit,(int)yLimit,(int)argument(newAngle),this);
    }
    public double atan(double rise, double run, double dG){
        double result = Math.atan(rise/run);
        if(rise>=0 && run >=0){
            return result;
        }
        else if(rise<=0 && run >=0){
            result += 2*Math.PI;
        }
        else if(rise>=0 && run <=0){
            result += Math.PI;
        }
        else if(rise<=0 && run <=0){
            result += Math.PI;
        }
        if (dG<0){
            result = result+Math.PI;
        }
        return result;
    }

    public double atan(double rise, double run){
        double result = Math.atan(rise/run);
        if(rise>=0 && run >=0){
            return result;
        }
        else if(rise<=0 && run >=0){
            result += 2*Math.PI;
        }
        else if(rise>=0 && run <=0){
            result += Math.PI;
        }
        else if(rise<=0 && run <=0){
            result += Math.PI;
        }
        return result;
    }
}
