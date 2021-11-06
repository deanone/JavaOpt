import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.lang.Math; 

public class PSO 
{
	ArrayList<Particle> particles;
	int numParticles;
	int d;	// dimension of particles	
	double tol;	// tolerance for terminating the PSO algorithm
	int maxNumOfIterations;	// maximum number of iterations for the PSO algorithm to terminate
	double lowerBound;
	double upperBound;
	int fType;
	double omega;
	double phiP;
	double phiG;
	double[] g;	// swarm's best known position
	boolean isOver;
	
	/**
	 * Constructor.
	 * @param numParticles the number of particles
	 * @param d the dimension of the particles
	 * @param tol the tolerance for terminating the algorithm
	 * @param maxNumOfIterations the maximum number of iterations for the PSO algorithm to terminate
	 * @param lowerBound
	 * @param upperBound
	 * @param fType the function to optimize
	 * @param omege
	 * @param phiP
	 * @param phiG
	 */
	public PSO(int numParticles, int d,  double tol, int maxNumOfIterations, double lowerBound, 
		double upperBound, int fType, double omega, double phiP, double phiG)
	{

		this.particles = new ArrayList<Particle>();
		this.numParticles = numParticles;
		this.d = d;
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;

		// construct initial particles
		for (int i = 0; i < this.numParticles; ++i)
		{
			addParticle(new Particle(this.d, this.lowerBound, this.upperBound));
		}

		this.tol = tol;
		this.maxNumOfIterations = maxNumOfIterations;
		this.fType = fType;
		this.omega = omega;
		this.phiP = phiP;
		this.phiG = phiG;
		
		g = new double[d];

		Random r = new Random();
        for (int i = 0; i < g.length; ++i)
        {
            g[i] = lowerBound + (r.nextDouble() * (upperBound - lowerBound));
        }
		
		isOver = false;
	}
	
	/**
	 * Returns the number of particles.
	 * @return the number of particles.
	 */
	public int getNumParticles()
	{
		return numParticles;
	}
	
	public int getParticlesDimension()
	{
		return d;
	}
	
	public double getTol()
	{
		return tol;
	}
	
	public int getMaxNumOfIterations()
	{
		return maxNumOfIterations;
	}
	
	public double getOmega()
	{
		return omega;
	}
	
	public double getPhiP()
	{
		return phiP;
	}
	
	public double getPhiG()
	{
		return phiG;
	}
	
	public double[] getG()
	{
		return g;
	}
	
	public boolean over()
	{
		return isOver;
	}
	
	public void printSolution()
	{
		System.out.print("Solution: g=[");
		System.out.print(g[0]);
		for (int i = 1; i < g.length; ++i)
		{
			System.out.print("," + g[i]);
		}
		System.out.println("]");
	}
	
	// Function to be optimized
	double f(double[] x, int type)
	{
		double result = 0.0;
		if (type == 1)	// Sphere function
		{
			for (int i = 0; i < x.length; ++i)
			{
				result += Math.pow(x[i], 2);
			}
			return result;
		}
		else if (type == 2)	// Rosenbrock function
		{
			for (int i = 0; i < (x.length - 1); ++i)
			{
				result += (100 * Math.pow(x[i + 1] - Math.pow(x[i], 2), 2) + Math.pow(1 - x[i], 2));
			}
			return result;
		}
		else	// TODO: Add more optimization test functions
		{
			return -1.0;
		}
	}
	
	public void addParticle(Particle p)
	{
		particles.add(p);
	}
	
	public Particle getParticle(int i)
	{
		return particles.get(i);
	}
	
//	private double distance(double[] x, double[] y)
//	{
//		double dist = 0.0;
//		for (int i = 0; i < x.length; i++)
//		{
//			dist += Math.pow(x[i] - y[i], 2);
//		}
//		dist = Math.sqrt(dist);
//		return dist;
//	}
	
	public void run()
	{
		for (int i = 0; i < numParticles; ++i)
		{
			Particle particle = particles.get(i);
			particle.initialize();
			double[] p = particle.p();
			double fp = f(p, fType);
			double fg = f(g, fType);			
			if (fp < fg)
			{
				g = Arrays.copyOf(p, p.length);
			}
		}
		
		Random r = new Random();
		int iter = 0;
		while (iter < maxNumOfIterations)
		{
			System.out.println("Iteration " + (iter + 1));
			for (int i = 0; i < numParticles; ++i)
			{
				Particle particle = particles.get(i);
				
				double[] v = particle.v();
				double[] x = particle.x();
				double[] p = particle.p();
								
				for (int j = 0; j < d; ++j)
				{
					double rp = r.nextDouble();
					double rg = r.nextDouble();
					v[j] = omega * v[j] + phiP * rp * (p[j] - x[j]) + phiG * rg * (g[j] - x[j]);
					x[j] = x[j] - v[j];
				}

				
				if (f(x, fType) < f(p, fType))
				{
					// Update the particle's best known position
					particle.p(x);
					p = particle.p();
					if (f(p, fType) < f(g, fType))
					{
						// Update the swarm's best known position
						g = Arrays.copyOf(p, d);
					}
				}
			}
			iter++;
		}
	}
	
	public static void main(String[] args)
	{
		if (args.length > 0)
		{
			int numParticles = Integer.parseInt(args[0]);	// e.g., 1000 or 10000
			int d = Integer.parseInt(args[1]);	// e.g., 4
			double lowerBound = Integer.parseInt(args[2]);	// e.g., 0
			double upperBound = Integer.parseInt(args[3]);	// e.g., 1
			int maxNumOfIterations = Integer.parseInt(args[4]);	// e.g., 1000
			int fType = Integer.parseInt(args[5]);	// e.g., 2
			double omega = Double.parseDouble(args[6]);	// e.g., 0.5
			double phiP = Double.parseDouble(args[7]);	// e.g., 0.5
			double phiG = Double.parseDouble(args[8]);	// e.g., 0.5
			double tol = Double.parseDouble(args[9]);	// e.g., 1e-10

			PSO pso = new PSO(numParticles, d, tol, maxNumOfIterations, lowerBound, upperBound, fType, omega, phiP, phiG);
			
			// Run the PSO algorithm
			pso.run();
			
			// Print results
			pso.printSolution();
			System.out.println("Optimum objective function value for solution g: " + pso.f(pso.getG(), fType));
			System.exit(0);
		}
		else
		{
			System.out.println("The current implementation of the PSO algorithm requires 10 command line arguments.");
			System.exit(0);
		}
	}
}
