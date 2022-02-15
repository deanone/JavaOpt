import java.util.Arrays;
import java.util.Random;
import java.lang.Math; 

public class Particle 
{
	/**
	 * The dimension of the particle.
	 */
	int d;
	double lowerBound;
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
	 * @param lowerBound
	 * @param upperBound
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
	 * @param d_ the dimension of the particle.
	 */
	public void d(int d_)
	{
		this.d = d_;
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
	 * @param x_ the position of the particle.
	 */
	public void x(double[] x_)
	{
		this.x = Arrays.copyOf(x_, x_.length);
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
	 * Returns the a specifc value from the position vector of the particle.
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
			return -1.0;
		}
	}
	
	/**
	 * Sets the velocity of the particle.
	 * @param v_ the velocity of the particle.
	 */
	public void v(double[] v_)
	{
		this.v = Arrays.copyOf(v_, v_.length);
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
	 * Returns the a specifc value from the velocity vector of the particle.
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
			return -1.0;
		}
	}
	
	/**
	 * Sets the best position of the particle.
	 * @param p_ the best position of the particle.
	 */
	public void p(double[] p_)
	{
		this.p = Arrays.copyOf(p_, p_.length);
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
	 * Returns the a specifc value from the best position vector of the particle.
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
			return -1.0;
		}
	}
}
