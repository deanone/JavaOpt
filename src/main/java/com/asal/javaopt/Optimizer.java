package com.asal.javaopt;

/**
 * @author A. Salamanis
 * @version 0.2
 * @since 2020-04-25
 *
 * Optimizer class: The base class that implements a general algorithm.
 */
public class Optimizer {
	protected PropertiesParser psoPropertiesParser;
	protected FunctionToOptimize funcToOptimize;
	protected int dimension;
	protected double tol;
	protected int maxNumIterations;
	protected double[] solution;
	
	/**
	 * Constructor
	 * @param propertiesFilename the name of the .properties file containing the configuration of the optimization algorithm. 
	 */
	protected Optimizer(String propertiesFilename) {
		psoPropertiesParser = new PropertiesParser(propertiesFilename);
	}
	
	/**
	 * Sets the dimension of the candidate solutions.
	 * @param dimension the dimension of the candidate solutions.
	 */
	protected void setDimension(int dimension) {
		this.dimension = dimension;
	}
	
    /**
     * Returns the dimension of the candidate solutions.
     * @return the dimension of the candidate solutions.
     */
    protected int getDimension() {
        return dimension;
    }
    
    /**
     * Sets the tolerance of the optimization algorithm.
     * @param tol the tolerance of the optimization algorithm.
     */
    protected void setTolerance(double tol) {
    	this.tol = tol;
    }
	
    /**
     * Returns the tolerance of the optimization algorithm.
     * @return the tolerance of the optimization algorithm.
     */
    protected double getTolerance() {
        return tol;
    }
    
    /**
     * Sets the maximum number of iterations of the optimization algorithm.
     * @param maxNumIterations the maximum number of iterations of the optimization algorithm.
     */
    protected void setMaxNumIterations(int maxNumIterations) {
    	this.maxNumIterations = maxNumIterations;
    }
    
    /**
     * Returns the maximum number of iterations of the optimization algorithm.
     * @return the maximum number of iterations of the optimization algorithm.
     */
    public int getMaxNumIterations() {
        return maxNumIterations;
    }
	
    /**
     * Returns the solution of the optimization algorithm.
     * @return the solution of the optimization algorithm.
     */
    protected double[] getSolution() {
        return solution;
    }
}
