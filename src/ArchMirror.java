import java.awt.geom.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.io.*;
//to express curved mirrors with geometric equations
public class ArchMirror implements Serializable {

    int x,y,focus,angle;
    boolean isConvex;
    Arc2D.Double arch;

    public ArchMirror(int a, int b, int fokus, boolean isKonvex)
    {
        this.x=a;
        this.y=b;
        this.focus=fokus;
        this.isConvex=isKonvex;
        if(isConvex)
            angle=0;
        else
            angle=180;
        arch=new Arc2D.Double((x - 2 * focus)+2*focus*Math.cos(Math.toRadians(angle)), (y-2*focus)-2*focus*Math.sin(Math.toRadians(angle)), focus*4, focus*4, 120+angle, 120, Arc2D.OPEN);

    }

    public void setAngle(int x)
    {
        if (isConvex)
        {
            this.angle = x;
            update();
        }
        else
        {
            this.angle = x+180;
            update();
        }
    }

    public void setX(int x)
    {
        this.x = x;
        update();
    }

    public void setY(int x)
    {
        this.y = y;
        update();
    }

    public void setFocus(int x)
    {
        focus = x;
        update();
    }

    public void update()
    {
        arch.setArc((x-2*focus)+2*focus*Math.cos(Math.toRadians(angle)), (y-2*focus)-2*focus*Math.sin(Math.toRadians(angle)), focus*4, focus*4, 120+angle, 120, Arc2D.OPEN);
    }

    public Point getCenter()
    {
        return new Point((int)arch.getX()+2*focus, (int)arch.getY()+2*focus );
    }
    public Arc2D.Double getArch() {
        return this.arch;
    }

    public void draw(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));
        g2.draw(arch);
        g2.setColor(Color.lightGray);
        if (isConvex)
            g2.drawArc((int)arch.getX()+3, (int)arch.getY()+3, focus*4-6, focus*4-6, 120+angle, 120);
        else
            g2.drawArc((int)arch.getX()-3, (int)arch.getY()-3, focus*4+6, focus*4+6, 120+angle, 120);
        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(1));
        System.out.println(isConvex);
    }
    public static void main(String[] args) {
        final ArchMirror a = new ArchMirror(400, 300, 50, false);
        final JFrame f = new JFrame("Test") {
            public void paint(Graphics g) {
                super.paint(g);
                a.draw(g);
            }
        };

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(new Dimension(800, 600));
        f.setVisible(true);
    }

}
