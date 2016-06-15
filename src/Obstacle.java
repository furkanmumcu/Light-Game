import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.*;
import java.io.*;
//a resizable optic object which only obstructs the incoming ray
class Obstacle extends LightObject {
    Rectangle2D.Double engel;
    int width, height;

    public Obstacle(int x, int y) {
        this.x = x;
        this.y = y;
        width = 100;
        height = 100;
        recalculate();
    }

    public Rectangle2D.Double getRectangle() {
        return engel;
    }

    public void recalculate() {
        engel = new Rectangle2D.Double(x, y, width, height);
    }

    public void setX(int x) {
        this.x = x;
        recalculate();
    }

    public void setY(int y) {
        this.y = y;
        recalculate();
    }

    public void setWidth(int w) {
        width = w;
        recalculate();
    }

    public void setHeight(int h) {
        height = h;
        recalculate();
    }

    public boolean isClicked(Point e) {
        if(e.x > x && e.x < x + width && e.y > y && e.y < y + height)
            return true;
        return false;
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.fill(engel);
        g.setColor (Color.red);
    }


    ///// The methods below this line are the methods which we dont need to use for obstacle object
    public Mirror getBlocked(){
        return null;
    }

    public int getAngle(){
        return 0;
    }

    public void setAngle(){

    }

    public LightSource event(LightSource lightBeforeReflection)
    {
        return null;
    }

}