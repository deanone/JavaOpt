package com.asal.javaopt;

import java.util.Arrays;
import java.util.Random;
import java.lang.Math; 

/**
 * @author A. Salamanis
 * @version 0.2
 * @since 2020-04-25
 *
 * Particle class: The class representing a particle, i.e., an enhanced candidate solution.
 */
public class Particle extends Agent {
    private double[] velocity;
    private double[] bestPosition;

    /**
     * Constructor.
     * @param dimension the dimension of the particle
     * @param lowerBound the lower bound of the interval from which the initial random values of the Particle are generated
     * @param upperBound the upper bound of the interval from which the initial random values of the Particle are generated
     */
    public Particle(int dimension) {
    	super(dimension);
        this.velocity = new double[dimension];
        this.bestPosition = new double[dimension];
    }
    
    /**
     * Initializes the particle.
     * @param lowerBound the lower bound of the interval from which the initial random values of the particle are generated
     * @param upperBound the upper bound of the interval from which the initial random values of the particle are generated
     * @param randomNumberGenerator a random number generator object
     */
    public void initialize(double lowerBound, double upperBound, Random randomNumberGenerator) {
    	// initialize position
    	super.initialize(lowerBound, upperBound, randomNumberGenerator);
        
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
     * Sets the velocity of the particle.
     * @param velocity the velocity of the particle
     */
    public void setVelocity(double[] velocity) {
        this.velocity = Arrays.copyOf(velocity, velocity.length);
    }
    
    /**
     * Returns the velocity of the particle.
     * @return the velocity of the particle
     */
    public double[] getVelocity() {
        return velocity;
    }
    
    /**
     * Returns a specific element from the velocity vector of the particle.
     * @param elementIndex the index of the element to return
     * @return the specific element from the velocity vector of the particle
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
     * @param bestPosition the best position of the particle
     */
    public void setBestPosition(double[] bestPosition) {
        this.bestPosition = Arrays.copyOf(bestPosition, bestPosition.length);
    }
    
    /**
     * Returns the best position of the particle.
     * @return the best position of the particle
     */
    public double[] getBestPosition() {
        return bestPosition;
    }
    
    /**
     * Returns a specific element from the best position vector of the particle.
     * @param elementIndex the index of the element to return
     * @return the specific element from the best position vector of the particle
     */
    public double getBestPositionElement(int elementIndex) {
        if ((elementIndex >= 0) && (elementIndex < dimension)) {
            return bestPosition[elementIndex];
        } else {
            return -1.0;    // TODO: Better handle the case where index is out of array's bounds
        }
    }
}
