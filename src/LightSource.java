import java.awt.*;
import java.awt.geom.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

public class LightSource implements Serializable {

    int angle ,x ,y , xLimit, yLimit;
    LightObject lObj;
    boolean isOrigin, inside;
    transient BufferedImage buffImage;
    transient Image img;
    Color a = Color.black;
    Color b = Color.yellow;
    int xVar;
    int yVar;

    public LightSource( int a, int b, int angle, LightObject gobj) {

        this.angle =angle;
        x = a;
        y = b;
        lObj = gobj;
        isOrigin = false;
        inside = false;
//		reloadImg();
        update(); //xLimit and yLimit are depend on x,y variables
//
    }

    public void setX(int a)
    {
        this.x=a;
        update();
    }
    public void setY(int a)
    {
        this.y=a;
        update();
    }

    public void update()
    {
        xLimit = (int)(x + (Math.cos(Math.toRadians(angle)) * 1600));
        yLimit = (int)(y - (Math.sin(Math.toRadians(angle)) * 1600));
    }

    public boolean isClicked(Point p)
    {
        if ( p.getX() < x + 30 && p.getX() > x + 30 && p.getY() > y + 30 && p.getY() < y - 30)
            return true;
        return false;
    }

    public void block(int x1, int y1)
    {
        xLimit = x1;
        yLimit = y1;
    }

    public void setAngle ( int x)
    {
        angle = x;
        update();
    }

    public void setColor( Color t)
    {
        b = t;
        a = t;
    }

    public void draw(Graphics g)
    {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setStroke(new BasicStroke(8));
        g2D.setColor(a);
        g2D.drawLine(x,y,xLimit,yLimit);
        g2D.setStroke(new BasicStroke(7));
        g2D.setColor(b);
        g2D.drawLine(x, y, xLimit, yLimit);
        g2D.setStroke(new BasicStroke(1));
        g2D.setColor(Color.black);
    }

    public Point intersect(LightObject lo)
    {
        if(lObj == lo)
            return null;
        Line2D.Double line = new Line2D.Double(x, y, xLimit, yLimit);
        if ( lo instanceof FlatMirror)
        {
            Point p1 = new Point();
            p1 = this.lineIntersect(lo);
            if ( p1 != null)
                block(p1.x+1, p1.y+1 );
            return p1;
        }
        if ( lo instanceof CurvedMirror)
        {
            CurvedMirror c = (CurvedMirror)lo;
            Arc2D.Double arch = c.getArch();
            List<Point> points = getCircleLineIntersectionPoint(new Point(x, y), new Point(xLimit, yLimit), c.getCenter(), 2 * c.getFocus());
            if(points.size() > 1) {
                Point p1 = points.get(0);
                Point p2 = points.get(1);
                if(arch.intersects(p1.x - 2, p1.y - 2, 4, 4) && (Math.abs(p1.x - x) > 4 || Math.abs(p1.y - y) > 4) && line.intersects(p1.x - 2, p1.y - 2, 4, 4))
                {
                    if ( p1 != null)
                        block(p1.x+1, p1.y+1 );
                    return p1;
                }

                if(arch.intersects(p2.x - 2, p2.y - 2, 4, 4) && (Math.abs(p2.x - x) > 4 || Math.abs(p2.y - y) > 4) && line.intersects(p2.x - 2, p2.y - 2, 4, 4))
                {
                    if ( p2 != null)
                        block(p2.x+1, p2.y+1 );
                    return p2;
                }
            }

        }
        if( lo instanceof Obstacle)
        {
            Obstacle obs = (Obstacle)lo;
//			ArrayList<Point> ints = new ArrayList();
            if(obs.getRectangle().intersectsLine(x, y, xLimit, yLimit)) {
//				return obstacleIntersection(obs.x, obs.y, obs.width, obs.height);
                block(obs.x+1, obs.y+1 );

                return new Point(50,100);
            }
        }
        if( lo instanceof Target)
        {
            Target t = (Target)lo;
            List<Point> points2 = getCircleLineIntersectionPoint(new Point(x, y), new Point(xLimit, yLimit), t.getCenter(), t.radius);
            if(points2.size() > 1)
            {
                Point p1 = points2.get(0);
                Point p2 = points2.get(1);
                if(Point2D.distance(p1.x, p1.y, x, y) < Point2D.distance(p2.x, p2.y, x, y) && line.intersects(p1.x - 2, p1.y - 2, 4, 4))
                    return p1;
                if(Point2D.distance(p1.x, p1.y, x, y) > Point2D.distance(p2.x, p2.y, x, y) && line.intersects(p2.x - 2, p2.y - 2, 4, 4))
                    return p2;
            }
        }
        return null;
    }

