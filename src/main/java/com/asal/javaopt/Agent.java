package com.asal.javaopt;

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
    protected int dimension;
    protected double[] position;
        
    /**
     * Constructor.
     * @param dimension the dimension of the particle.
     */
    public Agent(int dimension) {
        this.dimension = dimension;
        this.position = new double[dimension];
    }
    
    /**
     * Initializes the agent.
     * @param lowerBound the lower bound of the interval from which the initial random values of the agent are generated.
     * @param upperBound the upper bound of the interval from which the initial random values of the agent are generated. 
     * @param randomNumberGenerator a random number generator object.
     */
    protected void initialize(double lowerBound, double upperBound, Random randomNumberGenerator) {
        // initialize position
        for (int i = 0; i < position.length; ++i) {
            position[i] = lowerBound + (randomNumberGenerator.nextDouble() * (upperBound - lowerBound));
        }
    }
        
    /**
     * Sets the dimension of the agent.
     * @param dimension the dimension of the agent.
     */
    protected void setDimension(int dimension) {
        this.dimension = dimension;
    }
    
    /**
     * Returns the dimension of the agent.
     * @return the dimension of the agent.
     */
    protected int getDimension() {
        return dimension;
    }
    
    /**
     * Sets the position of the agent.
     * @param position the position of the agent.
     */
    protected void setPosition(double[] position) {
        this.position = Arrays.copyOf(position, position.length);
    }
    
    /**
     * Returns the position of the agent.
     * @return the position of the agent.
     */
    protected double[] getPosition() {
        return position;
    }
    
    /**
     * Returns a specific element from the position vector of the agent.
     * @param elementIndex the index of the element to return.
     * @return the specific element from the position vector of the agent.
     */
    public double getPositionElement(int elementIndex) {
        if ((elementIndex >= 0) && (elementIndex < dimension)) {
            return position[elementIndex];
        } else {
            return -1.0;    // TODO: Better handle the case where index is out of array's bounds
        }
    }
}
