import java.util.Arrays;
import java.util.Random;
import java.lang.Math; 

/**
 * @author A. Salamanis
 * @version 0.2
 * @since 2020-04-25
 *
 * Particle class: The class representing a particle, i.e., a candidate solution.
 */
public class Particle {
    int dimension;
    double[] position;
    double[] velocity;
    double[] bestPosition;

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
    public Particle(int dimension, double lowerBound, double upperBound, Random randomNumberGenerator) {
        this.dimension = dimension;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.randomNumberGenerator = randomNumberGenerator;
        
        this.position = new double[dimension];
        this.velocity = new double[dimension];
        this.bestPosition = new double[dimension];
    }
    
    /**
     * Initializes the particle.
     */
    public void initialize() {
    	randomNumberGenerator.setSeed(42);	// for reproducibility of the results
        // initialize position
        for (int i = 0; i < position.length; ++i) {
            position[i] = lowerBound + (randomNumberGenerator.nextDouble() * (upperBound - lowerBound));
        }
        
        // initialize best position
        this.bestPosition = Arrays.copyOf(position, position.length);
        
        // initialize velocity
        double vUpperBound = Math.abs(upperBound - lowerBound);
        double vLowerBound = (-1.0) * vUpperBound;
        for (int i = 0; i < velocity.length; ++i) {
            velocity[i] = vLowerBound + (randomNumberGenerator.nextDouble() * (vUpperBound - vLowerBound));
        }
    }
    
    /**
     * Sets the dimension of the particle.
     * @param dimension the dimension of the particle.
     */
    public void setDimension(int dimension) {
        this.dimension = dimension;
    }
    
    /**
     * Returns the dimension of the particle.
     * @return the dimension of the particle.
     */
    public int getDimension() {
        return dimension;
    }
    
    /**
     * Sets the position of the particle.
     * @param x the position of the particle.
     */
    public void setPosition(double[] position) {
        this.position = Arrays.copyOf(position, position.length);
    }
    
    /**
     * Returns the position of the particle.
     * @return the position of the particle.
     */
    public double[] getPosition() {
        return position;
    }
    
    /**
     * Returns a specific element from the position vector of the particle.
     * @param elementIndex the index of the element to return.
     * @return the specific element from the position vector of the particle.
     */
    public double getPositionElement(int elementIndex) {
        if ((elementIndex >= 0) && (elementIndex < dimension)) {
            return position[elementIndex];
        } else {
            return -1.0;    // TODO: Better handle the case where index is out of array's bounds
        }
    }
    
    /**
     * Sets the velocity of the particle.
     * @param velocity the velocity of the particle.
     */
    public void setVelocity(double[] velocity) {
        this.velocity = Arrays.copyOf(velocity, velocity.length);
    }
    
    /**
     * Returns the velocity of the particle.
     * @return the velocity of the particle.
     */
    public double[] getVelocity() {
        return velocity;
    }
    
    /**
     * Returns a specific element from the velocity vector of the particle.
     * @param i the index of the element to return.
     * @return the specific element from the velocity vector of the particle.
     */
    public double getVelocityElement(int elementIndex) {
        if ((elementIndex >= 0) && (elementIndex < dimension)) {
            return velocity[elementIndex];
        } else {
            return -1.0;    // TODO: Better handle the case where index is out of array's bounds
        }
    }
    
    /**
     * Sets the best position of the particle.
     * @param bestPosition the best position of the particle.
     */
    public void setBestPosition(double[] bestPosition) {
        this.bestPosition = Arrays.copyOf(bestPosition, bestPosition.length);
    }
    
    /**
     * Returns the best position of the particle.
     * @return the best position of the particle.
     */
    public double[] getBestPosition() {
        return bestPosition;
    }
    
    /**
     * Returns a specific element from the best position vector of the particle.
     * @param i the index of the element to return.
     * @return the specific element from the best position vector of the particle.
     */
    public double getBestPositionElement(int elementIndex) {
        if ((elementIndex >= 0) && (elementIndex < dimension)) {
            return bestPosition[elementIndex];
        } else {
            return -1.0;    // TODO: Better handle the case where index is out of array's bounds
        }
    }
}
