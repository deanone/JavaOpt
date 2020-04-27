import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.lang.Math; 

public class PSO 
{
	ArrayList<Particle> _particles;
	int _numParticles;
	int _n;
	double _lowerBound;
	double _upperBound;
	int _maxNumOfIterations;
	double _omega;
	double _phiP;
	double _phiG;
	
	// swarm's best known position
	double[] _g;
	
	public PSO(int _numParticles, int _n, double _lowerBound, double _upperBound, int _maxNumOfIterations, double _omega, double _phiP, double _phiG)
	{
		this._numParticles = _numParticles;
		this._n = _n;
		this._lowerBound = _lowerBound;
		this._upperBound = _upperBound;
		this._maxNumOfIterations = _maxNumOfIterations;
		this._omega = _omega;
		this._phiP = _phiP;
		this._phiG = _phiG;
		_g = new double[_n];
	}
	
	double f(double[] _x, int type)
	{
		double result = 0.0;
		if (type == 1)
		{
			for (int i = 0; i < _x.length; i++)
			{
				result += Math.pow(_x[i], 2);
			}
			return result;
		}
		else
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
	
	public void run()
	{
		// initialize
		for (int i = 0; i < _numParticles; i++)
		{
			_particles.get(i).initialize();
			double fp = f(_particles.get(i).p(), 1);
			double fg = f(_g, 1);			
			if (fp < fg)
			{
				double[] _p = _particles.get(i).p();
				_g = Arrays.copyOf(_p, _p.length);
			}
		}
		
		Random r = new Random();
		for (int iter = 0; iter < _maxNumOfIterations; iter++)
		{
			for (int i = 0; i < _numParticles; i++)
			{
				double[] _newV = new double[_n];
				double[] _newX = new double[_n];
				
				double[] _oldV = _particles.get(i).v();
				double[] _x = _particles.get(i).x();
				double[] _p = _particles.get(i).p();
				for (int d = 0; d < _n; d++)
				{
					double rp = r.nextDouble();
					double rg = r.nextDouble();
					_newV[d] = _omega * _oldV[d] + _phiP * rp * (_p[d] - _x[d]) + _phiG * rg * (_g[d] - _x[d]);
					_newX[d] = _x[d] - _newV[d];
				}
				_particles.get(i).v(_newV);
				_particles.get(i).x(_newX);
				
				if (f(_particles.get(i).x(), 1) < f(_particles.get(i).p(), 1))
				{
					// Update the particle's best known position
					_particles.get(i).p(_particles.get(i).x());
					if (f(_particles.get(i).p(), 1) < f(_g, 1))
					{
						// Update the swarm's best known position
						_g = Arrays.copyOf(_particles.get(i).p(), _n);
					}
				}
			}
		}
	}
	
	public static void main(String[] args)
	{
		int _numParticles = 100;
		int _n = 10;
		double _lowerBound = 0.0;
		double _upperBound = 1.0;
		int _maxNumOfIterations = 100;
		double _omega = 0.5;
		double _phiP = 0.5;
		double _phiG = 0.5;
		
		PSO pso = new PSO(_numParticles, _n, _lowerBound, _upperBound, _maxNumOfIterations, _omega, _phiP, _phiG);
		
		// construct initial particles
		for (int i = 0; i < _numParticles; i++)
		{
			pso.addParticle(new Particle(_n, _lowerBound, _upperBound));;
		}
		
		// Run PSO algorithm
		pso.run();

	}
}
