import java.awt.*;
import javax.swing.*;
import java.util.*;


//Target class which is the goal of the game
public class Target extends LightObject {

    int radius;
    boolean hit;

    public Target(int x, int y) {
        this.x = x;
        this.y = y;
        radius = 30;
        hit = false;
    }

    public boolean isClicked(Point p) {
        if( (int)Math.hypot( p.x - x, p.y - y ) <= radius ) {
            return true;
        }
        return false;
    }

    public void draw(Graphics g) {
        if(hit)
            g.setColor(Color.yellow);
        else
            g.setColor(Color.gray);
        g.fillOval(x - radius, y - radius, 2*radius, 2*radius);
        g.setColor(Color.black);
    }

    public Point getCenter() {
        return new Point(x, y);
    }

    public Mirror getBlocked() {
        return null;
    }

    public void setHit(boolean b) {
        hit = b;
    }

    public LightSource event(LightSource ls) {
        setHit(true);
        return null;
    }
}
