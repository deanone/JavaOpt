public class DistanceCalculator
{
	/**
	 * The first vector.
	 */
	double[] x;

	/**
	 * The second vector.
	 */
	double[] y;

	public DistanceCalculator(double[] x, double[] y)
	{
		this.x = x;
		this.y = y;
	}

	/**
	 * Computes the Euclidean distance between the vectors.
	 * @return the Euclidean distance.
	 */
	public double euclideanDistance()
	{
		double dist = 0.0;
		for (int i = 0; i < x.length; ++i)
		{
			dist += Math.pow(x[i] - y[i], 2);
		}
		dist = Math.sqrt(dist);
		return dist;
	}
}