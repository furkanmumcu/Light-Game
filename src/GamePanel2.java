import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel2 extends JPanel {
    ConcaveMirror fm;
    LightSource isik;
    LightSource newIsik;
    Target t1;
    Point p2 = new Point();
    int xL, yL;

    public GamePanel2()
    {
        fm = new ConcaveMirror (100,255, 30);
        fm.setAngle(-95);
        t1 = new Target(175,30);

        addMouseListener (new MirrorListener());
        addMouseMotionListener (new MirrorListener());

        newIsik = new LightSource(0,0,0,null);
        isik = new LightSource(0,0,-45,null);
        xL = isik.xLimit;
        yL = isik.yLimit;

        setBackground(Color.white);
        setPreferredSize(new Dimension(600, 600));

    }

    public void paintComponent (Graphics page)
    {
        super.paintComponent(page);
        t1.draw(page);
        isik.draw(page);
        fm.draw(page);


        if ( isik.intersect(t1)!=null)
            t1.event(isik);

        if ( isik.intersect(fm) != null)
        {

            Point p1 = isik.intersect(fm);
//			isik.block(p1.x+1,p1.y+1);
            System.out.println(newIsik.x + "   " + newIsik.y);
            System.out.println(newIsik.xLimit + "   " + newIsik.yLimit);
            System.out.println( "x= "+p1.x);
            System.out.println( "y= "+p1.y);

            if ( fm.event(isik)!=null)
            {

                newIsik = fm.event(isik);
                System.out.println("x1= "+ newIsik.x + "   y1= "+ newIsik.y + "   x2= "+ newIsik.xLimit + "   y2= "+ newIsik.yLimit + " angle:" + newIsik.angle);
                newIsik.draw(page);

            }

        }
        if(isik.intersect(fm) == null){

            isik.setAngle(-45);
            isik.xLimit = xL;
            isik.yLimit = yL;
        }
        if (newIsik.intersect(t1)!=null)
        {
            t1.event(newIsik);

        }
        repaint();

    }

    private class MirrorListener extends MouseAdapter //implements MouseListener,MouseMotionListener
    {
        public void mouseDragged(MouseEvent event)
        {
            p2 = event.getPoint();
            if(fm.isClicked(p2)&& SwingUtilities.isLeftMouseButton(event)){

                fm.setX((int)p2.getX());
                fm.setY((int)p2.getY());
            }
            if(fm.isClicked(p2)&& SwingUtilities.isRightMouseButton(event)){
                fm.setAngle((int)fm.argument(fm.angle)+5);
            }

            repaint();
        }


//		System.out.println("p2.x="+p2.x);
//		System.out.println("p2.y="+p2.y);
//		System.out.println("fm.x="+fm.x);
//		System.out.println("fm.y="+fm.y);


        public void mousePressed(MouseEvent event){
            if(fm.isClicked(p2)&& SwingUtilities.isRightMouseButton(event)){
                fm.setAngle((int)fm.argument(fm.angle)+5);
            }

            repaint();

        }


        // public void mousePressed(MouseEvent event){}
        public void mouseClicked(MouseEvent event){}
        public void mouseReleased(MouseEvent event){}
        public void mouseEntered(MouseEvent event){}
        public void mouseExited(MouseEvent event){}
        public void mouseMoved(MouseEvent event){}
    }


}
