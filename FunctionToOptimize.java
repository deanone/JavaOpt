public class FunctionToOptimize
{
    /**
     * Variable to distinguish functions from each other.
     */
    int type;

    /**
     * Constructor.
     */
    public FunctionToOptimize(int type)
    {
        this.type = type;
    }

    /**
     * Implements a mathematical function optimized by PSO.
     * @param x the input of the function.
     * @return output of the function.
     */
    double f(double[] x)
    {
        double result = 0.0;
        if (type == 1)  // Sphere function
        {
            for (int i = 0; i < x.length; ++i)
            {
                result += Math.pow(x[i], 2);
            }
            return result;
        }
        else if (type == 2) // Rosenbrock function
        {
            for (int i = 0; i < (x.length - 1); ++i)
            {
                result += (100 * Math.pow(x[i + 1] - Math.pow(x[i], 2), 2) + Math.pow(1 - x[i], 2));
            }
            return result;
        }
        else    // TODO: Add more optimization test functions
        {
            return -1.0;
        }
    }
}