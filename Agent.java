import java.util.Arrays;
import java.util.Random;

/**
 * @author A. Salamanis
 * @version 0.2
 * @since 2020-04-25
 *
 * Agent class: The class that represents an agent, i.e., a candidate solution.
 */
public class Agent {
    int dimension;
    double[] position;
        
    /**
     * Constructor.
     * @param dimension the dimension of the particle.
     * @param lowerBound the lower bound of the interval from which the initial random values of the Agent are generated.
     * @param upperBound the upper bound of the interval from which the initial random values of the Agent are generated.
     */
    public Agent(int dimension, double lowerBound, double upperBound, Random randomNumberGenerator) {
        this.dimension = dimension;
        this.position = new double[dimension];
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
     * @param position the position of the agent.
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
