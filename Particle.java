import java.util.Arrays;

public class Particle 
{
	int _n;
	double[] _x;
	double[] _v;
	double[] _p;

	public Particle(int _n)
	{
		this._n = _n;
		_x = new double[_n];
		_v = new double[_n];
		_p = new double[_n];
	}
	
	// method overloading in setters and getters
	
	public void n(int _n)
	{
		this._n = _n;
	}
	
	public int n()
	{
		return _n;
	}
	
	public void x(double[] _x)
	{
		this._x = Arrays.copyOf(_x, _x.length);
	}
	
	public double[] x()
	{
		return _x;
	}
	
	public double x(int i)
	{
		if (i >= 0 && i < _n)
		{
			return _x[i];
		}
		else
		{
			return -1.0;
		}
	}
	
	public void v(double[] _v)
	{
		this._v = Arrays.copyOf(_v, _v.length);
	}
	
	public double[] v()
	{
		return _v;
	}
	
	public double v(int i)
	{
		if (i >= 0 && i < _n)
		{
			return _v[i];
		}
		else
		{
			return -1.0;
		}
	}
	
	public void p(double[] _p)
	{
		this._p = Arrays.copyOf(_p, _p.length);
	}
	
	public double[] p()
	{
		return _p;
	}
	
	public double p(int i)
	{
		if (i >= 0 && i < _n)
		{
			return _p[i];
		}
		else
		{
			return -1.0;
		}
	}
}
