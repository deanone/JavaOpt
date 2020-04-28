import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.lang.Math; 

public class PSO 
{
	ArrayList<Particle> _particles;
	int _numParticles;
	
	int _n;	// dimension of particles	
	
	double _tol;	// tolerance for terminating algorithm
	
	int _maxNumOfIterations;
	
	double _lowerBound;
	double _upperBound;
	
	int _fType;
	
	double _omega;
	double _phiP;
	double _phiG;
	
	double[] _g;	// swarm's best known position
	
	boolean isOver;
	
	public PSO(int _numParticles, int _n,  double _tol, int _maxNumOfIterations, double _lowerBound, double _upperBound, int _fType, double _omega, double _phiP, double _phiG)
	{
		this._particles = new ArrayList<Particle>();
		this._numParticles = _numParticles;
		this._n = _n;
		this._tol = _tol;
		this._maxNumOfIterations = _maxNumOfIterations;
		
		this._lowerBound = _lowerBound;
		this._upperBound = _upperBound;
		
		this._fType = _fType;
		
		this._omega = _omega;
		this._phiP = _phiP;
		this._phiG = _phiG;
		
		_g = new double[_n];
		Random r = new Random();
        for (int i = 0; i < _g.length; i++)
        {
            _g[i] = _lowerBound + (r.nextDouble() * (_upperBound - _lowerBound));;
        }
		
		isOver = false;
	}
	
	public int getNumParticles()
	{
		return _numParticles;
	}
	
	public int getParticlesDimension()
	{
		return _n;
	}
	
	public double getTol()
	{
		return _tol;
	}
	
	public int getMaxNumOfIterations()
	{
		return _maxNumOfIterations;
	}
	
	public double getOmega()
	{
		return _omega;
	}
	
	public double getPhiP()
	{
		return _phiP;
	}
	
	public double getPhiG()
	{
		return _phiG;
	}
	
	public double[] getG()
	{
		return _g;
	}
	
	public boolean over()
	{
		return isOver;
	}
	
	public void printSolution()
	{
		System.out.print("Solution: g=[");
		System.out.print(_g[0]);
		for (int i = 1; i < _g.length; i++)
		{
			System.out.print("," + _g[i]);
		}
		System.out.println("]");
	}
	
	// Function to be optimized
	double f(double[] _x, int type)
	{
		double result = 0.0;
		if (type == 1)	// Sphere function
		{
			for (int i = 0; i < _x.length; i++)
			{
				result += Math.pow(_x[i], 2);
			}
			return result;
		}
		else if (type == 2)	// Rosenbrock function
		{
			for (int i = 0; i < (_x.length - 1); i++)
			{
				result += (100* Math.pow(_x[i+1] - Math.pow(_x[i], 2), 2) + Math.pow(1 - _x[i], 2));
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
		_particles.add(p);
	}
	
	public Particle getParticle(int i)
	{
		return _particles.get(i);
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
		for (int i = 0; i < _numParticles; i++)
		{
			Particle particle = _particles.get(i);
			particle.initialize();
			double[] _p = particle.p();
			double fp = f(_p, _fType);
			double fg = f(_g, _fType);			
			if (fp < fg)
			{
				_g = Arrays.copyOf(_p, _p.length);
			}
		}
		
		Random r = new Random();
		int iter = 0;
		while (iter < _maxNumOfIterations)
		{
			System.out.println("Iteration " + (iter + 1));
			for (int i = 0; i < _numParticles; i++)
			{
				Particle particle = _particles.get(i);
				
				double[] _v = particle.v();
				double[] _x = particle.x();
				double[] _p = particle.p();
								
				for (int d = 0; d < _n; d++)
				{
					double rp = r.nextDouble();
					double rg = r.nextDouble();
					_v[d] = _omega * _v[d] + _phiP * rp * (_p[d] - _x[d]) + _phiG * rg * (_g[d] - _x[d]);
					_x[d] = _x[d] - _v[d];
				}

				
				if (f(_x, _fType) < f(_p, _fType))
				{
					// Update the particle's best known position
					particle.p(_x);
					_p = particle.p();
					if (f(_p, _fType) < f(_g, _fType))
					{
						// Update the swarm's best known position
						_g = Arrays.copyOf(_p, _n);
					}
				}
			}
			iter++;
		}
	}
	
	public static void main(String[] args)
	{
		int _numParticles = 100000;
		int _n = 4;
		double _lowerBound = 0;
		double _upperBound = 1;
		int _maxNumOfIterations = 10000;
		int _fType = 2;
		double _omega = 0.5;
		double _phiP = 0.5;
		double _phiG = 0.5;
		double _tol = 1e-10;
		
		PSO pso = new PSO(_numParticles, _n, _tol, _maxNumOfIterations, _lowerBound, _upperBound, _fType, _omega, _phiP, _phiG);
		
		// construct initial particles
		for (int i = 0; i < _numParticles; i++)
		{
			pso.addParticle(new Particle(_n, _lowerBound, _upperBound));;
		}
		
		// Run PSO algorithm
		pso.run();
		
		// Print results
		pso.printSolution();
		System.out.println("Optimum objective function value for solution g: " + pso.f(pso.getG(), _fType));

	}
}
