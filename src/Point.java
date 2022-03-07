/**
 * A simple class representing a location in 2D space.
 */
public final class Point
{
    private double x;
    private double y;
    private double z;

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public double getZ()
    {
        return z;
    }

    public String toString() {return x + ", " + y + ", " + z;}
}
