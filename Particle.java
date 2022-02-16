import java.util.Arrays;
import java.util.Random;
import java.lang.Math; 

public class Particle 
{
    /**
     * The dimension of the particle.
     */
    int d;

    /**
     * The lower bound of the interval from which the random values of the Particle are generated.
     */
    double lowerBound;
    
    /**
     * The upper bound of the interval from which the random values of the Particle are generated.
     */
    double upperBound;
    
    /**
     * The position of the particle.
     */
    double[] x;
    
    /**
     * The velocity of the particle.
     */
    double[] v;
    
    /**
     * The best position of the particle.
     */
    double[] p;

    /**
     * Constructor.
     * @param d the dimension of the particle.
     * @param lowerBound the lower bound of the interval from which the random values of the Particle are generated.
     * @param upperBound the upper bound of the interval from which the random values of the Particle are generated.
     */
    public Particle(int d, double lowerBound, double upperBound)
    {
        this.d = d;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.x = new double[d];
        this.v = new double[d];
        this.p = new double[d];
    }
    
    /**
     * Initializes the particle.
     */
    public void initialize()
    {
        Random r = new Random();
        
        // initialize x
        for (int i = 0; i < x.length; ++i)
        {
            x[i] = lowerBound + (r.nextDouble() * (upperBound - lowerBound));
        }
        
        // initialize p
        this.p = Arrays.copyOf(x, x.length);
        
        // initialize v
        double vUpperBound = Math.abs(upperBound - lowerBound);
        double vLowerBound = (-1.0) * vUpperBound;
        for (int i = 0; i < v.length; ++i)
        {
            v[i] = vLowerBound + (r.nextDouble() * (vUpperBound - vLowerBound));
        }
    }
    
    /**
     * Sets the dimension of the particle.
     * @param d the dimension of the particle.
     */
    public void d(int d)
    {
        this.d = d;
    }
    
    /**
     * Returns the dimension of the particle.
     * @return the dimension of the particle.
     */
    public int d()
    {
        return d;
    }
    
    /**
     * Sets the position of the particle.
     * @param x the position of the particle.
     */
    public void x(double[] x)
    {
        this.x = Arrays.copyOf(x, x.length);
    }
    
    /**
     * Returns the position of the particle.
     * @return the position of the particle.
     */
    public double[] x()
    {
        return x;
    }
    
    /**
     * Returns a specifc value from the position vector of the particle.
     * @param i the index of the value to return.
     * @return the specific value from the position vector of the particle.
     */
    public double x(int i)
    {
        if (i >= 0 && i < d)
        {
            return x[i];
        }
        else
        {
            return -1.0;    // TODO: Better handle the case where index is out of array's bounds
        }
    }
    
    /**
     * Sets the velocity of the particle.
     * @param v the velocity of the particle.
     */
    public void v(double[] v)
    {
        this.v = Arrays.copyOf(v, v.length);
    }
    
    /**
     * Returns the velocity of the particle.
     * @return the velocity of the particle.
     */
    public double[] v()
    {
        return v;
    }
    
    /**
     * Returns a specifc value from the velocity vector of the particle.
     * @param i the index of the value to return.
     * @return the specific value from the velocity vector of the particle.
     */
    public double v(int i)
    {
        if (i >= 0 && i < d)
        {
            return v[i];
        }
        else
        {
            return -1.0;    // TODO: Better handle the case where index is out of array's bounds
        }
    }
    
    /**
     * Sets the best position of the particle.
     * @param p the best position of the particle.
     */
    public void p(double[] p)
    {
        this.p = Arrays.copyOf(p, p.length);
    }
    
    /**
     * Returns the best position of the particle.
     * @return the best position of the particle.
     */
    public double[] p()
    {
        return p;
    }
    
    /**
     * Returns a specifc value from the best position vector of the particle.
     * @param i the index of the value to return.
     * @return the specific value from the best position vector of the particle.
     */
    public double p(int i)
    {
        if (i >= 0 && i < d)
        {
            return p[i];
        }
        else
        {
            return -1.0;    // TODO: Better handle the case where index is out of array's bounds
        }
    }
}
