import java.util.Arrays;
import java.util.Random;

/**
 * @author A. Salamanis
 * @version 0.2
 * @since 2020-04-25
 *
 * Agent class: The class representing an agent, i.e., a candidate solution.
 */
public class Agent {
    int dimension;
    double[] position;
    
    /**
     * The lower bound of the interval from which the initial random values of the Particle are generated.
     */
    double lowerBound;
    
    /**
     * The upper bound of the interval from which the initial random values of the Particle are generated.
     */
    double upperBound;
    
    Random randomNumberGenerator;
    
    /**
     * Constructor.
     * @param dimension the dimension of the particle.
     * @param lowerBound the lower bound of the interval from which the initial random values of the Particle are generated.
     * @param upperBound the upper bound of the interval from which the initial random values of the Particle are generated.
     */
    public Agent(int dimension, double lowerBound, double upperBound, Random randomNumberGenerator) {
        this.dimension = dimension;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.randomNumberGenerator = randomNumberGenerator;
        this.position = new double[dimension];
    }
    
    /**
     * Initializes the agent.
     */
    public void initialize() {
    	randomNumberGenerator.setSeed(42);	// for reproducibility of the results
        // initialize position
        for (int i = 0; i < position.length; ++i) {
            position[i] = lowerBound + (randomNumberGenerator.nextDouble() * (upperBound - lowerBound));
        }
    }
    
    /**
     * Sets the dimension of the agent.
     * @param dimension the dimension of the agent.
     */
    public void setDimension(int dimension) {
        this.dimension = dimension;
    }
    
    /**
     * Returns the dimension of the agent.
     * @return the dimension of the agent.
     */
    public int getDimension() {
        return dimension;
    }
    
    /**
     * Sets the position of the agent.
     * @param x the position of the agent.
     */
    public void setPosition(double[] position) {
        this.position = Arrays.copyOf(position, position.length);
    }
    
    /**
     * Returns the position of the agent.
     * @return the position of the agent.
     */
    public double[] getPosition() {
        return position;
    }
}