    public Point lineIntersect( LightObject lo) //from http://stackoverflow.com/questions/16314069/calculation-of-intersections-between-line-segments
    {
        Mirror mr = lo.getBlocked();
        int x1 = this.x;
        int y1 = this.y;
        int x2 = this.xLimit;
        int y2 = this.yLimit;
        int x3 = mr.xLimit1;
        int x4 = mr.xLimit2;
        int y3 = mr.yLimit1;
        int y4 = mr.yLimit2;

        double denom = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);

        if (denom == 0.0)
        {
            return null;
        }
        double ua = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3))/denom;
        double ub = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3))/denom;

        if (ua >= 0.0f && ua <= 1.0f && ub >= 0.0f && ub <= 1.0f)
        {
            return new Point((int) (x1 + ua*(x2 - x1)), (int) (y1 + ua*(y2 - y1)));
        }
        return null;
    }
    //taken from http://stackoverflow.com/questions/13053061/circle-line-intersection-points
    public static List<Point> getCircleLineIntersectionPoint(Point pointA,
                                                             Point pointB, Point center, double radius) {
        double baX = pointB.x - pointA.x;
        double baY = pointB.y - pointA.y;
        double caX = center.x - pointA.x;
        double caY = center.y - pointA.y;

        double a = baX * baX + baY * baY;
        double bBy2 = baX * caX + baY * caY;
        double c = caX * caX + caY * caY - radius * radius;

        double pBy2 = bBy2 / a;
        double q = c / a;

        double disc = pBy2 * pBy2 - q;
        if (disc < 0) {
            return Collections.emptyList();
        }
        // if disc == 0 ... dealt with later
        double tmpSqrt = Math.sqrt(disc);
        double abScalingFactor1 = -pBy2 + tmpSqrt;
        double abScalingFactor2 = -pBy2 - tmpSqrt;

        Point p1 = new Point((int)(pointA.x - baX * abScalingFactor1), (int)(pointA.y
                - baY * abScalingFactor1));
        if (disc == 0) { // abScalingFactor1 == abScalingFactor2
            return Collections.singletonList(p1);
        }
        Point p2 = new Point((int)(pointA.x - baX * abScalingFactor2), (int)(pointA.y
                - baY * abScalingFactor2));
        return Arrays.asList(p1, p2);
    }

    public int angleBetween2Lines(LightObject lo)
    {
        Mirror mr = lo.getBlocked();

        double angle1 = Math.atan2(this.y - this.yLimit,
                this.x - this.xLimit);
        double angle2 = Math.atan2(mr.yLimit1 - mr.yLimit2,
                mr.xLimit1 - mr.xLimit2);
        return (int)(angle1-angle2);
    }
    public Point obstacleIntersection ( int coX, int coY, int width, int height)
    {
        LightEquations light = new LightEquations(x,y,xLimit,yLimit) ;
        Point[] intersections = new Point[4] ;
        Point closestPoint ;
        if(light.calcY(coX)>=coY && light.calcY(coX) <= (coY+height))
        {
            intersections[0] = new Point(coX,light.calcY(coX)) ;
        }
        if(light.calcY(coX+width) >= coY && light.calcY(coX+width) <= (coY+height))
        {
            intersections[1] = new Point(coX+width,light.calcY(coX+width)) ;
        }
        if(light.calcX(coY) >= coX && light.calcX(coY) <= (coX+width))
        {
            intersections[2] = new Point(light.calcX(coY),coY) ;
        }
        if(light.calcX(coY+height) >= coX && light.calcX(coY+height) <= (coX+width))
        {
            intersections[3] = new Point( light.calcX(coY+height) , coY+height ) ;
        }
        for(int i = 1 ; i < 3 ; i++ )
        {
            if(intersections[0] != null)
            {
                Point temp1 = intersections [0] ;
                if(intersections[i] != null)
                {
                    Point temp2 = intersections[i] ;
                    double hypot1 = Math.hypot(Math.abs(temp1.getX()-x),Math.abs(temp1.getY()-y)) ;
                    double hypot2 = Math.hypot(Math.abs(temp2.getX()-x),Math.abs(temp2.getY()-y)) ;
                    if(hypot1>hypot2)
                    {
                        intersections[0] = intersections[i] ;
                        intersections[i] = null ;
                    }
                }
            }
            else
            {
                if(intersections[i] != null)
                {
                    intersections[0] = intersections[i] ;
                }
                intersections[i] = null ;
            }
        }

        return intersections[0] ;
    }





}
