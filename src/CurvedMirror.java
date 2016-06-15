import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
//abs parent of concave convex lenses
abstract class CurvedMirror extends LightObject {

    int focus;
    ArchMirror arch;
    boolean isConvex;

    public CurvedMirror(int a, int b, int f, boolean isKonvex)
    {
        this.x = a;
        this.y = b;
        this.focus = f;
        this.isConvex = isKonvex;
        this.angle = 0;
        arch = new ArchMirror(x, y, focus, isConvex);
    }

    public boolean isClicked(Point e) {
        if(e.getY() < y + 50 && e.getY() > y - 50 && e.getX() < x + 50 && e.getX() > x - 50) {
            return true;
        }
        return false;
    }
    public void setAngle(int x)
    {
        this.angle = x;
        arch.setAngle(x);
    }

    public void setX(int x)
    {
        this.x = x;
        arch.setX(x);
    }

    public void setY(int x)
    {
        this.y = x;
        arch.setX(y);
    }

    public void draw(Graphics g)
    {
//		Drawer dr1;
        ArchMirror aMr;

        aMr = new ArchMirror(x, y, focus, isConvex);
        aMr.setAngle(this.angle);
//		dr1 = new Drawer(x, y, angle, focus);

//		dr1.draw(g);
        aMr.draw(g);

    }

    public LightSource event(LightSource lightBeforeEvent) {
        return null;
    }

    public Mirror getBlocked() {
        return null;
    }

    public Arc2D.Double getArch() {
        return arch.getArch();
    }

    public Point getCenter() {
        return arch.getCenter();
    }

    public int getFocus() {
        return focus;
    }

    public boolean contains(Point p) {
        return getArch().contains(p);
    }
}
