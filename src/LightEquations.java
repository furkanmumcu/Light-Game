//class for calculation of line properties


public class LightEquations {

    double x,y,m; // horizontal , vertical and slope value
    double xCoor,yCoor,c; // x y coordinates and constant value

    double interX, interY, angle;

    public LightEquations(double a1, double a2, double b1, double b2)
    {
        xCoor = a2 - b2;
        yCoor = a1 - a2;
        c = a1 * b2 - a2 * b1;
        update();
    }

    public LightEquations(double aa, double bb, double cc)
    {
        xCoor = aa;
        yCoor = bb;
        c = cc;
        update();
    }

    public void update()
    {
        m = xCoor / yCoor * (-1);
        y = c / yCoor * (-1);
        x = c / xCoor * (-1);
    }

    public double getX()
    {
        return xCoor;
    }

    public double getY()
    {
        return yCoor;
    }

    public int calcX( int a) // calculate horizontal
    {
        double asd = ((-1*c)+(-yCoor*y))/xCoor ;
        return (int)asd;
    }

    public int calcY( int a) // calculate vertical
    {
        double dsa = ((-1*c)+(-xCoor*x))/yCoor ;
        return (int)dsa;
    }

    public double getCons()
    {
        return c;
    }

    public double getHorInt()
    {
        return x;
    }

    public double getVerInt()
    {
        return y;
    }

    public double determinant(LightEquations l1)
    {
        return ( this.getX()*l1.getY() ) - ( this.getY()*l1.getX()) ;
    }

    public double xIntersect(LightEquations l1)
    {
        return ( this.getY()*l1.getCons() ) - ( this.getCons()*l1.getY() );
    }

    public double yIntersect(LightEquations l1)
    {
        return ( this.getX()*l1.getCons() ) - ( this.getCons()*l1.getX() );
    }
}
