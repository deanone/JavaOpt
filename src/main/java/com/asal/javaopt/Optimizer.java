package com.asal.javaopt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * @author A. Salamanis
 * @version 0.2
 * @since 2020-04-25
 *
 * Optimizer class: The base class that implements a general optimization algorithm.
 */
class Optimizer {
	protected PropertiesParser psoPropertiesParser;
	protected FunctionToOptimize funcToOptimize;
	protected int dimension;
	protected int numAgents;
	ArrayList<Agent> swarm;
	protected double tol;
	protected int maxNumIterations;
	protected double[] solution;
	
	/**
	 * Constructor
	 * @param propertiesFilename the name of the .properties file containing the configuration of the optimization algorithm
	 */
	protected Optimizer(String propertiesFilename) {
		psoPropertiesParser = new PropertiesParser(propertiesFilename);
		swarm = new ArrayList<Agent>();
		try {
        	// read properties of the optimization algorithm from a .properties file
            psoPropertiesParser.readPropertiesValues();
            this.funcToOptimize = new FunctionToOptimize(psoPropertiesParser.getPropertyAsInteger("fType"));
            this.dimension = psoPropertiesParser.getPropertyAsInteger("dimension");
            this.numAgents = psoPropertiesParser.getPropertyAsInteger("numAgents");
            this.tol = psoPropertiesParser.getPropertyAsDouble("tol");
            this.maxNumIterations = psoPropertiesParser.getPropertyAsInteger("maxNumIterations");
            double lowerBound = psoPropertiesParser.getPropertyAsDouble("lowerBound");
            double upperBound = psoPropertiesParser.getPropertyAsDouble("upperBound");
            
            Random randomNumberGenerator = new Random();
            randomNumberGenerator.setSeed(42); // for reproducibility of the results
    		
            // initialize the solution vector
            solution = new double[dimension];
            for (int i = 0; i < solution.length; ++i) {
            	solution[i] = lowerBound + (randomNumberGenerator.nextDouble() * (upperBound - lowerBound));
            }
		} catch (Exception e) {
            System.out.println("Exception: " + e);
        }
	}
	
	/**
	 * Sets the dimension of the candidate solutions.
	 * @param dimension the dimension of the candidate solutions
	 */
	protected void setDimension(int dimension) {
		this.dimension = dimension;
	}
	
    /**
     * Returns the dimension of the candidate solutions.
     * @return the dimension of the candidate solutions
     */
    protected int getDimension() {
        return dimension;
    }
    
    /**
     * Sets the number of agents.
     * @param numAgents the number of agents
     */
    protected void setNumAgents(int numAgents) {
    	this.numAgents = numAgents;
    }
    
    /**
     * Returns the number of agents.
     * @return the number of agents
     */
    protected int getNumAgents() {
        return swarm.size();
    }
    
    /**
     * Adds a new agent to the swarm.
     * @param agent the new agent
     */
    protected void addAgent(Agent agent) {
        swarm.add(agent);
    }

    /**
     * Returns an agent from the swarm.
     * @param agentIndex the index of the agent to return
     * @return the agent at the input index
     */
    public Agent getAgent(int agentIndex) {
        return swarm.get(agentIndex);
    }
    
    /**
     * Sets the tolerance of the optimization algorithm.
     * @param tol the tolerance of the optimization algorithm
     */
    protected void setTolerance(double tol) {
    	this.tol = tol;
    }
	
    /**
     * Returns the tolerance of the optimization algorithm.
     * @return the tolerance of the optimization algorithm
     */
    protected double getTolerance() {
        return tol;
    }
    
    /**
     * Sets the maximum number of iterations of the optimization algorithm.
     * @param maxNumIterations the maximum number of iterations of the optimization algorithm
     */
    protected void setMaxNumIterations(int maxNumIterations) {
    	this.maxNumIterations = maxNumIterations;
    }
    
    /**
     * Returns the maximum number of iterations of the optimization algorithm.
     * @return the maximum number of iterations of the optimization algorithm
     */
    public int getMaxNumIterations() {
        return maxNumIterations;
    }
	
    /**
     * Returns the solution of the optimization algorithm.
     * @return the solution of the optimization algorithm
     */
    protected double[] getSolution() {
        return solution;
    }
    
    /**
     * Prints the solution of the optimization algorithm.
     */
    protected void printSolution() {
        System.out.println();
        System.out.println("<------------------------ Solution ------------------------>");
        char[] spaces = new char[17];
        Arrays.fill(spaces, ' ');
        System.out.print(new String(spaces));
        System.out.print("g = [");
        System.out.printf("%.4f", solution[0]);
        for (int solutionElementIndex = 1; solutionElementIndex < solution.length; ++solutionElementIndex) {
            System.out.printf(", %.4f", solution[solutionElementIndex]);
        }
        System.out.println("]");
        System.out.println("<---------------------------------------------------------->");
    }
    
    protected void run() {
    }
}
