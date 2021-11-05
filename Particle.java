import java.util.Arrays;
import java.util.Random;
import java.lang.Math; 

public class Particle 
{
	int d;	// dimension of the particle
	double lowerBound;
	double upperBound;
	double[] x;
	double[] v;
	double[] p;

	public Particle(int d, double lowerBound, double upperBound)
	{
		this.d = d;
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.x = new double[d];
		this.v = new double[d];
		this.p = new double[d];
	}
	
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
	
	public void d(int d_)
	{
		this.d = d_;
	}
	
	public int d()
	{
		return d;
	}
	
	public void x(double[] x_)
	{
		this.x = Arrays.copyOf(x_, x_.length);
	}
	
	public double[] x()
	{
		return x;
	}
	
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
	
	public void v(double[] v_)
	{
		this.v = Arrays.copyOf(v_, v_.length);
	}
	
	public double[] v()
	{
		return v;
	}
	
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
	
	public void p(double[] p_)
	{
		this.p = Arrays.copyOf(p_, p_.length);
	}
	
	public double[] p()
	{
		return p;
	}
	
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
