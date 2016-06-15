import java.awt.*;

public class Circle
{
    int radius;
    int x;
    int y;
    boolean selected;

    public Circle( int a, int b, int c)
    {
        radius = a;
        x =b;
        y =c;
        selected = false;
    }
    public void setRadius( int r)
    {
        radius = r;
    }

    public double getArea()
    {
        return Math.PI * radius * radius;
    }

    public String toString()
    {
        return "Type: Circle\n" +"Radius: " + radius +"\nCoordinates:(" + x +","+ y +")" +"\nSelected:"+ selected;
    }
    public void setLocation(double a,double b)
    {
        x = (int)a;
        y = (int)b;
    }
    public boolean getSelected()
    {
        return selected;
    }
    public void setSelected( boolean an)
    {
        selected = an;
    }
    //	public Shape contains( int x, int y)
//	{
//		if ( this.x == x && this.y == y )
//		{
//			return this;
//		}
//		return null;
//	}
    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void draw( Graphics g) //draw circle
    {
        x= x-radius/2;
        y= y-radius/2;
        g.fillOval(x,y,radius,radius);

    }
    public boolean contains( int a, int b) //return true or false according to contain
    {
        if( Math.pow((y - b), 2) + Math.pow((x - a), 2) > Math.pow(radius, 2))
        {
            return false;
        }
        else
            return true;

    }
}