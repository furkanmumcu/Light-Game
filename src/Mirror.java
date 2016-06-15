import javax.swing.*;
import java.awt.*;
import java.io.*;
//a helper class for lenses and flat mirror
class Mirror implements Serializable{
    int x, y, xLimit1, yLimit1, xLimit2, yLimit2;
    int angle;
    int length;

    public Mirror(int x, int y, int angle, int length) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.length = length;
        update();
    }

    public void setAngle(int angle) {
        this.angle = angle;
        System.out.println("angle"+angle);
        update();
    }

    public void setX(int x) {
        this.x = x;
        update();
    }

    public void setY(int y) {
        this.y = y;
        update();
    }
    public int getXLimit1()
    {
        return this.xLimit1;
    }
    public int getXLimit2()
    {
        return this.xLimit2;
    }

    public int getYLimit1()
    {
        return this.yLimit1;
    }
    public int getYLimit2()
    {
        return this.yLimit2;
    }

    public void update() {
        yLimit1 = (int)(y - (Math.sin(Math.toRadians(angle)) * length/2));
        yLimit2 = (int)(y + (Math.sin(Math.toRadians(angle)) * length/2));
        xLimit1 = (int)(x + (Math.cos(Math.toRadians(angle)) * length/2));
        xLimit2 = (int)(x - (Math.cos(Math.toRadians(angle)) * length/2));

    }

    public void draw(Graphics g) {
        g.drawLine(xLimit1, yLimit1, xLimit2, yLimit2);
    }
}
