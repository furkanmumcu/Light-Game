import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
public class FlatMirror extends LightObject{

    Mirror mirror;
    Mirror front;
    Mirror back;

    public FlatMirror(int x, int y)
    {
        this.x = x;
        this.y = y;
        angle  = 0;
        front = new Mirror(x, y, 0, 100);
        back = new Mirror(x, y, 0, 100);
        mirror = new Mirror(x, y, 0, 100);
    }

    public boolean isClicked(Point e) {
        if(e.getY() < y + 50 && e.getY() > y - 50 && e.getX() < x + 50 && e.getX() > x - 50) {
            return true;
        }
        return false;
    }


    public Mirror getBlocked() {
        return mirror;
    }

    public void setX(int x) {
        super.setX(x);
        mirror.setX(x);
    }

    public void setAngle(int angle) {
        this.angle = angle;
        mirror.setAngle(angle);



    }
    public int getAngle() {
        return this.angle;
    }

    public void setY(int y) {
        super.setY(y);
        mirror.setY(y);
    }

    public void draw(Graphics g)
    {

        Graphics2D g2D = (Graphics2D)g;
        if ( iconFlag )
        {
            front = new Mirror(x, y, angle, 75);
            back = new Mirror((int)(x + 4 * Math.sin(Math.toRadians(angle))), (int)(y + 4 * Math.cos(Math.toRadians(angle))), angle, 77);
        }
        else
        {
            front = new Mirror(x, y, angle, 100);
            back  = new Mirror((int)(x + 4 *  Math.sin(Math.toRadians(angle))), (int)(y + 4 * Math.cos(Math.toRadians(angle))), angle, 102);
        }
//		front = new Mirror(x, y, angle, a);
//		back  = new Mirror((int)(x + 4 * Math.sin(Math.toRadians(angle))), (int)(y + 4 * Math.cos(Math.toRadians(angle))), angle, a);

        front.update();
        back.update();
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(10));
        g2.setColor(Color.black);
        front.draw(g);
        g2.setStroke(new BasicStroke(8));
        g2.setColor(Color.green);
        back.draw(g);
        g2.setStroke(new BasicStroke(1));

    }


    public LightSource event(LightSource lightBeforeReflection)
    {
        int lightAngle      = lightBeforeReflection.angle;
        int reflectionAngle = lightAngle;
        int angleDifference = lightAngle - this.angle;

        if ( (angleDifference >= -360 && angleDifference <= -180) || (angleDifference >= 0 && angleDifference <= 180) )
        {
            reflectionAngle = 2*this.angle - lightAngle;
            LightSource ls = new LightSource( lightBeforeReflection.xLimit, lightBeforeReflection.yLimit, (int)argument(reflectionAngle),this );
            System.out.println("x1= "+ ls.x + "   y1= "+ ls.y + "   x2= "+ ls.xLimit + "   y2= "+ ls.yLimit + " angle:" + (int)argument(reflectionAngle));
            return ls;
        }
        else
        {
            return null;

        }


    }



}
